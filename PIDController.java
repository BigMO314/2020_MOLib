package frc.molib;

import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpiutil.math.MathUtil;

/**
 * A wrapper class on the WPI PIDController
 * <p>Adds an enabled flag and restricted output range.</p>
 */
public class PIDController extends edu.wpi.first.wpilibj.controller.PIDController {
	private boolean enabled = false;

	private double minLimit = Double.NEGATIVE_INFINITY;
	private double maxLimit = Double.POSITIVE_INFINITY;

	/**
	 * Allocates a PIDController with the given constants for Kp, Ki, and Kd and a default period of 0.02 seconds.
	 * @param Kp The proportional coefficient
	 * @param Ki The integral coefficient
	 * @param Kd The derivative coefficient
	 */
	public PIDController(double Kp, double Ki, double Kd) { super(Kp, Ki, Kd); }

	/**
	 * Allocates a PIDController with the given constants for Kp, Ki, and Kd.
	 * @param Kp The proportional coefficient
	 * @param Ki The integral coefficient
	 * @param Kd The derivative coefficient
	 * @param period The period between controller updates in seconds.
	 */
	public PIDController(double Kp, double Ki, double Kd, double period) { super(Kp, Ki, Kd, period); }

	public boolean isEnabled() { return enabled; }
	public void enable() { enabled = true; }
	public void disable() { enabled = false; }

	public void configOutputRange(double min, double max) {
		minLimit = min;
		maxLimit = max;
	}

	@Override 
	public double calculate(double measurement) { return MathUtil.clamp(super.calculate(measurement), minLimit, maxLimit); }

	@Override 
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("MOLib PIDController");
		builder.setSafeState(() -> enabled = false);
		builder.addDoubleProperty("P", this::getP, this::setP);
		builder.addDoubleProperty("I", this::getI, this::setI);
		builder.addDoubleProperty("D", this::getD, this::setD);
		builder.addBooleanProperty("Enabled", this::isEnabled, value -> enabled = value);
		builder.addDoubleProperty("Setpoint", this::getSetpoint, this::setSetpoint);
		builder.addBooleanProperty("On Target", this::atSetpoint, null);
	}
}