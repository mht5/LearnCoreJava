package chapter05.abstract_class;

/**
 * subclass of an abstract class
 * @author mhts
 * @date 2018��6��17��
 */
public class Student extends Person{
	private String major;

	public Student(String name, String major) {
		super(name);
		this.major = major;
	}
	
	public String getDescription() {
		return "a student majoring in " + major;
	}

}
