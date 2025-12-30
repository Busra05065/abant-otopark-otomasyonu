package parkSystem;

import java.time.LocalDateTime;

public class SubscriberVehicle extends Vehicle{
	
	public SubscriberVehicle(String plate, LocalDateTime localDateTime) {
		super(plate, new SubscriberFeeCalculator());
	}

}
