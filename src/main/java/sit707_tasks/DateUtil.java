package sit707_tasks;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Ahsan Habib
 */
public class DateUtil {

	// Months in order 0-11 maps to January-December.
	private static String[] MONTHS = new String[] {
			"January", "February", "March", "April", "May", "June", 
			"July", "August", "September", "October", "November", "December"
	};
	
	private int day, month, year, hour, minute, second;
	
	private static final ZoneId ZONE_ID = ZoneId.of("Australia/Melbourne");
	
	/*
	 * Constructs object from given day, month and year.
	 */
	public DateUtil(int day, int month, int year, int hour, int minute, int second) {
		// Is supplied day/month/year a valid date?
		if (day < 1 || day > 31)
			throw new RuntimeException("Invalid day: " + day + ", expected range 1-31");
		if (month < 1 || month > 12)
			throw new RuntimeException("Invalid month: " + month + ", expected range 1-12");
		if (year < 1700 || year > 2024)
			throw new RuntimeException("Invalid year: " + year + ", expected range 1700-2024");
		if (hour < 0 || hour > 23)
            throw new RuntimeException("Invalid hour: " + hour + ", expected range 0-23");
        if (minute < 0 || minute > 59)
            throw new RuntimeException("Invalid minute: " + minute + ", expected range 0-59");
        if (second < 0 || second > 59)
            throw new RuntimeException("Invalid second: " + second + ", expected range 0-59");
        if (day > monthDuration(month, year))
			throw new RuntimeException("Invalid day: " + day + ", max day: " + monthDuration(month, year));
		
		this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}
	
	public int getHour() {
		return hour;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public int getSecond() {
		return second;
	}
	
	public void increment() {
        ZonedDateTime zonedDateTime = getZonedDateTime().plusSeconds(1);
        updateFieldsFromZonedDateTime(zonedDateTime);
    }

    public void decrement() {
        ZonedDateTime zonedDateTime = getZonedDateTime().minusSeconds(1);
        updateFieldsFromZonedDateTime(zonedDateTime);
    }

    private ZonedDateTime getZonedDateTime() {
        return ZonedDateTime.of(year, month, day, hour, minute, second, 0, ZONE_ID);
    }
    
    private void updateFieldsFromZonedDateTime(ZonedDateTime zonedDateTime) {
        this.year = zonedDateTime.getYear();
        this.month = zonedDateTime.getMonthValue();
        this.day = zonedDateTime.getDayOfMonth();
        this.hour = zonedDateTime.getHour();
        this.minute = zonedDateTime.getMinute();
        this.second = zonedDateTime.getSecond();
    }
	
	/**
	 * Calculate duration of current month of year.
	 * @param month
	 * @param year
	 * @return
	 */
	public static int monthDuration(int month, int year) {		
		if (month == 2 && year % 4 == 0) {
			// February leap year?
			return 29;			
		} else if (month == 2) {  
			// normal 28 days February
			return 28;
			
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			// 30 days' months
			return 30;			
		}
		return 31;  // rest are 31 days' months.
	}
	
	/**
	 * User friendly output.
	 */
	public String toString() {
		return String.format("%02d %s %04d %02d:%02d:%02d", day, MONTHS[month - 1], year, hour, minute, second);
	}
	
}
