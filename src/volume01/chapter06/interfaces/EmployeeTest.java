package volume01.chapter06.interfaces;

import java.util.Arrays;

/**
 * test Arrays.sort()
 * @author mhts
 * @date 2018Äê6ÔÂ18ÈÕ
 */
public class EmployeeTest {
	public static void main(String[] args) {
		Employee[] staff = new Employee[3];
		staff[0] = new Employee("jack", 20000);
		staff[1] = new Employee("mike", 16000);
		staff[2] = new Employee("sam", 12000);
		Arrays.sort(staff);
		for(Employee emp : staff) {
			System.out.println(emp);
		}
	}
}
