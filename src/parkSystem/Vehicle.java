package parkSystem;

public abstract class Vehicle {
	protected String plate;
	protected FeeCalculator feeCalculator;
	
	public Vehicle( String vehicle, FeeCalculator feeCalculator) {
		this.plate=vehicle;
		this.feeCalculator=feeCalculator;
	}
	public String getPlate() {
		return plate;
	}
	public double calculateFee(long hours) {
		return feeCalculator.calculateFee(hours);
	}
	

}
