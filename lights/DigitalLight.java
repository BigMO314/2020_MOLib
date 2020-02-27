package frc.molib.lights;

import edu.wpi.first.wpilibj.Solenoid;

/**
 * Interface for controlling simple lights that can be controlled through the PCM
 */
public class DigitalLight {
	private Solenoid sol_Controller;
	
	/**
	 * Constructor using the default PCM ID
	 * @param channel The channel on the PCM to control
	 */
	public DigitalLight(int channel) { this.sol_Controller = new Solenoid(channel); }
	public void turnOn() { sol_Controller.set(true); }
	public void turnOff() { sol_Controller.set(false); }
	public void toggle() { sol_Controller.set(!sol_Controller.get()); }
}
