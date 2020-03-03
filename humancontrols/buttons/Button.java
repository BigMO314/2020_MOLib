package frc.molib.humancontrols.buttons;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/**
 * A non-command based Button class. 
 * <p>Sub-class this and override the {@link #get()} method to determine how the button value is read.</p>
 * @see edu.wpi.first.wpilibj.buttons.Button
 */
public class Button implements Sendable {
	protected volatile boolean m_sendableValue;
	private static int instances;

	protected boolean lastPressed = grab();
	protected boolean lastReleased = !grab();

	protected boolean pressable = !grab();
	protected boolean releasable = grab();

	/**
	 * Constructor
	 * <p>Uses the default method of naming the Button</p>
	 */
	public Button() { 
		instances++;
		SendableRegistry.addLW(this, "Button[" + instances + "]");
		ButtonScheduler.getInstance().addButton(this);
	}

	/**
	 * Constructor
	 * @param name Button name as it appears on the table.
	 */
	public Button(String name) {
		SendableRegistry.addLW(this, "Button[" + name + "]");
		ButtonScheduler.getInstance().addButton(this);
	}

	/**
	 * Constructor
	 * @param subsystem Subtable name containing the button.
	 * @param name Button name as it appears in the table.
	 */
	public Button(String subsystem, String name) {
		SendableRegistry.addLW(this, subsystem, "Button[" + name + "]");
		ButtonScheduler.getInstance().addButton(this);
	}
	
	/**
	 * Default implementation simply returns the LiveWindow 'value' property. Override this method to change how the value is read.
	 * @return True when the LiveWindow 'value' reads true.
	 */
	public boolean get() { return m_sendableValue; }

	/**
	 * Check if the button has bee pressed.
	 * @return True if the button has been pressed since the last time this method was called.
	 */
	public final boolean getPressed() {
		boolean currentPressed = lastPressed;
		lastPressed = false;
		return currentPressed;
	}

	/**
	 * Check if the button has been released.
	 * @return True if the button has been released since the last time this method was called.
	 */
	public final boolean getReleased() {
		boolean currentReleased = lastReleased;
		lastReleased = false;
		return currentReleased;
	}

	private boolean grab() { return get() || m_sendableValue; }

	protected void update() {
		if(!grab()) pressable = true;
		else releasable = true;

		if(grab() && !lastPressed && pressable) { lastPressed = true; pressable = false; }
		if(!grab() && !lastReleased && releasable) { lastReleased = true; releasable = false; }
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSafeState(() -> m_sendableValue = false);
		builder.addBooleanProperty("value", this::grab, value -> m_sendableValue = value);
		builder.addBooleanProperty("pressed", () -> lastPressed, null);
		builder.addBooleanProperty("released", () -> lastReleased, null);
	}
}