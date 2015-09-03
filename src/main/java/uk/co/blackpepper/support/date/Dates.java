package uk.co.blackpepper.support.date;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class Dates {

	private Dates() {
		throw new AssertionError();
	}
	
	public static Date newDate(int year, int month, int day) {
		return newDate(year, month, day, 0, 0, 0);
	}

	public static Date newDate(int year, int month, int day, int hour, int minute, int second) {
		return newDate(year, month, day, hour, minute, second, 0);
	}
	
	public static Date newDate(int year, int month, int day, int hour, int minute, int second, int millis) {
		Calendar calendar = new GregorianCalendar(year, month - 1, day, hour, minute, second);
		calendar.setLenient(false);
		calendar.set(Calendar.MILLISECOND, millis);
		return calendar.getTime();
	}

	public static Date removeMillis(Date dateWithMillis) {
		if (dateWithMillis == null) {
			return null;
		}
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(dateWithMillis);
		cal.set(Calendar.MILLISECOND, 0);
		Date dateWithoutMillis = new Date(cal.getTime().getTime());
		return dateWithoutMillis;
	}

	public static Date copy(Date date) {
		if (date == null) {
			return null;
		}
		
		return new Date(date.getTime());
	}
	
	public static Timestamp toSqlTimestamp(Date date) {
		return (date != null) ? new Timestamp(date.getTime()) : null;
	}
}
