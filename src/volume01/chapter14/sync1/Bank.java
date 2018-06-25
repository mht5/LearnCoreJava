package volume01.chapter14.sync1;

import java.util.Arrays;

/**
 * synchronized bank using keyword "synchronized"
 * Java中每个对象都有一个内部锁，要使用synchronized方法必须先获取其内部锁
 * @author mhts
 * @date 2018年6月22日
 */
public class Bank {
	private final double[] accounts;
	
	public Bank(int n, double initialBalance) {
		accounts = new double[n];
		Arrays.fill(accounts, initialBalance);
	}
	
	public synchronized void transfer(int from, int to, double amount) throws InterruptedException {
		while (accounts[from] < amount) {
			wait();
		}
		System.out.print(Thread.currentThread());
		accounts[from] -= amount;
		System.out.printf(" %10.2f from %d to %d", amount, from, to);
		accounts[to] += amount;
		System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
		notifyAll();
	}

	private double getTotalBalance() {
		double sum = 0;
		for (double d : accounts) {
			sum += d;
		}
		return sum;
	}
	
	public int size() {
		return accounts.length;
	}

}
