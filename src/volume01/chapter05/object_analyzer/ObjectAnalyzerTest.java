package volume01.chapter05.object_analyzer;

import java.util.ArrayList;

import volume01.chapter05.equals.Employee;

/**
 * test object analyzer
 * @author mhts
 * @date 2018Äê6ÔÂ18ÈÕ
 */
public class ObjectAnalyzerTest {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i =1; i <= 5; i++) {
			list.add(i * i);
		}
		System.out.println(ObjectAnalyzer.toString(list));
		System.out.println(ObjectAnalyzer.toString(new Employee("jack", 5000, 2010, 2, 2)));
	}
}
