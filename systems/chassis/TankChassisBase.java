package frc.molib.systems.chassis;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SolenoidBase;
import edu.wpi.first.wpilibj.SpeedController;
import frc.molib.systems.SystemBase;



@SuppressWarnings("rawtypes")
public abstract class TankChassisBase implements SystemBase {
	protected interface Subsystem {
		public abstract static class Motors implements SystemBase {
			protected double mPower_Left, mPower_Right;
			public void set(double left, double right) { mPower_Left = left; mPower_Right = right; }
	
			public static class Two<MotorController extends SpeedController> extends Motors {
				private final MotorController mtrDrive_Left;
				private final MotorController mtrDrive_Right;
	
				public Two(MotorController mtrDrive_Left, MotorController mtrDrive_Right) {
					this.mtrDrive_Left = mtrDrive_Left;
					this.mtrDrive_Right = mtrDrive_Right;
				}
	
				@Override public void update() {
					mtrDrive_Left.set(mPower_Left);
					mtrDrive_Right.set(mPower_Right);
				}
			}
	
			public static class Four<MotorController extends SpeedController> extends Motors {
				private final MotorController mtrDrive_Left_1;
				private final MotorController mtrDrive_Left_2;
				private final MotorController mtrDrive_Right_1;
				private final MotorController mtrDrive_Right_2;
	
				public Four(MotorController mtrDrive_Left_1, MotorController mtrDrive_Left_2, 
				MotorController mtrDrive_Right_1, MotorController mtrDrive_Right_2) {
					this.mtrDrive_Left_1 = mtrDrive_Left_1;
					this.mtrDrive_Left_2 = mtrDrive_Left_2;
					this.mtrDrive_Right_1 = mtrDrive_Right_1;
					this.mtrDrive_Right_2 = mtrDrive_Right_2;
				}
	
				@Override public void update() {
					mtrDrive_Left_1.set(mPower_Left);
					mtrDrive_Left_2.set(mPower_Left);
					mtrDrive_Right_1.set(mPower_Right);
					mtrDrive_Right_2.set(mPower_Right);
				}
			}
	
			public static class Six<MotorController extends SpeedController> extends Motors {
				private final MotorController mtrDrive_Left_1;
				private final MotorController mtrDrive_Left_2;
				private final MotorController mtrDrive_Left_3;
				private final MotorController mtrDrive_Right_1;
				private final MotorController mtrDrive_Right_2;
				private final MotorController mtrDrive_Right_3;
	
				public Six(MotorController mtrDrive_Left_1, MotorController mtrDrive_Left_2,  MotorController mtrDrive_Left_3, 
				MotorController mtrDrive_Right_1, MotorController mtrDrive_Right_2, MotorController mtrDrive_Right_3) {
					this.mtrDrive_Left_1 = mtrDrive_Left_1;
					this.mtrDrive_Left_2 = mtrDrive_Left_2;
					this.mtrDrive_Left_3 = mtrDrive_Left_3;
					this.mtrDrive_Right_1 = mtrDrive_Right_1;
					this.mtrDrive_Right_2 = mtrDrive_Right_2;
					this.mtrDrive_Right_3 = mtrDrive_Right_3;
				}
	
				@Override public void update() {
					mtrDrive_Left_1.set(mPower_Left);
					mtrDrive_Left_2.set(mPower_Left);
					mtrDrive_Left_3.set(mPower_Left);
					mtrDrive_Right_1.set(mPower_Right);
					mtrDrive_Right_2.set(mPower_Right);
					mtrDrive_Right_3.set(mPower_Right);
				}
			}
	
			public abstract static class CTRE<MotorController extends BaseMotorController> extends Motors {
				private final MotorController mtrDrive_Left;
				private final MotorController mtrDrive_Right;
	
				protected CTRE(MotorController mtrDrive_Left, MotorController mtrDrive_Right) {
					this.mtrDrive_Left = mtrDrive_Left;
					this.mtrDrive_Right = mtrDrive_Right;
				}
	
				@Override public void update() {
					mtrDrive_Left.set(ControlMode.PercentOutput, mPower_Left);
					mtrDrive_Right.set(ControlMode.PercentOutput, mPower_Right);
				}
	
				public static class Two<MotorController extends BaseMotorController> extends CTRE<MotorController> {
					 public Two(MotorController mtrDrive_Left, MotorController mtrDrive_Right) { super(mtrDrive_Left, mtrDrive_Right); }
				}
				
				public static class Four<MasterController extends BaseMotorController, SlaveController extends BaseMotorController> extends CTRE<MasterController> {
					public Four(MasterController mtrDrive_LeftMaster, SlaveController mtrDrive_LeftSlave,
					MasterController mtrDrive_RightMaster, SlaveController mtrDrive_RightSlave) {
						super(mtrDrive_LeftMaster, mtrDrive_RightMaster);
						mtrDrive_LeftSlave.follow(mtrDrive_LeftMaster);
						mtrDrive_RightSlave.follow(mtrDrive_RightMaster);
					}
				}
				
				public static class Six<MasterController extends BaseMotorController, SlaveController extends BaseMotorController> extends CTRE<MasterController> {
					public Six(MasterController mtrDrive_LeftMaster, SlaveController mtrDrive_LeftSlave_1, SlaveController mtrDrive_LeftSlave_2,
					MasterController mtrDrive_RightMaster, SlaveController mtrDrive_RightSlave_1, SlaveController mtrDrive_RightSlave_2) {
						super(mtrDrive_LeftMaster, mtrDrive_RightMaster);
						mtrDrive_LeftSlave_1.follow(mtrDrive_LeftMaster);
						mtrDrive_LeftSlave_2.follow(mtrDrive_LeftMaster);
						mtrDrive_RightSlave_1.follow(mtrDrive_RightMaster);
						mtrDrive_RightSlave_2.follow(mtrDrive_RightMaster);
					}
				}
			}
		}
	
		public static class Shifter<SolenoidController extends SolenoidBase> implements SystemBase {
			private final SolenoidController solShifter;
			private final GearSpeed mGear_Default;
			private GearSpeed mGear_Current;
			public Shifter(SolenoidController solShifter, GearSpeed gear){ 
				this.solShifter = solShifter; 
				this.mGear_Default = gear;
				this.mGear_Current = gear;
			}
			public void set(GearSpeed gear) { mGear_Current = gear; }
			@Override public void update() {
				if(solShifter instanceof Solenoid) ((Solenoid) solShifter).set(mGear_Current != mGear_Default);
				else if(solShifter instanceof DoubleSolenoid) ((DoubleSolenoid) solShifter).set(mGear_Current != mGear_Default ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
			}
		}
	}

	protected final Subsystem.Motors sysMotors;
	protected final Subsystem.Shifter sysShifter;
	
	protected TankChassisBase() {
		sysMotors = null;
		sysShifter = null;
	}
//Two Motor, No Shifter
	protected <MotorController extends BaseMotorController> TankChassisBase(
	MotorController mtrDrive_Left, MotorController mtrDrive_Right) {
		this(
			mtrDrive_Left, mtrDrive_Right,
			(SolenoidBase) null, (GearSpeed) null);
	}
	protected <MotorController extends SpeedController> TankChassisBase(
	MotorController mtrDrive_Left, MotorController mtrDrive_Right) {
		this(
			mtrDrive_Left, mtrDrive_Right,
			(SolenoidBase) null, (GearSpeed) null);
	}
//Two Motor Generic
	protected <MotorController extends BaseMotorController, SolenoidController extends SolenoidBase> TankChassisBase(
	MotorController mtrDrive_Left, MotorController mtrDrive_Right,
	SolenoidController solShifter, GearSpeed gear){
		sysMotors = new Subsystem.Motors.CTRE.Two<MotorController>(mtrDrive_Left, mtrDrive_Right);
		sysShifter = new Subsystem.Shifter<SolenoidController>(solShifter, GearSpeed.kLow);
	}
	protected <MotorController extends SpeedController, SolenoidController extends SolenoidBase> TankChassisBase(
	MotorController mtrDrive_Left, MotorController mtrDrive_Right,
	SolenoidController solShifter, GearSpeed gear){
		sysMotors = new Subsystem.Motors.Two<MotorController>(mtrDrive_Left, mtrDrive_Right);
		sysShifter = new Subsystem.Shifter<SolenoidController>(solShifter, GearSpeed.kLow);
	}	
//Four Motor, No Shifter
	protected <MotorController extends BaseMotorController> TankChassisBase(
	MotorController mtrDrive_LeftMaster, MotorController mtrDrive_LeftSlave, 
	MotorController mtrDrive_RightMaster, MotorController mtrDrive_RightSlave) {
		this(
			mtrDrive_LeftMaster, mtrDrive_LeftSlave,
			mtrDrive_RightMaster, mtrDrive_RightSlave,
			(SolenoidBase) null, (GearSpeed) null);
	}
	protected <MotorController extends SpeedController> TankChassisBase(
		MotorController mtrDrive_Left_1, MotorController mtrDrive_Left_2, 
		MotorController mtrDrive_Right_1, MotorController mtrDrive_Right_2) {
			this(
				mtrDrive_Left_1, mtrDrive_Left_2,
				mtrDrive_Right_1, mtrDrive_Right_2,
				(SolenoidBase) null, (GearSpeed) null);
	}
//Four Motor Generic
	protected <MasterMotorController extends BaseMotorController, SlaveMotorController extends BaseMotorController, SolenoidController extends SolenoidBase> TankChassisBase(
		MasterMotorController mtrDrive_LeftMaster, SlaveMotorController mtrDrive_LeftSlave, 
		MasterMotorController mtrDrive_RightMaster, SlaveMotorController mtrDrive_RightSlave,
		SolenoidController solShifter, GearSpeed gear){
		sysMotors = new Subsystem.Motors.CTRE.Four<MasterMotorController, SlaveMotorController>(mtrDrive_LeftMaster, mtrDrive_LeftSlave, mtrDrive_RightMaster, mtrDrive_RightSlave);
		sysShifter = new Subsystem.Shifter<SolenoidController>(solShifter, GearSpeed.kLow);
	}
	protected <MotorController extends SpeedController, SolenoidController extends SolenoidBase> TankChassisBase(
		MotorController mtrDrive_Left_1, MotorController mtrDrive_Left_2, 
		MotorController mtrDrive_Right_1, MotorController mtrDrive_Right_2,
		SolenoidController solShifter, GearSpeed gear){
		sysMotors = new Subsystem.Motors.Four<MotorController>(mtrDrive_Left_1, mtrDrive_Left_2, mtrDrive_Right_1, mtrDrive_Right_2);
		sysShifter = new Subsystem.Shifter<SolenoidController>(solShifter, GearSpeed.kLow);
	}
		
//Six Motor, No Shifter
	protected <MasterMotorController extends BaseMotorController, SlaveMotorController extends BaseMotorController> TankChassisBase(
		MasterMotorController mtrDrive_LeftMaster, SlaveMotorController mtrDrive_LeftSlave_1,  SlaveMotorController mtrDrive_LeftSlave_2, 
		MasterMotorController mtrDrive_RightMaster, SlaveMotorController mtrDrive_RightSlave_1, SlaveMotorController mtrDrive_RightSlave_2) {
		this(
			mtrDrive_LeftMaster, mtrDrive_LeftSlave_1, mtrDrive_LeftSlave_2,
			mtrDrive_RightMaster, mtrDrive_RightSlave_1, mtrDrive_RightSlave_2,
			(SolenoidBase) null, (GearSpeed) null);
	}
	protected <MotorController extends SpeedController> TankChassisBase(
	MotorController mtrDrive_Left_1, MotorController mtrDrive_Left_2,  MotorController mtrDrive_Left_3, 
	MotorController mtrDrive_Right_1, MotorController mtrDrive_Right_2, MotorController mtrDrive_Right_3) {
		this(
			mtrDrive_Left_1, mtrDrive_Left_2, mtrDrive_Left_2,
			mtrDrive_Right_1, mtrDrive_Right_2, mtrDrive_Right_3,
			(SolenoidBase) null, (GearSpeed) null);
	}
//Six Motor Generic
	protected <MasterMotorController extends BaseMotorController, SlaveMotorController extends BaseMotorController, SolenoidController extends SolenoidBase> TankChassisBase(
		MasterMotorController mtrDrive_LeftMaster, SlaveMotorController mtrDrive_LeftSlave_1,  SlaveMotorController mtrDrive_LeftSlave_2, 
		MasterMotorController mtrDrive_RightMaster, SlaveMotorController mtrDrive_RightSlave_1, SlaveMotorController mtrDrive_RightSlave_2,
		SolenoidController solShifter, GearSpeed gear){
		sysMotors = new Subsystem.Motors.CTRE.Six<MasterMotorController, SlaveMotorController>(mtrDrive_LeftMaster, mtrDrive_LeftSlave_1, mtrDrive_LeftSlave_2, mtrDrive_RightMaster, mtrDrive_RightSlave_1, mtrDrive_RightSlave_2);
		sysShifter = new Subsystem.Shifter<SolenoidController>(solShifter, GearSpeed.kLow);
	}
	protected <MotorController extends SpeedController, SolenoidController extends SolenoidBase> TankChassisBase(
		MotorController mtrDrive_Left_1, MotorController mtrDrive_Left_2,  MotorController mtrDrive_Left_3, 
		MotorController mtrDrive_Right_1, MotorController mtrDrive_Right_2, MotorController mtrDrive_Right_3, 
		SolenoidController solShifter, GearSpeed gear){
		sysMotors = new Subsystem.Motors.Six<MotorController>(mtrDrive_Left_1, mtrDrive_Left_2, mtrDrive_Left_3, mtrDrive_Right_1, mtrDrive_Right_2, mtrDrive_Right_3);
		sysShifter = new Subsystem.Shifter<SolenoidController>(solShifter, GearSpeed.kLow);
	}

	@Override public abstract void initialize();

	public void setDrive(double left, double right) { if(sysMotors != null) sysMotors.set(left, right); }
	public void setGear(GearSpeed gear) { if(sysShifter != null) sysShifter.set(gear); }

	@Override public void update() {
		sysMotors.update();
		sysShifter.update();
	}
}