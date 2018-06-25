package volume01.chapter14.future;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * test callable, future and future task
 * @author mhts
 * @date 2018Äê6ÔÂ24ÈÕ
 */
public class FutureTest {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in	)) {
			System.out.print("Enter base directory(e.g. C:/test): ");
			String directory = in.nextLine();
			System.out.print("Enter keyword(e.g. test): ");
			String keyword = in.nextLine();
			
			MatchCounter counter = new MatchCounter(new File(directory), keyword);
			FutureTask<Integer> task = new FutureTask<>(counter);
			Thread t = new Thread(task);
			t.start();
			try {
				System.out.println(task.get() + " matching files.");
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
