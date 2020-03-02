package frc.molib.humancontrols.buttons;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/**
 * A non-command based Button class. 
 * <p>Simply sub-class this and override the {@link #get()} method to determine how the button value is read</p>
 * @see edu.wpi.first.wpilibj.buttons.Button
 */
public class Button implements Sendable {
	protected volatile boolean m_sendableValue;
	private static int instances;

	protected boolean lastPressed = grab();
	protected boolean lastReleased = !grab();

	public Button() { 
		instances++;
		SendableRegistry.addLW(this, "Button", instances);
		ButtonScheduler.getInstance().addButton(this);
	}

	public Button(String name) {
		SendableRegistry.addLW(this, "Button[" + name + "]");
		ButtonScheduler.getInstance().addButton(this);
	}

	public Button(String subsystem, String name) {
		SendableRegistry.addLW(this, subsystem, "Button[" + name + "]");
		ButtonScheduler.getInstance().addButton(this);
	}
	
	/**
	 * Default implementation simply returns the LiveWindow 'pressed' value. Override this method to change how the value is read.
	 * @return Whether the button reads pressed or not.
	 */
	public boolean get() { return m_sendableValue; }

	/**
	 * 
	 * @return Whether the button has been pressed since the last time this method was called.
	 */
	public final boolean getPressed() {
		boolean currentPressed = lastPressed;
		lastPressed = false;
		return currentPressed;
	}

	/**
	 * 
	 * @return Whether the button has been released since the last time this method was called.
	 */
	public final boolean getReleased() {
		boolean currentReleased = lastReleased;
		lastReleased = false;
		return currentReleased;
	}

	private boolean grab() { return get() || m_sendableValue; }

	protected void update() {
		if(grab() && !lastPressed) lastPressed = true;
		if(!grab() && !lastReleased) lastReleased = true;
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSafeState(() -> m_sendableValue = false);
		builder.addBooleanProperty("value", this::grab, value -> m_sendableValue = value);
		builder.addBooleanProperty("pressed", () -> lastPressed, null);
		builder.addBooleanProperty("released", () -> lastReleased, null);
	}
}