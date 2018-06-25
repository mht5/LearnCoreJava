package volume01.chapter05.enums;

import java.util.Scanner;

/**
 * test enum
 * @author mhts
 * @date 2018Äê6ÔÂ17ÈÕ
 */
public class EnumTest {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a size: (SMALL, MEDIUM, LARGE, EXTRA_LARGE) ");
		String input = in.next().toUpperCase();
		Size size = Enum.valueOf(Size.class, input);
		System.out.println("Size=" + size);
		System.out.println("abbreviation=" + size.getAbbreviation());
		if (size == Size.EXTRA_LARGE) {
			System.out.println("extra large");
		}
		System.out.println(Size.MEDIUM.ordinal());
		in.close();
	}
}
