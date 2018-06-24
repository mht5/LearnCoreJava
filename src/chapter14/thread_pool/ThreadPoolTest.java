package chapter14.thread_pool;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * test thread pool
 * @author mhts
 * @date 2018Äê6ÔÂ24ÈÕ
 */
public class ThreadPoolTest {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in	)) {
			System.out.print("Enter base directory(e.g. C:/test): ");
			String directory = in.nextLine();
			System.out.print("Enter keyword(e.g. test): ");
			String keyword = in.nextLine();
			
			ExecutorService pool = Executors.newCachedThreadPool();
			MatchCounter counter = new MatchCounter(new File(directory), keyword, pool);
			Future<Integer> result = pool.submit(counter);
			try {
				System.out.println(result.get() + " matching files.");
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			pool.shutdown();
			int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
			System.out.println("largest pool size: " + largestPoolSize);
		}
	}
}
