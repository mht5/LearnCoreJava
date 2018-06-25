package volume01.chapter06.clone;

import java.util.Date;

/**
 * deep clone
 * @author mhts
 * @date 2018Äê6ÔÂ18ÈÕ
 */
public class Employee implements Cloneable {
	private String name;
	private double salary;
	private Date hireDate;
	
	public Employee(String name, double salary) {
		super();
		this.name = name;
		this.salary = salary;
		this.hireDate = new Date();
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
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public void setHireDate() {
		this.hireDate = new Date();
	}
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}

	/**
	 * if the class contains mutable fields, need to call the clone method on those fields
	 */
	public Employee clone() throws CloneNotSupportedException {
		Employee cloned = (Employee) super.clone();
		cloned.hireDate = (Date) hireDate.clone();
		return cloned;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", hireDate=" + hireDate + "]";
	}
}
