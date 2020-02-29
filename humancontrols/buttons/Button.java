package frc.molib.humancontrols.buttons;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj.buttons.Trigger.ButtonScheduler;

/**
 * A non-command based Button class. 
 * <p>Simply sub-class this and override the {@link #get()} method to determine how the button value is read</p>
 * @see edu.wpi.first.wpilibj.buttons.Button
 */
public class Button implements Sendable {
	protected volatile boolean m_sendablePressed;
	private static int instances;

	protected boolean lastPressed = grab();
	protected boolean lastReleased = !grab();

	public Button() { this(""); }
	public Button(String label) {
		instances++;
		SendableRegistry.addLW(this, "Button " + label, instances);
		new ButtonScheduler() {
			@Override public void execute() {
				if(grab() && !lastPressed) lastPressed = true;
				if(!grab() && !lastReleased) lastReleased = true;
			}
		}.start();
	}
	
	public boolean get() { return m_sendablePressed; }
	public final boolean getPressed() {
		boolean currentPressed = lastPressed;
		lastPressed = false;
		return currentPressed;
	}
	public final boolean getReleased() {
		boolean currentReleased = lastReleased;
		lastReleased = false;
		return currentReleased;
	}

	private boolean grab() { return get() || m_sendablePressed; }

	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("MOLib Button");
		builder.setSafeState(() -> m_sendablePressed = false);
		builder.addBooleanProperty("pressed", this::grab, value -> m_sendablePressed = value);
	}
}