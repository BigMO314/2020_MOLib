package frc.molib.vision;

import frc.molib.DashTable;
import frc.molib.DashTable.DashEntry;

/**
 * Interface for the Limelight table on NetworkTables
 */
public final class Limelight {
	public enum LEDMode {
		kDefault(0),
		kOff(1),
		kBlink(2),
		kOn(3);

		public final int value;
		private LEDMode(int value) { this.value = value; }
	}

	public enum CamMode {
		kVisionProcessor(0),
		kDriverCam(1);
		
		public final int value;
		private CamMode(int value) { this.value = value; }
	}

	public enum StreamMode {
		kStandard(0),
		kPrimaryPiP(1),
		kSecondaryPiP(2);

		public final int value;
		private StreamMode(int value){ this.value = value; }
	}
	
	private static final DashTable TABLE = new DashTable("limelight");
	private static final Limelight INSTANCE = new Limelight();

	public static final DashEntry<Boolean>	dshHasTarget = TABLE.new DashEntry<Boolean>("tv");
	public static final DashEntry<Double> 	dshPosX = TABLE.new DashEntry<Double>("tx");
	public static final DashEntry<Double> 	dshPosY = TABLE.new DashEntry<Double>("ty");
	public static final DashEntry<Integer> 	dshWidth = TABLE.new DashEntry<Integer>("thor");
	public static final DashEntry<Integer> 	dshHeight = TABLE.new DashEntry<Integer>("tver");
	public static final DashEntry<Double> 	dshArea = TABLE.new DashEntry<Double>("ta");
	
	public static final DashEntry<Integer>	dshLEDMode = TABLE.new DashEntry<Integer>("ledMode");
	public static final DashEntry<Integer>	dshCamMode = TABLE.new DashEntry<Integer>("camMode");
	public static final DashEntry<Integer>	dshPipeline = TABLE.new DashEntry<Integer>("pipeline");
	public static final DashEntry<Integer>	dshStreamMode = TABLE.new DashEntry<Integer>("stream");

	private Limelight() {

	}

	public static Limelight getInstance() { return INSTANCE; }

	public static boolean hasTarget() { return dshHasTarget.get(); }
	public static double getPosX() { return dshPosX.get(); }
	public static double getPosY() { return dshPosY.get(); }
	public static int getWidth() { return dshWidth.get(); }
	public static int getHeight() { return dshHeight.get(); }
	public static double getArea() { return dshArea.get(); }
	
	public static void setLEDMode(LEDMode mode) { dshLEDMode.set(mode.value); }
	public static void setCamMode(CamMode mode) { dshCamMode.set(mode.value); }
	public static void setPipeline(int pipeline) { dshPipeline.set(pipeline); }
	public static void setStream(StreamMode mode) { dshStreamMode.set(mode.value); }

	public static LEDMode getLEDMode() { return LEDMode.values()[dshLEDMode.get()]; }
	public static CamMode getCamMode() { return CamMode.values()[dshCamMode.get()]; }
	public static int getPipeline() { return dshPipeline.get(); }
	public static StreamMode getStreamMode() { return StreamMode.values()[dshStreamMode.get()]; }	
}
