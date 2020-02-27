package frc.molib.humancontrols;

class ControlsMath {
	private ControlsMath() { throw new AssertionError("Utitily Class"); }
	public static double deaden(double value, double deadzoneThreshold) {
		if (Math.abs(value) < deadzoneThreshold)
			return 0.0;
		else if (value < 0.0)
			return (value + deadzoneThreshold) / (1.0 - deadzoneThreshold);
		else
			return (value - deadzoneThreshold) / (1.0 - deadzoneThreshold);
	}
}