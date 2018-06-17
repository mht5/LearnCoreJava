package chapter05.abstract_class;


/**
 * abstract class test
 * @author mhts
 * @date 2018Äê6ÔÂ17ÈÕ
 */
public class PersonTest {
	public static void main(String[] args) {
		Person[] people = new Person[2];
		people[0] = new Employee("jack", 20000, 2010, 6, 12);
		people[1] = new Student("mike", "history");
		for (Person person : people) {
			System.out.println(person.getDescription());
		}
	}
}
