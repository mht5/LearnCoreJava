package chapter09.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * test map
 * @author mhts
 * @date 2018年6月20日
 */
public class MapTest {

	public static void main(String[] args) {
		/**
		 * HashMap
		 */
		Map<String, Employee> staff = new HashMap<>();
		staff.put("3", new Employee("sam", 12000));
		staff.put("2", new Employee("mike", 16000));
		staff.put("1", new Employee("jack", 20000));
		Employee emp = staff.put("4", new Employee("saul", 8000));
		System.out.println(emp);
//		put()返回这个键存储的上一个值
		emp = staff.put("4", new Employee("aron", 16000));
		System.out.println(emp);
		
		System.out.println(staff);
		
		staff.remove("3");
		System.out.println(staff);
		
		staff.replace("2", new Employee("judy", 12000));
		System.out.println(staff.get("2"));
		System.out.println(staff.getOrDefault("5", new Employee("beth", 8000)));
		
		staff.forEach((k, v) -> System.out.println("key=" + k + ", value=" + v));
		
		/**
		 * LinkedHashMap
		 */
		Map<String, Employee> staff1 = new LinkedHashMap<>();
		staff1.put("3", new Employee("sam", 12000));
		staff1.put("2", new Employee("mike", 16000));
		staff1.put("1", new Employee("jack", 20000));
		staff1.put("4", new Employee("saul", 8000));
		Iterator<String> iter1 = staff1.keySet().iterator();
		while (iter1.hasNext()) {
			System.out.println(iter1.next());
		}
		Iterator<Employee> iter2 = staff1.values().iterator();
		while (iter2.hasNext()) {
			System.out.println(iter2.next());
		}
	}

}
