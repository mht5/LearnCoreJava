package chapter09.shuffle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * test shuffle
 * @author mhts
 * @date 2018Äê6ÔÂ21ÈÕ
 */
public class ShuffleTest {
	public static void main(String[] args) {
		List<Integer> nums = new ArrayList<>();
		for ( int i = 0; i < 49; i++) {
			nums.add(i);
		}
		Collections.shuffle(nums);
		List<Integer> result = nums.subList(0, 6);
		Collections.sort(result);
		System.out.println(result);
	}
}
