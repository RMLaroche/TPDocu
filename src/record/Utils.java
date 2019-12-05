package record;

public class Utils {
	public static void sysPrint(String str) {
		if (HardwareStore.myDebug) {
			System.out.println(str);
		}
	}

}
