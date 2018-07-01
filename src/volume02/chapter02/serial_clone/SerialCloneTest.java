package volume02.chapter02.serial_clone;

/**
 * test serial clone
 * @author mhts
 * @date 2018Äê7ÔÂ1ÈÕ
 */
public class SerialCloneTest {

	public static void main(String[] args) throws CloneNotSupportedException {
		Employee jack = new Employee("jack", 20000, 2010, 10, 12);
		Employee jack2 = (Employee) jack.clone();
		
		jack.raiseSalary(20);
		
		System.out.println(jack);
		System.out.println(jack2);
	}

}
