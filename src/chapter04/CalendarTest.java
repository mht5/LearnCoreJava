package chapter04;

import java.time.LocalDate;

/**
 * 使用LocalDate实现打印日历
 * @author mhts
 * @date 2018年6月17日
 */
public class CalendarTest {
	public static void main(String[] args) {
		LocalDate date = LocalDate.now();
		int month = date.getMonthValue();
		int today = date.getDayOfMonth();
		date = date.minusDays(today -1);
		int dayOfWeek = date.getDayOfWeek().getValue();
		
		System.out.println("Mon Tue Wed Thu Fri Sat Sun");
		for (int i = 1; i < dayOfWeek; i++) {
			System.out.print("       ");
		}
		
		while (date.getMonthValue() == month) {
			int dayOfMonth = date.getDayOfMonth();
			System.out.printf("%3d", dayOfMonth);
			System.out.print(dayOfMonth == today ? "*" : " ");
			date  =date.plusDays(1);
			if (date.getDayOfWeek().getValue() == 1) {
				System.out.println();
			}
		}
	}
}
