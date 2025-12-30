package parkSystem;

public class VisitorVehicle extends Vehicle{
	
	public VisitorVehicle(String vehicle) {
		super(vehicle, new VisitorFeeCalculator());
	}
	

}
