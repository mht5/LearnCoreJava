package chapter05.array_list;

import java.util.ArrayList;

import chapter05.equals.Employee;

/**
 * test array list
 * @author mhts
 * @date 2018Äê6ÔÂ17ÈÕ
 */
public class ArrayListTest {

	public static void main(String[] args) {
		ArrayList<Employee> staff = new ArrayList<>();
		staff.add(new Employee("jack", 20000, 2010, 6, 12));
		staff.add(new Employee("mike", 10000, 2017, 12, 12));
		staff.add(new Employee("sam", 32000, 2008, 4, 6));
		
		for (Employee emp : staff) {
			emp.raiseSalary(20);
			System.out.println(emp);
		}
	}

}
