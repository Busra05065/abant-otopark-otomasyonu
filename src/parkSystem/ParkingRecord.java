package parkSystem;

import java.time.Duration;
import java.time.LocalDateTime;


public class ParkingRecord {
	private String plate;
	private LocalDateTime entryTime;
	private LocalDateTime exitTime;
	private double fee;
	private int floorNumber;
	private int spotNumber;

	public int getFloorNumber() { 
		return floorNumber; }
	
	public int getSpotNumber() { 
		return spotNumber; }
	
	public ParkingRecord (String plate) {
		this.plate=plate;
		this.entryTime=LocalDateTime.now();
		}
		
		public String getPlate(){
			return plate;
		}
		
		public LocalDateTime getEntryTime() {
			return entryTime;
		}
		public LocalDateTime getExitTime() {
			return exitTime;
		}
		public double getFee() {
			return fee;
		}
		public void setExitTime (LocalDateTime exitTime ) {
			this.exitTime= exitTime;
		}
		public void setFee (double fee ) {
			this.fee= fee;
		}

		public double calculateFee() {
	
			return fee;
		}
		
}
