package formats;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormat {
	
	public static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		
		public static String dateToString(LocalDate date) {
			if(date == null) {
				return null;
			}
			return date.format(dateFormat);
		}
		
		
		public static LocalDate stringToDate(String date) {
			return LocalDate.parse(date, dateFormat);
	}

}
