package volume01.chapter04;

/**
 * 
 * @author mhts
 * @date 2018Äê6ÔÂ17ÈÕ
 */
public class Employee {
	private String name;
	private double salary;
	
	public Employee(String name, double salary) {
		super();
		this.name = name;
		this.salary = salary;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent /100;
		this.salary += raise;
	}
}
