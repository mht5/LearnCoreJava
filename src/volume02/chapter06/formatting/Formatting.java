package volume02.chapter06.formatting;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * test time formatting
 * @author mhts
 * @date 2018��7��9��
 */
public class Formatting {

	public static void main(String[] args) {
		ZonedDateTime apollo11Launch= ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0, ZoneId.of("America/New_York"));
		
		String formatted = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(apollo11Launch);
		System.out.println(formatted);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
		formatted = formatter.format(apollo11Launch);
		System.out.println(formatted);
		
		formatted = formatter.withLocale(Locale.FRANCE).format(apollo11Launch);
		System.out.println(formatted);
		
		formatter = DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm");
		formatted = formatter.format(apollo11Launch);
		System.out.println(formatted);
		
		LocalDate churchsBirthday = LocalDate.parse("1903-06-14");
		System.out.println("churchsBirthday: " + churchsBirthday);
		
		apollo11Launch = ZonedDateTime.parse("1969-07-16 03:32:00-0400",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssxx"));
		System.out.println("apollo11Launch: " + apollo11Launch);
		
		for (DayOfWeek w : DayOfWeek.values()) {
			System.out.print(w.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " ");
		}
	}

}
