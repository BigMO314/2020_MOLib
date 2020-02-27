package frc.molib.humancontrols;

public class Joystick extends edu.wpi.first.wpilibj.Joystick {

	private double deadzoneThreshold;

	public Joystick(int port) { this(port, 0.0); }
	public Joystick(int port, double deadzoneThreshold) { 
		super(port);
		this.deadzoneThreshold = deadzoneThreshold;
	}

	public void configDeadzoneThreshold(double value) { this.deadzoneThreshold = value; }

	@Override
	public double getRawAxis(int axis) { 
		if (axis != getThrottleChannel())
			return ControlsMath.deaden(super.getRawAxis(axis), deadzoneThreshold); 
		else
			return super.getRawAxis(axis);
	}
}