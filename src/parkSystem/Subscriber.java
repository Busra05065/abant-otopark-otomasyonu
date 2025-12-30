package parkSystem;

import java.time.LocalDate;

public class Subscriber {
	private String plate;
	private LocalDate endDate;
	
	public Subscriber(String plate, LocalDate endDate) {
		this.plate=plate;
		
		this.endDate=endDate;
	
	}
	public String getPlate() {
		return plate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
		}
	public boolean isActive() {
		return endDate.isAfter(LocalDate.now());
		}
	

}
