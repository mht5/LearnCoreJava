package volume01.chapter14.sync;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized bank using Lock and Condition
 * @author mhts
 * @date 2018��6��22��
 */
public class Bank {
	private final double[] accounts;
	private Lock bankLock;
	private Condition sufficientFunds;
	
	public Bank(int n, double initialBalance) {
		accounts = new double[n];
		Arrays.fill(accounts, initialBalance);
		bankLock = new ReentrantLock();
		sufficientFunds = bankLock.newCondition();
	}
	
	public void transfer(int from, int to, double amount) throws InterruptedException {
		bankLock.lock();
		try {
			while (accounts[from] < amount) {
				sufficientFunds.await();
			}
			System.out.print(Thread.currentThread());
			accounts[from] -= amount;
			System.out.printf(" %10.2f from %d to %d", amount, from, to);
			accounts[to] += amount;
			System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
			sufficientFunds.signalAll();
		} finally {
			bankLock.unlock();
		}
	}

	private double getTotalBalance() {
		bankLock.lock();
		try {
			double sum = 0;
			for (double d : accounts) {
				sum += d;
			}
			return sum;
		} finally {
			bankLock.unlock();
		}
	}
	
	public int size() {
		return accounts.length;
	}

}