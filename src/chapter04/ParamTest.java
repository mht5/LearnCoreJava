package chapter04;

/**
 * 
 * @author mhts
 * @date 2018Äê6ÔÂ17ÈÕ
 */
public class ParamTest {
	public static void main(String[] args) {
		/**
		 * methods can not modify numeric parameters
		 */
		System.out.println("Testing tripleValue: ");
		double percent = 10;
		System.out.println("Before, percent=" + percent);
		tripleValue(percent);
		System.out.println("After, percent=" + percent);
		
		/**
		 * method can change the state of object parameters
		 */
		System.out.println("Testing tripleSalary:");
		Employee jack = new Employee("jack", 100);
		System.out.println("Before, salary=" + jack.getSalary());
		tripleSalary(jack);
		System.out.println("After, salary=" + jack.getSalary());
		
		/**
		 * methods can not attach new objects to object parameters
		 */
		Employee mike = new Employee("mike", 200);
		System.out.println("Before, jack=" + jack.getName());
		System.out.println("Before, mike=" + mike.getName());
		swap(jack, mike);
		System.out.println("After, jack=" + jack.getName());
		System.out.println("After, mike=" + mike.getName());
	}
	
	public static void tripleValue(double x) {
		x *= 3;
		System.out.println("End of method, x=" + x);
	}
	
	public static void tripleSalary(Employee x) {
		x.raiseSalary(200);
		System.out.println("End of method, salary=" + x.getSalary());
	}
	
	public static void swap(Employee x, Employee y) {
		Employee tmp = x;
		x = y;
		y = tmp;
		System.out.println("End of method, x=" + x.getName());
		System.out.println("End of method, y=" + y.getName());
	}
}
