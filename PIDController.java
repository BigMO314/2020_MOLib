package frc.molib;

import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpiutil.math.MathUtil;

public class PIDController extends edu.wpi.first.wpilibj.controller.PIDController {
	private boolean enabled = false;

	private double minLimit = Double.NEGATIVE_INFINITY;
	private double maxLimit = Double.POSITIVE_INFINITY;

	public PIDController(double Kp, double Ki, double Kd) { super(Kp, Ki, Kd); }
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
		builder.addDoubleProperty("p", this::getP, this::setP);
		builder.addDoubleProperty("I", this::getI, this::setI);
		builder.addDoubleProperty("D", this::getD, this::setD);
		builder.addBooleanProperty("Enabled", this::isEnabled, value -> enabled = value);
		builder.addDoubleProperty("Setpoint", this::getSetpoint, this::setSetpoint);
		builder.addBooleanProperty("On Target", this::atSetpoint, null);
	}
}