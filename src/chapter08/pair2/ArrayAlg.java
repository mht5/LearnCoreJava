package chapter08.pair2;

import chapter08.Pair;

/**
 * generic function with restriction
 * @author mhts
 * @date 2018��6��19��
 */
public class ArrayAlg {
	/**
	 * ���Ʒ�����Ҫʵ��Comparable�ӿ���ʹ��compateTo()
	 * @param a
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T extends Comparable> Pair<T> minmax(T[] a) {
		if (a == null || a.length == 0) {
			return null;
		}
		T min = a[0];
		T max = a[0];
		for (int i = 1; i < a.length; i++) {
			T s = a[i];
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
