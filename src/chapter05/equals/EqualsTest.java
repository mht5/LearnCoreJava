package chapter05.equals;

/**
 * test equals(), hashCode() and toString()
 * @author mhts
 * @date 2018Äê6ÔÂ17ÈÕ
 */
public class EqualsTest {
	public static void main(String[] args) {
		Employee emp1 = new Employee("jack", 20000, 2010, 6, 12);
		Employee emp2 = emp1;
		Employee emp3 = new Employee("jack", 20000, 2010, 6, 12);
		Employee mike = new Employee("mike", 10000, 2017, 12, 12);
		
		System.out.println("emp1==emp2: " + (emp1==emp2));
		System.out.println("emp1==emp3: " + (emp1==emp3));
		System.out.println("emp1.equals(emp3): " + emp1.equals(emp3));
		System.out.println("emp1.equals(mike): " + emp1.equals(mike));
		
		System.out.println("mike.toString(): " + mike);
		
		Manager manager1 = new Manager("sam", 32000, 2008, 4, 6);
		Manager manager2 = new Manager("sam", 32000, 2008, 4, 6);
		manager2.setBonus(10000);
		
		System.out.println("manager2.toString(): " + manager2);
		System.out.println("manager1.equals(manager2): " + manager1.equals(manager2));
		
		System.out.println("emp1.hashCode(): " + emp1.hashCode());
		System.out.println("emp3.hashCode(): " + emp3.hashCode());
		System.out.println("mike.hashCode(): " + mike.hashCode());
		System.out.println("manager1.hashCode: " + manager1.hashCode());
	}
}
