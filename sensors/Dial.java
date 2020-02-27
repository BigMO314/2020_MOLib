package frc.molib.sensors;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

/**
 * An interface to a dial with multiple distinct positions.
 */
public class Dial {
	private AnalogPotentiometer potSource;
	private final int positions;
	//private final double maxVoltage = 1.0; //TODO: Find real max voltage
	
	/**
	 * Constructor
	 * @param channel The analog channel this dial is plugged into
	 * @param maxPosition The number of positions the dial should detect
	 */
	public Dial(int channel, int positions) {
		this.potSource = new AnalogPotentiometer(channel);
		this.positions = positions;
	}
	
	public int get() {
		double currentVoltage = potSource.get();
		for (int position = 1; position <= positions; position++)
			if (currentVoltage <= ((1.0 / (double) (positions - 1)) * (double) (position - 0.5)))
				return position;
		return -1;
	}
}
