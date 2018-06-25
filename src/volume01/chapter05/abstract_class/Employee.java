package volume01.chapter05.abstract_class;

import java.time.LocalDate;

import volume01.chapter05.abstract_class.Person;

/**
 * subclass of an abstract class
 * @author mhts
 * @date 2018Äê6ÔÂ17ÈÕ
 */
public class Employee extends Person{
	private double salary;
	private LocalDate hireDate;
	
	public Employee(String name, double salary, int year, int month, int day) {
		super(name);
		this.salary = salary;
		this.hireDate = LocalDate.of(year, month, day);
	}
	
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public LocalDate getHireDate() {
		return hireDate;
	}
	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	
	public String getDescription() {
		return String.format("an employee with a salary of %.2f", salary);
	}
}
