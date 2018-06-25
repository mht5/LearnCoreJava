package volume01.chapter03;

import java.util.Date;
import java.util.Scanner;

/**
 * first in a long list
 * @author mhts
 * @date 2018Äê6ÔÂ15ÈÕ
 */
public class InputTest {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Please input your name: ");
		String name = in.next();
		System.out.print("Please input your age: ");
		int age = in.nextInt();
		System.out.println("name = " + name + ", age = " + age);
		in.close();

		System.out.printf("%1$s %2$tB %2$te, %2$tY\n", "Current date: ", new Date());
		System.out.printf("%s %tB %<te, %<tY\n", "Current date: ", new Date());
	}
}
