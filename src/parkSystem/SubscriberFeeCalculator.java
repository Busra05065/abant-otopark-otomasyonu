package parkSystem;

public class SubscriberFeeCalculator implements FeeCalculator{
	
	@Override
	public double calculateFee(long hours) {
		double fee=0;
		if (hours<=1) {
			fee=75;}
		else 
		{fee=75 + (hours-1)*20; }
		
		return fee * 0.7;
	}

	

}
