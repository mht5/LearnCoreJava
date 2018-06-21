package chapter09.priority_queue;

import java.time.LocalDate;
import java.util.PriorityQueue;

/**
 * test priority queue
 * PriorityQueue.remove() always removes the smallest element
 * @author mhts
 * @date 2018Äê6ÔÂ20ÈÕ
 */
public class PriorityQueueTest {
	public static void main(String[] args) {
		PriorityQueue<LocalDate> queue = new PriorityQueue<>();
		queue.add(LocalDate.of(2014, 12, 12));
		queue.add(LocalDate.of(2002, 12, 12));
		queue.add(LocalDate.of(2010, 12, 12));
		queue.add(LocalDate.of(2006, 12, 12));

		System.out.println("Iterating over elements...");
		for (LocalDate date : queue) {
			System.out.println(date);
		}
		
		System.out.println("Removing elements");
		while (!queue.isEmpty()) {
			System.out.println(queue.remove());
		}
	}
}
