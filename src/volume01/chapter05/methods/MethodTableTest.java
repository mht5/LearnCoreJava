package volume01.chapter05.methods;

import java.lang.reflect.Method;

/**
 * test invoking methods through reflection
 * @author mhts
 * @date 2018Äê6ÔÂ18ÈÕ
 */
public class MethodTableTest {
	public static void main(String[] args) {
		try {
			Method square = MethodTableTest.class.getMethod("square", double.class);
			Method sqrt = Math.class.getMethod("sqrt", double.class);
			
			printTable(1, 10, 10, square);
			printTable(1, 10, 10, sqrt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static double square(double x) {
		return x * x;
	}
	
	public static void printTable(double from, double to, int n, Method f) {
		System.out.println(f);
		double dx = (to - from) / (n - 1);
		for (double i = from; i <= to; i+=dx) {
			try {
				double j = (Double) f.invoke(null, i);
				System.out.printf("%10.4f | %10.4f%n", i, j);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
