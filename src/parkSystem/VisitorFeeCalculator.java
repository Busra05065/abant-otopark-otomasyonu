package parkSystem;

public class VisitorFeeCalculator implements FeeCalculator{
	
	@Override
	
	public double calculateFee(long hours) {
		if (hours<=1)
			return 75;
		else 
			return 75 + (hours-1)*20;
		
	}
	
}
