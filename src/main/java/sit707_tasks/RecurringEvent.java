package sit707_tasks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class RecurringEvent extends Event{
	
	public enum Frequency {
        DAILY, WEEKLY, MONTHLY
    }

    private Frequency frequency;

    public RecurringEvent(String eventName, Date startDate, Date endDate, TimeZone timeZone, Frequency frequency) {
        super(eventName, startDate, endDate, timeZone);
        this.frequency = frequency;
    }

    @Override
    public boolean isRecurringEvent() {
        return true;
    }

    @Override
    public List<Date> getNextOccurrences(int numberOfOccurrences) {
        List<Date> occurrences = new ArrayList<>();
        Calendar calendar = Calendar.getInstance(getTimeZone());
        calendar.setTime(getStartDate());

        for (int i = 0; i < numberOfOccurrences; i++) {
            occurrences.add(calendar.getTime());
            switch (frequency) {
                case DAILY:
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    break;
                case WEEKLY:
                    calendar.add(Calendar.WEEK_OF_YEAR, 1);
                    break;
                case MONTHLY:
                    calendar.add(Calendar.MONTH, 1);
                    break;
                default:
                    break;
            }
        }
        return occurrences;
    }

}
