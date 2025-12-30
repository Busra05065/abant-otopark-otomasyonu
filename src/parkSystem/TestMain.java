package parkSystem;

import java.time.LocalDateTime;

public class TestMain {
    public static void main(String[] args) {
        ParkingService service = new ParkingService();

        System.out.println("--- OTOPARK TEST SİSTEMİ BAŞLATILDI ---\n");

       
        Vehicle v1 = service.findVehicleTypeByPlate("34YC356"); 
        Vehicle v2 = service.findVehicleTypeByPlate("14ESK14");
        Vehicle v3 = service.findVehicleTypeByPlate("34TEST999");
        Vehicle v4 = service.findVehicleTypeByPlate("26AGD441"); 
        Vehicle v5 = service.findVehicleTypeByPlate("03BE44"); 
        

        // Giriş İşlemleri
        System.out.println("Girişler Yapılıyor:");
        System.out.println(service.vehicleEntry(v1));
        System.out.println(service.vehicleEntry(v2));
        System.out.println(service.vehicleEntry(v3));
        System.out.println(service.vehicleEntry(v4));
        System.out.println(service.vehicleEntry(v5));

        System.out.println("\n--- ÇIKIŞ VE ÜCRET HESAPLAMA ---");

       
        double f1 = service.vehicleExit(v1);
        double f2 = service.vehicleExit(v2);
        double f3 = service.vehicleExit(v3);
        double f4 = service.vehicleExit(v4);
        double f5 = service.vehicleExit(v5);
        
        
        System.out.println(v1.getPlate() + " [Beklenen: İNDİRİMLİ] Ücret: " + f1 + " TL");
        System.out.println(v2.getPlate() + " [Beklenen: NORMAL - Süre Dolmuş] Ücret: " + f2 + " TL");
        System.out.println(v3.getPlate() + " [Beklenen: NORMAL - Ziyaretçi] Ücret: " + f3 + " TL");
        System.out.println(v4.getPlate() + " [Beklenen: İNDİRİMLİ] Ücret: " + f4 + " TL");
        System.out.println(v5.getPlate() + " [Beklenen: İNDİRİMLİ] Ücret: " + f5 + " TL");

        System.out.println("\n--- TÜM KAYITLAR LİSTELENİYOR ---");
        service.printAllRecords();
    }
}