package frc.molib.sensors;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import com.ctre.phoenix.motorcontrol.can.BaseTalon;

public class MagEncoder implements Sendable{
	private final BaseTalon mtrSource;
	private double distancePerPulse;
	
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
		builder.addDoubleProperty("Distance per Tick", this::getDistancePerPulse, this::configDistancePerPulse);
	}
}
