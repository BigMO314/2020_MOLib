package frc.molib;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * A wrapper class on the WPI NetworkTable class.
 * <p>Provides a more streamlined and simplified interface</p>
 * @see edu.wpi.first.networktables.NetworkTable
 */
public class DashTable {
	private final NetworkTable table;
	public DashTable(String key) { 
		this.table = NetworkTableInstance.getDefault().getTable(key); 
	}
	public final void clear() { clear(table); }
	private final void clear(NetworkTable table) { 
		for(String key : table.getKeys()) table.delete(key); 
		for(String key : table.getSubTables()) clear(table.getSubTable(key));
	}

	public class SubTable extends DashTable {
		public SubTable(String key) { super(table.getPath() + "/" + key); }
	}

	/**
	 * An entry pulled from the parent DashTable class.
	 * @param <V> Type of value the entry holds.
	 * @see edu.wpi.first.networktables.NetworkTableEntry
	 */
	public final class DashEntry<V> { 
		private final NetworkTableEntry entry;

		public DashEntry(String key) {
			this.entry = table.getEntry(key);
		}

		public DashEntry(String key, V defaultValue) { 
			this.entry = table.getEntry(key);
			this.set(defaultValue);
		}

		@SuppressWarnings("unchecked")
		public V get() { return (V) entry.getValue().getValue(); }
		public void set(V value) { entry.setValue(value); }
		public final void delete() { entry.delete(); }
	}
}
