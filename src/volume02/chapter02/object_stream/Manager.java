package volume02.chapter02.object_stream;

public class Manager extends Employee {
	private static final long serialVersionUID = -72844085160040043L;
	private double bonus;
	private Employee secretary;

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

	public Employee getSecretary() {
		return secretary;
	}

	public void setSecretary(Employee secretary) {
		this.secretary = secretary;
	}
	
	@Override
	public String toString() {
		return "Manager [bonus=" + bonus + ", secretary=" + secretary + "]";
	}
}
