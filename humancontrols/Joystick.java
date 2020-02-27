package frc.molib.humancontrols;

/**
 * Wrapper class on the WPI Joystick class.
 * <p>Handle input from standard Joysticks connected to the Driver Station.</p>
 * <p>This class handles standard input that comes from the Driver Station. Each time a value is
 * requested the most recent value is returned. There is a single class instance for each joystick
 * and the mapping of ports to hardware buttons depends on the code in the Driver Station.
 * 
 * @see edu.wpi.first.wpilibj.Joystick
 */
public class Joystick extends edu.wpi.first.wpilibj.Joystick {

	private double deadzoneThreshold = 0.1;

	public Joystick(int port) { super(port); }

	public void configDeadzoneThreshold(double value) { this.deadzoneThreshold = value; }

	@Override
	public double getRawAxis(int axis) { 
		if (axis != getThrottleChannel())
			return ControlsMath.deaden(super.getRawAxis(axis), deadzoneThreshold); 
		else
			return super.getRawAxis(axis);
	}
}