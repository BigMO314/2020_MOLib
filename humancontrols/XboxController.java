package frc.molib.humancontrols;

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

	private double deadzoneThreshold, triggerThreshold;
	
	public XboxController(int port) { this(port, 0.0, 0.5); }
	public XboxController(int port, double deadzoneThreshold) { this(port, deadzoneThreshold, 0.5); }
	public XboxController(int port, double deadzoneThreshold, double triggerThreshold) {
		super(port);
		this.deadzoneThreshold = deadzoneThreshold;
		this.triggerThreshold = triggerThreshold;
	}
	
	public void configDeadzoneThreshold(double value) { this.deadzoneThreshold = value; }
	public void configTriggerThreshold(double value) { this.triggerThreshold = value; }

	public void setRumble(double value) { setRumble(value, value); }
	public void setRumble(double leftValue, double rightValue) {
		super.setRumble(RumbleType.kLeftRumble, leftValue);
		super.setRumble(RumbleType.kRightRumble, rightValue);
	}
	
	@Override public double getRawAxis(int axis) { return ControlsMath.deaden(super.getRawAxis(axis), deadzoneThreshold); }
	public boolean getTriggerButton(Hand hand) { return super.getTriggerAxis(hand) > this.triggerThreshold; }
}
