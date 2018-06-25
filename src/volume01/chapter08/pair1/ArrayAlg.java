package volume01.chapter08.pair1;

import volume01.chapter08.Pair;

/**
 * find the min and max value in a string array
 * @author mhts
 * @date 2018Äê6ÔÂ19ÈÕ
 */
public class ArrayAlg {
	public static Pair<String> minmax(String[] a) {
		if (a == null || a.length == 0) {
			return null;
		}
		String min = a[0];
		String max = a[0];
		for (int i = 1; i < a.length; i++) {
			String s = a[i];
			if (min.compareTo(s) > 0) {
				min = s;
			}
			if (max.compareTo(s) < 0) {
				max = s;
			}
		}
		return new Pair<>(min, max);
	}
}
