package volume01.chapter08.pair3;

import volume01.chapter05.equals.Employee;
import volume01.chapter05.equals.Manager;
import volume01.chapter08.Pair;

/**
 * test wildcard type
 * @author mhts
 * @date 2018年6月20日
 */
public class PairTest3 {

	public static void main(String[] args) {
		Manager ceo = new Manager("jack", 20000, 2012, 12, 12);
		Manager cfo = new Manager("mike", 12000, 2016, 2, 20);
		
		Pair<Manager> buddies = new Pair<>(ceo, cfo);
		printBuddies(buddies);
		ceo.setBonus(10000);
		cfo.setBonus(8000);
		Manager[] managers = {ceo, cfo};
		
		Pair<Employee> result = new Pair<>();
		minmaxBonus(managers, result);
		System.out.println("first: " + result.getFirst().getName()
				+ ", second: " + result.getSecond().getName());
		maxminBonus(managers, result);
		System.out.println("first: " + result.getFirst().getName()
				+ ", second: " + result.getSecond().getName());
	}
	
	/**
	 * 带有子类型限定符的通配符可以从泛型对象读取
	 * @param p
	 */
	public static void printBuddies(Pair<? extends Employee> p) {
		Employee first = p.getFirst();
		Employee second = p.getSecond();
		System.out.println(first.getName() + " and " + second.getName() + " are buddies");
	}
	
	/**
	 * 带有超类型限定符的通配符可以向泛型对象写入
	 * @param a
	 * @param result
	 */
	public static void minmaxBonus(Manager[] a, Pair<? super Manager> result) {
		if (a.length == 0) {
			return;
		}
		Manager min = a[0];
		Manager max = a[0];
		Manager manager;
		double bonus;
		for (int i = 1; i < a.length; i++) {
			manager = a[i];
			bonus = a[i].getBonus();
			if (min.getBonus() > bonus) {
				min = manager;
			}
			if (max.getBonus() < bonus) {
				max = manager;
			}
		}
		result.setFirst(min);
		result.setSecond(max);
	}
	
	public static void maxminBonus(Manager[] a, Pair<? super Manager> result) {
		minmaxBonus(a, result);
		PairAlg.swap(result);
	}

}
