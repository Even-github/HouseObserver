package per.even.HouseObserver.utils;

import java.text.NumberFormat;

public class NumberFormatter {
	public static String doubleFormat(Double d) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		return nf.format(d);
	}
}
