package frc.molib.humancontrols.axes;

import edu.wpi.first.wpilibj.GenericHID;

public class BasicAxis {
	private final GenericHID ctlSource;
	private final int axisNumber;
	private boolean isInverted = false;

	public BasicAxis(GenericHID ctlSource, int axisNumber) {
		this.ctlSource = ctlSource;
		this.axisNumber = axisNumber;
	}

	public void configInverted(boolean isInverted) { this.isInverted = isInverted; }

	public double get() { 
		if(isInverted) return -ctlSource.getRawAxis(axisNumber);
		else return ctlSource.getRawAxis(axisNumber);
	}
}