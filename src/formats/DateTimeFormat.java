package formats;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormat {
	public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		
		public static String dateTimeToString(LocalDateTime dateTime) {
			return dateTime.format(dateTimeFormat);
		}
		
		
		public static LocalDateTime stringToDateTime(String dateTime) {
			return LocalDateTime.parse(dateTime, dateTimeFormat);
	}
}
