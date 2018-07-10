package volume02.chapter06.local_date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 * test local date
 * @author mhts
 * @date 2018Äê7ÔÂ9ÈÕ
 */
public class LocalDates {

	public static void main(String[] args) {
		LocalDate today = LocalDate.now();
		System.out.println("today: " + today);
		
		LocalDate alonzosBirthday = LocalDate.of(1903, 6, 14);
		alonzosBirthday = LocalDate.of(1903, Month.JUNE, 14);
		System.out.println("alonzosBirthday: " + alonzosBirthday);
		
		LocalDate programmersDay = LocalDate.of(2018, 1, 1).plusDays(255);
		System.out.println("programmersDay: " + programmersDay);
		
		LocalDate laborDay = LocalDate.of(2018, Month.MAY, 1);
		LocalDate nationalDay = LocalDate.of(2018, 10, 1);
		
		System.out.println("Until national day: " + laborDay.until(nationalDay));
		System.out.println("Until national day: " + laborDay.until(nationalDay, ChronoUnit.DAYS));
		
		System.out.println(LocalDate.of(2016, 1, 31).plusMonths(1));
		System.out.println(LocalDate.of(2016, 3, 31).minusMonths(1));
		
		DayOfWeek startOfLastMillennium = LocalDate.of(1900, 1, 1).getDayOfWeek();
		System.out.println("startOfLastMillennium: " + startOfLastMillennium);
		System.out.println(startOfLastMillennium.getValue());
		
		System.out.println(DayOfWeek.SATURDAY.plus(3));
	}

}
