
package com.woyaofa.util;

import java.util.Date;

public class DateTime {

	public static boolean isInRange (Date start, Date end, Date value) {

		if (value == null) {
			return false;
		} else {
			return isInRange(start, end, value.getTime());
		}
	}

	public static boolean isInRange (Date start, Date end, long value) {

		if (start != null) {
			long s = start.getTime();

			if (s > value) {
				return false;
			}
		}

		if (end != null) {
			long e = end.getTime();

			if (e < value) {
				return false;
			}
		}

		return true;
	}
}
