package volume01.chapter03;

/**
 * 可以通过特定标签跳出多重循环，该标签必须放在希望跳出的最外层循环之前
 * @author mhts
 * @date 2018年6月15日
 */
public class BreakTest {
	public static void main(String[] args) {
		int n = 0;
		System.out.println("Before loop");
		get_out:
		while (n < 5) {
			for (int i = 0; i < 10; i++) {
				System.out.println(i*i);
				if (i > 5) {
//					break;
					break get_out;
				}
			}
			n++;
		}
		System.out.println("After loop");
	}
}
