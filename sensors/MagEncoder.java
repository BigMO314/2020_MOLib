package frc.molib.sensors;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import com.ctre.phoenix.motorcontrol.can.BaseTalon;

/**
 * Represents the Magnetic Encoder attached to one of the CTRE Talon Motor Controllers
 * @see com.ctre.phoenix.motorcontrol.can.TalonSRX
 * @see com.ctre.phoenix.motorcontrol.can.TalonFX
 */
public class MagEncoder implements Sendable{
	private final BaseTalon mtrSource;
	private double distancePerPulse;
	
	/**
	 * Constructor
	 * @param mtrSource The CTRE Talon Motor Montroller the Mag Encoder is attacted to.
	 */
	public MagEncoder(BaseTalon mtrSource) { 
		this.mtrSource = mtrSource; 
		SendableRegistry.addLW(this, "Mag Encoder", mtrSource.getDeviceID());
	}
	
	public double getDistancePerPulse() { return distancePerPulse; }
	public void configDistancePerPulse(double distancePerPulse) { this.distancePerPulse = distancePerPulse; }
	public double getDistance() { return mtrSource.getSelectedSensorPosition(0) * distancePerPulse; }
	public double getRate() { return mtrSource.getSelectedSensorVelocity(0) * 10.0 * distancePerPulse; }
	public void reset() { mtrSource.setSelectedSensorPosition(0, 0, 0); }

	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("MOLib Mag Encoder");

		builder.addDoubleProperty("Speed", this::getRate, null);
		builder.addDoubleProperty("Distance", this::getDistance, null);
	}
}
