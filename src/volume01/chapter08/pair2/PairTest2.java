package volume01.chapter08.pair2;

import java.time.LocalDate;

import volume01.chapter08.Pair;

/**
 * test generic type with restriction
 * @author mhts
 * @date 2018Äê6ÔÂ19ÈÕ
 */
public class PairTest2 {
	public static void main(String[] args) {
		LocalDate[] birthdays = {
				LocalDate.of(2014, 8, 20),
				LocalDate.of(2016, 7, 23),
				LocalDate.of(2010, 12, 12),
				LocalDate.of(2012, 2, 2),
		};
		Pair<LocalDate> mm = ArrayAlg.minmax(birthdays);
		System.out.println("min=" + mm.getFirst());
		System.out.println("max=" + mm.getSecond());
	}
}
