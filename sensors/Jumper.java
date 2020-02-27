package frc.molib.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

public class Jumper extends DigitalInput{
	public Jumper(int channel) { super(channel); }
	
	@Override
	public boolean get() { return !super.get(); }
}
