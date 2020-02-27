package frc.molib.drivetrain;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

@Deprecated
public class TankDrivetrain {
	private subsystem.motors.Generic sys_Motors;
	private subsystem.shifter.Generic sys_Shifter;
	
	public enum Gear {
		HighSpeed,
		LowSpeed;
	}
	
	private interface subsystem {
		public interface motors {
			public abstract class Generic {
				protected double leftPower = 0.0;
				protected double rightPower = 0.0;
				
				public abstract void update();
				
				public void set(double left, double right) {
					leftPower = left;
					rightPower = right;
				}
			}
			
			public class TwoTalonSRX extends Generic {
				private TalonSRX mtr_L_Drive, mtr_R_Drive;
				
				public TwoTalonSRX(TalonSRX mtr_L_Drive, TalonSRX mtr_R_Drive) {
					this.mtr_L_Drive = mtr_L_Drive;
					this.mtr_R_Drive = mtr_R_Drive;
				}
				
				@Override
				public void update() {
					mtr_L_Drive.set(ControlMode.PercentOutput, leftPower);
					mtr_R_Drive.set(ControlMode.PercentOutput, rightPower);
				}
			}
			
			public static class FourTalonSRX extends Generic {
				private TalonSRX mtr_L_Drive_1, mtr_L_Drive_2;
				private TalonSRX mtr_R_Drive_1, mtr_R_Drive_2;

				public FourTalonSRX(TalonSRX mtr_L_Drive_1, TalonSRX mtr_L_Drive_2, TalonSRX mtr_R_Drive_1, TalonSRX mtr_R_Drive_2) {
					this.mtr_L_Drive_1 = mtr_L_Drive_1;
					this.mtr_L_Drive_2 = mtr_L_Drive_2;
					this.mtr_R_Drive_1 = mtr_R_Drive_1;
					this.mtr_R_Drive_2 = mtr_R_Drive_2;
				}

				@Override public void update() {
					mtr_L_Drive_1.set(ControlMode.PercentOutput, leftPower);
					mtr_L_Drive_2.set(ControlMode.PercentOutput, leftPower);
					mtr_R_Drive_1.set(ControlMode.PercentOutput, rightPower);
					mtr_R_Drive_2.set(ControlMode.PercentOutput, rightPower);
				}
			}
		}
		
		public interface shifter {
			public abstract class Generic {
				protected Gear currentGear;
				public abstract void update();
				public void set(Gear newGear) { this.currentGear = newGear; }
			}
			
			public class None extends Generic {
				@Override
				public void update() { }
			}
			
			public class Single extends Generic {
				private Solenoid sol_Shifter;
				private Gear defaultGear;
				public Single(Solenoid sol_Shifter, Gear defaultGear) {
					this.sol_Shifter = sol_Shifter;
					this.defaultGear = defaultGear;
					this.set(defaultGear);
				}
				
				@Override
				public void update() {
					if (currentGear == defaultGear)
						sol_Shifter.set(false);
					else
						sol_Shifter.set(true);
				}
			}
			
			public class Double extends Generic {
				private DoubleSolenoid sol_Shifter;
				private Gear forwardGear;
				public Double(DoubleSolenoid sol_Shifter, Gear forwardGear) {
					this.sol_Shifter = sol_Shifter;
					this.forwardGear = forwardGear;
					this.set(forwardGear);
				}
				
				@Override
				public void update() {
					if (currentGear == forwardGear)
						sol_Shifter.set(DoubleSolenoid.Value.kForward);
					else
						sol_Shifter.set(DoubleSolenoid.Value.kReverse);
				}
			}
		}
	}
	
	public TankDrivetrain(
			TalonSRX mtr_L_Drive, TalonSRX mtr_R_Drive) {
		sys_Motors = new subsystem.motors.TwoTalonSRX(
				mtr_L_Drive, mtr_R_Drive);
		sys_Shifter = new subsystem.shifter.None();
	}
	
	public TankDrivetrain(
			TalonSRX mtr_L_Drive_1, TalonSRX mtr_L_Drive_2, 
			TalonSRX mtr_R_Drive_1, TalonSRX mtr_R_Drive_2) {
		sys_Motors = new subsystem.motors.FourTalonSRX(
				mtr_L_Drive_1, mtr_L_Drive_2, 
				mtr_R_Drive_1, mtr_R_Drive_2);
		sys_Shifter = new subsystem.shifter.None();
	}
	
	public TankDrivetrain(
			TalonSRX mtr_L_Drive, TalonSRX mtr_R_Drive,
			Solenoid sol_Shifter, Gear defaultGear) {
		sys_Motors = new subsystem.motors.TwoTalonSRX(
				mtr_L_Drive, mtr_R_Drive);
		sys_Shifter = new subsystem.shifter.Single(
				sol_Shifter, defaultGear);
	}
	
	public TankDrivetrain(
			TalonSRX mtr_L_Drive_1, TalonSRX mtr_L_Drive_2, 
			TalonSRX mtr_R_Drive_1, TalonSRX mtr_R_Drive_2,
			Solenoid sol_Shifter, Gear defaultGear) {
		sys_Motors = new subsystem.motors.FourTalonSRX(
				mtr_L_Drive_1, mtr_L_Drive_2, 
				mtr_R_Drive_1, mtr_R_Drive_2);
		sys_Shifter = new subsystem.shifter.Single(
				sol_Shifter, defaultGear);
	}
	
	public TankDrivetrain(
			TalonSRX mtr_L_Drive, TalonSRX mtr_R_Drive,
			DoubleSolenoid sol_Shifter, Gear forwardGear) {
		sys_Motors = new subsystem.motors.TwoTalonSRX(
				mtr_L_Drive, mtr_R_Drive);
		sys_Shifter = new subsystem.shifter.Double(
				sol_Shifter, forwardGear);
	}
	
	public TankDrivetrain(
			TalonSRX mtr_L_Drive_1, TalonSRX mtr_L_Drive_2, 
			TalonSRX mtr_R_Drive_1, TalonSRX mtr_R_Drive_2,
			DoubleSolenoid sol_Shifter, Gear forwardGear) {
		sys_Motors = new subsystem.motors.FourTalonSRX(
				mtr_L_Drive_1, mtr_L_Drive_2, 
				mtr_R_Drive_1, mtr_R_Drive_2);
		sys_Shifter = new subsystem.shifter.Double(
				sol_Shifter, forwardGear);
	}
	
	public void setDrive(double left, double right) { sys_Motors.set(left, right); }
	public void setGear(Gear gear) { sys_Shifter.set(gear); }
	
	public void update() {
		sys_Motors.update();
		sys_Shifter.update();
	}
}
