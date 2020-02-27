package frc.molib.humancontrols;

/**
 * Wrapper class on the WPI XboxController class
 * <p>Handle input from Xbox 360 or Xbox One controllers connected to the Driver Station.</p>
 * <p>This class handles Xbox input that comes from the Driver Station. Each time a value is
 * requested the most recent value is returned. There is a single class instance for each controller
 * and the mapping of ports to hardware buttons depends on the code in the Driver Station.</p>
 * 
 * @see edu.wpi.first.wpilibj.XboxController
 */
public class XboxController extends edu.wpi.first.wpilibj.XboxController {
	public enum Axis {
		kXLeft(0),
		kYLeft(1),
		kXRight(2),
		kYRight(3),
		kTriggerLeft(4),
		kTriggerRight(5);

		public final int value;
		private Axis(int value) {
			this.value = value;
		}
	}

	private double deadzoneThreshold = 0.1;
	private double triggerThreshold = 0.5;
	
	/**
	 * Construct an instance of an Xbox Controller.
	 * @param port The port on the Driver Station that the controller is plugged into.
	 */
	public XboxController(int port) { super(port); }
	
	public void configDeadzoneThreshold(double value) { this.deadzoneThreshold = value; }
	public void configTriggerThreshold(double value) { this.triggerThreshold = value; }

	public void setRumble(double value) { setRumble(value, value); }
	public void setRumble(double leftValue, double rightValue) {
		super.setRumble(RumbleType.kLeftRumble, leftValue);
		super.setRumble(RumbleType.kRightRumble, rightValue);
	}
	
	@Override public double getRawAxis(int axis) { return ControlsMath.deaden(super.getRawAxis(axis), deadzoneThreshold); }
	public boolean getTriggerButton(Hand hand) { return super.getTriggerAxis(hand) > this.triggerThreshold; }
	public double getTriggerAxis() { return getTriggerAxis(Hand.kRight) - getTriggerAxis(Hand.kLeft); }
}
