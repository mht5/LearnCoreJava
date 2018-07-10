package volume02.chapter06.zoned_times;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * test zoned date time
 * @author mhts
 * @date 2018年7月9日
 */
public class ZonedTimes {

	public static void main(String[] args) {
		ZonedDateTime apollo11Launch= ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0, ZoneId.of("America/New_York"));
		System.out.println("apollo11Lauch: " + apollo11Launch);
		
		Instant instant = apollo11Launch.toInstant();
		System.out.println("instant: " + instant);
		
		ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC"));
		System.out.println("zonedDateTime: " + zonedDateTime);
		
		/**
		 * 夏令时开始，前拨一个小时
		 */
		ZonedDateTime skipped = ZonedDateTime.of(LocalDate.of(2013, 3, 31), LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
		System.out.println("skipped: " + skipped);
		
		/**
		 * 夏令时结束，回拨一小时
		 */
		ZonedDateTime ambigous = ZonedDateTime.of(LocalDate.of(2013, 10, 27), LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
		ZonedDateTime anHourLater = ambigous.plusHours(1);
		System.out.println("ambigous: " + ambigous);
		System.out.println("anHourLater: " + anHourLater);
		
		/**
		 * 调整跨越夏令时边界的日期是需要用Period
		 */
		ZonedDateTime meeting = ZonedDateTime.of(LocalDate.of(2013, 10, 31), LocalTime.of(14, 30), ZoneId.of("America/Los_Angeles"));
		System.out.println("meeting: " + meeting);
		ZonedDateTime nextMeeting = meeting.plus(Duration.ofDays(7));
		System.out.println("nextMeeting: " + nextMeeting);
		nextMeeting = meeting.plus(Period.ofDays(7));
		System.out.println("nextMeeting: " + nextMeeting);
	}

}
