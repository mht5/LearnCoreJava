package chapter05.inheritance;

/**
 * subclass
 * @author mhts
 * @date 2018Äê6ÔÂ17ÈÕ
 */
public class Manager extends Employee {
	private double bonus;

	public Manager(String name, double salary, int year, int month, int day) {
		super(name, salary, year, month, day);
		this.bonus = 0;
	}
	
	public double getSalary() {
		double baseSalary = super.getSalary();
		return baseSalary + bonus;
	}
	
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
}
