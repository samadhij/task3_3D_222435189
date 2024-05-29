package sit707_tasks;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Ahsan Habib
 */
public class DateUtilTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testStudentIdentity() {
		String studentId = "222435189";
		Assert.assertNotNull("Student ID is null", studentId);
	}

	@Test
	public void testStudentName() {
		String studentName = "Samadhi Jayasinghe";
		Assert.assertNotNull("Student name is null", studentName);
	}
	
	// Basic Increment Tests
	
	@Test
    public void testIncrementMidnight() {
        DateUtil date = new DateUtil(15, 4, 2024, 23, 59, 59);
        date.increment();
        Assert.assertEquals(16, date.getDay());
        Assert.assertEquals(4, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
        Assert.assertEquals(0, date.getHour());
        Assert.assertEquals(0, date.getMinute());
        Assert.assertEquals(0, date.getSecond());
    }

    @Test
    public void testIncrementEndOfMonth() {
        DateUtil date = new DateUtil(30, 4, 2024, 23, 59, 59);
        date.increment();
        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(5, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
    }

    @Test
    public void testIncrementLeapYear() {
        DateUtil date = new DateUtil(28, 2, 2024, 23, 59, 59);
        date.increment();
        Assert.assertEquals(29, date.getDay());
        Assert.assertEquals(2, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
    }

    @Test
    public void testIncrementNonLeapYear() {
        DateUtil date = new DateUtil(28, 2, 2023, 23, 59, 59);
        date.increment();
        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(3, date.getMonth());
        Assert.assertEquals(2023, date.getYear());
    }
    
    // Basic Decrement Tests

    @Test
    public void testDecrementMidnight() {
        DateUtil date = new DateUtil(16, 4, 2024, 0, 0, 0);
        date.decrement();
        Assert.assertEquals(15, date.getDay());
        Assert.assertEquals(4, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
        Assert.assertEquals(23, date.getHour());
        Assert.assertEquals(59, date.getMinute());
        Assert.assertEquals(59, date.getSecond());
    }

    @Test
    public void testDecrementStartOfMonth() {
        DateUtil date = new DateUtil(1, 5, 2024, 0, 0, 0);
        date.decrement();
        Assert.assertEquals(30, date.getDay());
        Assert.assertEquals(4, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
    }

    @Test
    public void testDecrementLeapYear() {
        DateUtil date = new DateUtil(1, 3, 2024, 0, 0, 0);
        date.decrement();
        Assert.assertEquals(29, date.getDay());
        Assert.assertEquals(2, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
    }

    @Test
    public void testDecrementNonLeapYear() {
        DateUtil date = new DateUtil(1, 3, 2023, 0, 0, 0);
        date.decrement();
        Assert.assertEquals(28, date.getDay());
        Assert.assertEquals(2, date.getMonth());
        Assert.assertEquals(2023, date.getYear());
    }
    
    // Invalid Date Tests

    @Test
    public void testInvalidDay() {
        thrown.expect(RuntimeException.class);
        new DateUtil(31, 4, 2024, 0, 0, 0);
    }

    @Test
    public void testInvalidMonth() {
        thrown.expect(RuntimeException.class);
        new DateUtil(10, 13, 2024, 0, 0, 0);
    }

    @Test
    public void testInvalidYear() {
        thrown.expect(RuntimeException.class);
        new DateUtil(10, 10, 2025, 0, 0, 0);
    }

    @Test
    public void testInvalidHour() {
        thrown.expect(RuntimeException.class);
        new DateUtil(10, 10, 2024, 24, 0, 0);
    }

    @Test
    public void testInvalidMinute() {
        thrown.expect(RuntimeException.class);
        new DateUtil(10, 10, 2024, 23, 60, 0);
    }

    @Test
    public void testInvalidSecond() {
        thrown.expect(RuntimeException.class);
        new DateUtil(10, 10, 2024, 23, 59, 60);
    }
    
    // Edge Case Tests
    @Test
    public void testIncrementEndOfYear() {
        DateUtil date = new DateUtil(31, 12, 2024, 23, 59, 59);
        date.increment();
        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(1, date.getMonth());
        Assert.assertEquals(2025, date.getYear());
        Assert.assertEquals(0, date.getHour());
        Assert.assertEquals(0, date.getMinute());
        Assert.assertEquals(0, date.getSecond());
    }

    @Test
    public void testDecrementEdgeStartOfYear() {
        DateUtil date = new DateUtil(1, 1, 2024, 0, 0, 0);
        date.decrement();
        Assert.assertEquals(31, date.getDay());
        Assert.assertEquals(12, date.getMonth());
        Assert.assertEquals(2023, date.getYear());
        Assert.assertEquals(23, date.getHour());
        Assert.assertEquals(59, date.getMinute());
        Assert.assertEquals(59, date.getSecond());
    }

    @Test
    public void testIncrementLastSecondOfDay() {
        DateUtil date = new DateUtil(15, 4, 2024, 23, 59, 59);
        date.increment();
        Assert.assertEquals(16, date.getDay());
        Assert.assertEquals(4, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
        Assert.assertEquals(0, date.getHour());
        Assert.assertEquals(0, date.getMinute());
        Assert.assertEquals(0, date.getSecond());
    }

    @Test
    public void testDecrementFirstSecondOfDay() {
        DateUtil date = new DateUtil(15, 4, 2024, 0, 0, 0);
        date.decrement();
        Assert.assertEquals(14, date.getDay());
        Assert.assertEquals(4, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
        Assert.assertEquals(23, date.getHour());
        Assert.assertEquals(59, date.getMinute());
        Assert.assertEquals(59, date.getSecond());
    }

    @Test
    public void testIncrementEndOfFebruary() {
        DateUtil date = new DateUtil(28, 2, 2024, 23, 59, 59);
        date.increment();
        Assert.assertEquals(29, date.getDay());
        Assert.assertEquals(2, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
    }

    @Test
    public void testDecrementStartOfMarch() {
        DateUtil date = new DateUtil(1, 3, 2023, 0, 0, 0);
        date.decrement();
        Assert.assertEquals(28, date.getDay());
        Assert.assertEquals(2, date.getMonth());
        Assert.assertEquals(2023, date.getYear());
    }

    @Test
    public void testIncrementFebruary29() {
        DateUtil date = new DateUtil(29, 2, 2024, 23, 59, 59);
        date.increment();
        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(3, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
    }

    @Test
    public void testDecrementMarch1() {
        DateUtil date = new DateUtil(1, 3, 2024, 0, 0, 0);
        date.decrement();
        Assert.assertEquals(29, date.getDay());
        Assert.assertEquals(2, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
    }
    
 // DST Transition Tests
    @Test
    public void testDSTTransitionForward() {
        // Daylight Saving Time transition: Spring forward
        DateUtil date = new DateUtil(1, 10, 2023, 1, 59, 59); // 1:59:59 AM
        date.increment();
        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(10, date.getMonth());
        Assert.assertEquals(2023, date.getYear());
        Assert.assertEquals(3, date.getHour()); // 3:00:00 AM due to DST transition
        Assert.assertEquals(0, date.getMinute());
        Assert.assertEquals(0, date.getSecond());
    }

    @Test
    public void testDSTTransitionBackward() {
        // Daylight Saving Time transition: Fall back
        DateUtil date = new DateUtil(7, 4, 2024, 2, 59, 59); // 2:59:59 AM
        date.increment();
        Assert.assertEquals(7, date.getDay());
        Assert.assertEquals(4, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
        Assert.assertEquals(2, date.getHour()); // 2:00:00 AM (skips back to 2:00:00 AM due to DST)
        Assert.assertEquals(0, date.getMinute());
        Assert.assertEquals(0, date.getSecond());
    }
    
    //Event Test Cases
    
    @Test
    public void testEventCreation() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.JUNE, 1, 10, 0); // June 1, 2024, 10:00 AM
        Date startDate = calendar.getTime();
        calendar.set(2024, Calendar.JUNE, 1, 12, 0); // June 1, 2024, 12:00 PM
        Date endDate = calendar.getTime();
        TimeZone timeZone = TimeZone.getTimeZone("Australia/Melbourne");

        Event event = new Event("Meeting", startDate, endDate, timeZone);

        Assert.assertEquals("Meeting", event.getEventName());
        Assert.assertEquals(startDate, event.getStartDate());
        Assert.assertEquals(endDate, event.getEndDate());
        Assert.assertEquals(timeZone, event.getTimeZone());
    }

    @Test
    public void testRecurringEvent() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.JUNE, 1, 10, 0); // June 1, 2024, 10:00 AM
        Date startDate = calendar.getTime();
        calendar.set(2024, Calendar.JUNE, 2, 12, 0); // June 1, 2024, 12:00 PM
        Date endDate = calendar.getTime();
        TimeZone timeZone = TimeZone.getTimeZone("Australia/Melbourne");

        Event event = new Event("Meeting", startDate, endDate, timeZone);

        Assert.assertTrue(event.isRecurringEvent());
    }

    @Test
    public void testNextOccurrences() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.JUNE, 1, 10, 0); // June 1, 2024, 10:00 AM
        Date startDate = calendar.getTime();
        calendar.set(2024, Calendar.JUNE, 1, 12, 0); // June 1, 2024, 12:00 PM
        Date endDate = calendar.getTime();
        TimeZone timeZone = TimeZone.getTimeZone("Australia/Melbourne");

        Event event = new Event("Meeting", startDate, endDate, timeZone);

        List<Date> occurrences = event.getNextOccurrences(5); // Get next 5 occurrences

        // Assert expected number of occurrences
        Assert.assertEquals(5, occurrences.size());
    }
    
    @Test
    public void testGetNextOccurrencesForRecurringEvent() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.JUNE, 1, 10, 0); // June 1, 2024, 10:00 AM
        Date startDate = calendar.getTime();
        calendar.set(2024, Calendar.JUNE, 1, 12, 0); // June 1, 2024, 12:00 PM
        Date endDate = calendar.getTime();
        TimeZone timeZone = TimeZone.getTimeZone("Australia/Melbourne");

        // Create a recurring event
        RecurringEvent recurringEvent = new RecurringEvent("Weekly Meeting", startDate, endDate, timeZone,
                RecurringEvent.Frequency.WEEKLY);

        List<Date> occurrences = recurringEvent.getNextOccurrences(5); // Get next 5 occurrences

        // Assert expected number of occurrences
        Assert.assertEquals(5, occurrences.size());
    }

    @Test
    public void testGetNextOccurrencesForMonthlyRecurringEvent() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.JUNE, 1, 10, 0); // June 1, 2024, 10:00 AM
        Date startDate = calendar.getTime();
        calendar.set(2024, Calendar.JUNE, 1, 12, 0); // June 1, 2024, 12:00 PM
        Date endDate = calendar.getTime();
        TimeZone timeZone = TimeZone.getTimeZone("Australia/Melbourne");

        // Create a monthly recurring event
        RecurringEvent recurringEvent = new RecurringEvent("Monthly Review", startDate, endDate, timeZone,
                RecurringEvent.Frequency.MONTHLY);

        List<Date> occurrences = recurringEvent.getNextOccurrences(5); // Get next 5 occurrences

        // Assert expected number of occurrences
        Assert.assertEquals(5, occurrences.size());
    }

    @Test
    public void testGetNextOccurrencesForDailyRecurringEvent() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.JUNE, 1, 10, 0); // June 1, 2024, 10:00 AM
        Date startDate = calendar.getTime();
        calendar.set(2024, Calendar.JUNE, 1, 12, 0); // June 1, 2024, 12:00 PM
        Date endDate = calendar.getTime();
        TimeZone timeZone = TimeZone.getTimeZone("Australia/Melbourne");

        // Create a daily recurring event
        RecurringEvent recurringEvent = new RecurringEvent("Daily Standup", startDate, endDate, timeZone,
                RecurringEvent.Frequency.DAILY);

        List<Date> occurrences = recurringEvent.getNextOccurrences(5); // Get next 5 occurrences

        // Assert expected number of occurrences
        Assert.assertEquals(5, occurrences.size());
    }
    
}
