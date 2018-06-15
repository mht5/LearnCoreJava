package chapter03;

import java.util.Scanner;

/**
 * first in a long list
 * @author mhts
 * @date 2018Äê6ÔÂ15ÈÕ
 */
public class InputTest {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.print("Please input your name: ");
		String name = in.next();
		System.out.print("Please input your age: ");
		int age = in.nextInt();
		System.out.println("name = " + name + ", age = " + age);
	}
}
