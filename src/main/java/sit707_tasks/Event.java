package sit707_tasks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Event {
	
	private String eventName;
    private Date startDate;
    private Date endDate;
    private TimeZone timeZone;

    public Event(String eventName, Date startDate, Date endDate, TimeZone timeZone) {
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeZone = timeZone;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public boolean isRecurringEvent() {
    	Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(this.startDate);
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 0);
        startCalendar.set(Calendar.MILLISECOND, 0);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(this.endDate);
        endCalendar.set(Calendar.HOUR_OF_DAY, 0);
        endCalendar.set(Calendar.MINUTE, 0);
        endCalendar.set(Calendar.SECOND, 0);
        endCalendar.set(Calendar.MILLISECOND, 0);

        // If start date and end date are the same, it's not recurring
        if (startCalendar.equals(endCalendar)) {
            return false;
        }

        // If start date is before end date, and they are on different days, it's recurring
        if (this.startDate.before(this.endDate)) {
            return true;
        }

        // If start date is before end date, but they occur on the same day, it's not recurring
        return false;
    }

    public List<Date> getNextOccurrences(int numberOfOccurrences) {
        List<Date> occurrences = new ArrayList<>();
        Calendar calendar = Calendar.getInstance(this.timeZone);
        calendar.setTime(this.startDate);
        
        for (int i = 0; i < numberOfOccurrences; i++) {
            occurrences.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1); // Increment by one day for each occurrence
        }
        return occurrences;
    }

}
