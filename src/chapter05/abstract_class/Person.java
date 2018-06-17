package chapter05.abstract_class;

/**
 * abstract class
 * @author mhts
 * @date 2018��6��17��
 */
public abstract class Person {
	public abstract String getDescription();
	private String name;
	
	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
