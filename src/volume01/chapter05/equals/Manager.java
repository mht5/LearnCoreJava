package volume01.chapter05.equals;

/**
 * class with equals(), hashCode() and toString()
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
	
	public double getBonus() {
		return bonus;
	}

	public boolean equals(Object otherObject) {
		if (! super.equals(otherObject)) return false;
		Manager other = (Manager) otherObject;
		return bonus == other.bonus;
	}
	
	public int hashCode() {
		return super.hashCode() + 17 * Double.hashCode(bonus);
	}
	
	public String toString() {
		return super.toString() + "[bonus=" + bonus + "]";
	}
}
