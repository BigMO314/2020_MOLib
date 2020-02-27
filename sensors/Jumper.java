package frc.molib.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * A small class derived from DigitalInput to represent a Jumper.
 * <p>Typically used to represent different robots: one should have a jumper plugged in, the other would not.</p>
 * <p>Reverses the value of the {@link #get()} method to appropriately represent whether the Jumper is present or not.</p>
 * @see edu.wpi.first.wpilibj.DigitalInput
 */
public class Jumper extends DigitalInput{
	/**
	 * Constructor
	 * @param channel The DIO channel for the Jumper.
	 */
	public Jumper(int channel) { super(channel); }
	
	@Override public boolean get() { return !super.get(); }
}
