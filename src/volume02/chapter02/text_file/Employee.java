package volume02.chapter02.text_file;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
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
	
	public boolean equals(Object otherObject)  {
		if (this == otherObject) {
			return true;
		}
		if (otherObject == null) {
			return false;
		}
		if (getClass() != otherObject.getClass()) {
			return false;
		}
		Employee other = (Employee) otherObject;
		return Objects.equals(name, other.name) && salary == other.salary
				&& Objects.equals(hireDate, other.hireDate);
	}
	
	public int hashCode() {
		return Objects.hash(name, salary, hireDate);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [name=" + name + ", salary=" + salary + ", hireDate=" + hireDate + "]";
	}
}
