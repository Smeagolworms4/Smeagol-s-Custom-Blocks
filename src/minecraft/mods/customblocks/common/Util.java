package mods.customblocks.common;

public class Util {
	
	public static String toString (Object o) {
		if (o != null) {
			return o.toString();
		}
		return "";
	}
	
	public static int toInt (Object o) {
		return Integer.parseInt(toString (o));
	}
}
