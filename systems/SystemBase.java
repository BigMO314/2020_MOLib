package frc.molib.systems;

public interface SystemBase {
	public default void initialize() {};
	public void update();
}