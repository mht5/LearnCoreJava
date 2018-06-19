package chapter07.stack_trace;

import java.util.Scanner;

/**
 * test stack trace
 * @author mhts
 * @date 2018Äê6ÔÂ19ÈÕ
 */
public class StackTraceTest {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a number: ");
		int result = factorial(in.nextInt());
		System.out.println(result);
		in.close();
	}
	
	public static int factorial(int num) {
		System.out.println("factorial(" + num + ")");
		Throwable t = new Throwable();
		StackTraceElement[] frames = t.getStackTrace();
		for (StackTraceElement frame : frames) {
			System.out.println(frame);
		}
		int result;
		if (num <= 1) {
			result = 1;
		} else {
			result = num * factorial(num - 1);
		}
		System.out.println("return " + result);
		return result;
	}

}
