package chapter14.fork_join;

import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

/**
 * test join
 * @author mhts
 * @date 2018Äê6ÔÂ24ÈÕ
 */
public class Counter extends RecursiveTask<Integer> {
	private static final long serialVersionUID = -3515677483677843621L;
	public static final int THRESHOLD = 1000;
	private double[] values;
	private int from;
	private int to;
	private DoublePredicate filter;
	
	public Counter(double[] values, int from, int to, DoublePredicate filter) {
		super();
		this.values = values;
		this.from = from;
		this.to = to;
		this.filter = filter;
	}
	
	public Integer compute() {
		if (to - from < THRESHOLD) {
			int count = 0;
			for (int i = from; i < to; i++) {
				if (filter.test(values[i])) {
					count++;
				}
			}
			return count;
		} else {
			int mid = (from + to) / 2;
			Counter first = new Counter(values, from, mid, filter);
			Counter second = new Counter(values, mid, to, filter);
			invokeAll(first, second);
			return first.join() + second.join();
		}
	}
	
}
