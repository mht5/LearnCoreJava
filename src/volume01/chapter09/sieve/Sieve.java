package volume01.chapter09.sieve;

import java.util.BitSet;

/**
 * find the prime numbers in a certain range, this is just to test BitSet
 * @author mhts
 * @date 2018Äê6ÔÂ21ÈÕ
 */
public class Sieve {

	public static void main(String[] args) {
		int n = 2000000;
		long start = System.currentTimeMillis();
		BitSet b = new BitSet(n + 1);
		int count = 0;
		int i;
		for (i = 2; i <= n; i++) {
			b.set(i);
		}
		i = 2;
		while (i * i <= n) {
			if (b.get(i)) {
				count++;
				int k = 2 * i;
				while (k <= n) {
					b.clear(k);
					k += i;
				}
			}
			i++;
		}
		while (i <= n) {
			if (b.get(i)) {
				count++;
			}
			i++;
		}
		System.out.println(count + " prime numbers found.");
		System.out.println("Total time: " + (System.currentTimeMillis() - start) + "ms");
	}

}
