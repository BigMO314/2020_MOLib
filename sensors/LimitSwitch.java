package frc.molib.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * A small class derived from DigitalInput to represent a Limit Switch.
 * <p>Reverses the value of the {@link #get()} method to appropriately represent whether the switch is tripped or not.</p>
 * @see edu.wpi.first.wpilibj.DigitalInput
 */
public class LimitSwitch extends DigitalInput {
	/**
	* Constructor
	* @param channel The DIO channel for the Limit Switch.
	*/
	public LimitSwitch(int channel) { super(channel); }

	@Override public boolean get() { return !super.get(); }
}