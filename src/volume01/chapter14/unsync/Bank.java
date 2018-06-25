package volume01.chapter14.unsync;

import java.util.Arrays;

/**
 * a bank with a number of accounts
 * @author mhts
 * @date 2018��6��22��
 */
public class Bank {
	private final double[] accounts;
	
	public Bank(int n, double initialBalance) {
		accounts = new double[n];
		Arrays.fill(accounts, initialBalance);
	}
	
	public void transfer(int from, int to, double amount) {
		if (accounts[from] < amount) {
			return;
		}
		System.out.print(Thread.currentThread());
		accounts[from] -= amount;
		System.out.printf(" %10.2f from %d to %d", amount, from, to);
		accounts[to] += amount;
		System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
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
