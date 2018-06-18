package chapter06.clone;

/**
 * test clone
 * @author mhts
 * @date 2018Äê6ÔÂ18ÈÕ
 */
public class CloneTest {

	public static void main(String[] args) {
		try {
			Employee original = new Employee("jack", 20000);
			Employee copy = original.clone();
			copy.raiseSalary(20);
			copy.setHireDate();
			System.out.println("original=" + original);
			System.out.println("copy=" + copy);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

}
