package chapter08.pair3;

import chapter08.Pair;

/**
 * swap the value of wildcard type and  generic type
 * @author mhts
 * @date 2018Äê6ÔÂ19ÈÕ
 */
public class PairAlg {
	
	public static boolean hasNulls(Pair<?> p) {
		return p.getFirst() == null || p.getSecond() == null;
	}
	
	public static void swap(Pair<?> p) {
		swapHelper(p);
	}
	
	public static <T> void swapHelper(Pair<T> p) {
		T t = p.getFirst();
		p.setFirst(p.getSecond());
		p.setSecond(t);
	}
}
