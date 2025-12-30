package parkSystem;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class ParkingService {
    private List<ParkingSpot> spots = new ArrayList<>();
    private Map<String, Vehicle> activeVehicles = new HashMap<>();
    private Map<String, ParkingRecord> activeRecords = new HashMap<>();

    public ParkingService() {
        
        for (int floor = 1; floor <= 3; floor++) {
            for (int i = 1; i <= 10; i++) {
                spots.add(new ParkingSpot(i, floor));
            }
        }
    }

    public Vehicle findVehicleTypeByPlate(String plate) {
        String sql = "SELECT * FROM subscriber WHERE plate = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, plate);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                
                java.sql.Timestamp ts = rs.getTimestamp("end_date");
                return new SubscriberVehicle(plate, ts.toLocalDateTime()); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
        return new VisitorVehicle(plate); 
    }
    
    private ParkingSpot findAvailableSpot() {
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) return spot;
        }
        return null;
    }

    public String vehicleEntry(Vehicle vehicle) {
        String plate = vehicle.getPlate();
        if (activeVehicles.containsKey(plate)) return "Araç zaten içeride!";

        ParkingSpot freeSpot = findAvailableSpot();
        if (freeSpot == null) return "Otopark dolu!";

        Vehicle determinedVehicle = findVehicleTypeByPlate(plate);
        freeSpot.occupy();
        activeVehicles.put(plate, determinedVehicle);

        ParkingRecord record = new ParkingRecord(plate);
        activeRecords.put(plate, record);

        
        String sql = "INSERT INTO parking_record (plate, entry_time, floor_number, spot_number) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, plate);
            ps.setObject(2, record.getEntryTime());
            ps.setInt(3, freeSpot.getFloor());
            ps.setInt(4, freeSpot.getSpotId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }

    
        return "Giriş Başarılı: " + plate + " (Kat: " + freeSpot.getFloor() + " | Spot: " + freeSpot.getSpotId() + ")";
    }

    public double vehicleExit(Vehicle v) {
        String plate = v.getPlate();
        Vehicle vehicle = activeVehicles.get(plate);
        ParkingRecord record = activeRecords.get(plate);

        if (vehicle == null) return -1;

        record.setExitTime(LocalDateTime.now());
        long hours = Duration.between(record.getEntryTime(), record.getExitTime()).toHours();
        if (hours == 0) hours = 1;

        double fee = vehicle.calculateFee(hours);

       
        String sql = "UPDATE parking_record SET exit_time = ?, fee = ? WHERE plate = ? AND exit_time IS NULL";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, record.getExitTime());
            ps.setDouble(2, fee);
            ps.setString(3, plate);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }

        activeVehicles.remove(plate);
        activeRecords.remove(plate);
        return fee;
    }

    public void listAllParkingRecords() {
        String sql = "SELECT * FROM parking_record";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- Mevcut Kayıtlar ---");
            while (rs.next()) {
                System.out.println("Plaka: " + rs.getString("plate") + " | Kat: " + rs.getInt("floor_number") + " | Ücret: " + rs.getDouble("fee"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void printAllRecords() { listAllParkingRecords(); }
}