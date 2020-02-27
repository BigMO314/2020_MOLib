package frc.molib.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSwitch extends DigitalInput {
	public LimitSwitch(int channel) { super(channel); }

	@Override
	public boolean get() { return !super.get(); }
}