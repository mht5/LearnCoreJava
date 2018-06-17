package chapter05.inheritance;

import java.time.LocalDate;

/**
 * superclass
 * @author mhts
 * @date 2018��6��17��
 */
public class Employee{
	private String name;
	private double salary;
	private LocalDate hireDate;
	
	public Employee(String name, double salary, int year, int month, int day) {
		this.name = name;
		this.salary = salary;
		this.hireDate = LocalDate.of(year, month, day);
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
}
