package chapter05.inheritance;

/**
 * inheritance test
 * @author mhts
 * @date 2018Äê6ÔÂ17ÈÕ
 */
public class ManagerTest {

	public static void main(String[] args) {
		Manager manager = new Manager("jack", 80000, 2010, 12, 15);
		manager.setBonus(20000);
		
		Employee[] staff = new Employee[3];
		staff[0] = manager;
		staff[1] = new Employee("mike", 50000, 2015, 1, 3);
		staff[2] = new Employee("judy", 60000, 2014, 6, 12);
		
		for (Employee e : staff) {
			System.out.println(e.getName() + "'s salary is " + e.getSalary());
		}
	}

}
