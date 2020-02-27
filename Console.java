package frc.molib;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for writing to the Driver Station Console.
 */
public class Console {
	private Console() { throw new AssertionError("Utility Class"); }
	private static String getTimeStamp() { return new SimpleDateFormat("HH.mm.ss").format(new Date()); }
	public static void logMsg(String message) { System.out.println("[Log] [" + getTimeStamp() + "] " + message); }
	public static void logErr(String message) { System.err.println("[Err] [" + getTimeStamp() + "] " + message); }
}