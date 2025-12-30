package parkSystem;

public class ParkingSpot {
    private final int spotId;   
    private final int floor;    
    private boolean occupied;   

    
    public ParkingSpot(int spotId, int floor) {
        this.spotId = spotId;
        this.floor = floor;
        this.occupied = false;
    }

   
    public int getFloor() {
        return floor;
    }

    
    public int getSpotId() {
        return spotId;
    }

    
    public boolean isOccupied() {
        return occupied;
    }

    
    public void occupy() {
        this.occupied = true;
    }

   
    public void release() {
        this.occupied = false;
    }

    @Override
    public String toString() {
        return "Kat: " + floor + " - Spot: " + spotId + " [" + (occupied ? "DOLU" : "BOŞ") + "]";
    }
}