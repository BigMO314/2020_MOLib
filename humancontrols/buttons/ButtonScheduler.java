package frc.molib.humancontrols.buttons;

import java.util.Vector;

public final class ButtonScheduler {
	private Vector<Button> buttonList = new Vector<Button>();
	private boolean enabled = true;

	private static ButtonScheduler instance;
	public static ButtonScheduler getInstance() {
		if (instance == null) instance = new ButtonScheduler();
		return instance;
	}

	private ButtonScheduler() {

	}

	public void enable() { enabled = true; }
	public void disable() { enabled = false; }

	protected void addButton(Button button) { buttonList.addElement(button); }
	protected void removeButton(Button button) { buttonList.remove(button); }
	public void removeAll() { buttonList.clear(); }

	public void update() {
		if (!enabled) return;
		for (Button btnTemp : buttonList)
			btnTemp.update();
	}
}