package volume01.chapter08.pair1;

import volume01.chapter08.Pair;

/**
 * test generic type
 * @author mhts
 * @date 2018Äê6ÔÂ19ÈÕ
 */
public class PairTest1 {
	public static void main(String[] args) {
		String[] names = {"jack", "mike", "sam", "tom"};
		Pair<String> minmax = ArrayAlg.minmax(names);
		System.out.println("min=" + minmax.getFirst());
		System.out.println("max=" + minmax.getSecond());
	}
}
