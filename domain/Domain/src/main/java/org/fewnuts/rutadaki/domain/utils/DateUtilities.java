package org.fewnuts.rutadaki.domain.utils;

import java.util.Calendar;
import java.util.Date;

import org.fewnuts.rutadaki.domain.WeekDay;

public class DateUtilities {

	
	public static Date getFirstDayMonth(int month, int year){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 1);
		return calendar.getTime();
	}
	
	public static Date getLastDayMonth(int month, int year){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 1);
		int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(year, month, lastDay);
		return calendar.getTime();
	}
	
	public static Date getMonday(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return calendar.getTime();
	}
	
	public static Date getSunday(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return calendar.getTime();
	}
	
	public static WeekDay getDayOfTheWeek(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		int calendarWeekDay = calendar.get(Calendar.DAY_OF_WEEK);
		WeekDay result = WeekDay.EVERYDAY;
		switch (calendarWeekDay) {
		case Calendar.MONDAY:
			result = WeekDay.MONDAY;
			break;
		case Calendar.TUESDAY:
			result = WeekDay.TUESDAY;
			break;
		case Calendar.WEDNESDAY:
			result = WeekDay.WEDNESDAY;
			break;
		case Calendar.THURSDAY:
			result = WeekDay.THURSDAY;
			break;
		case Calendar.FRIDAY:
			result = WeekDay.FRIDAY;
			break;
		case Calendar.SATURDAY:
			result = WeekDay.SATURDAY;
			break;
		case Calendar.SUNDAY:
			result = WeekDay.SUNDAY;
			break;
		default:
			break;
		}
		return result;
	}
	
	public static boolean isWeekend(Date date){
		WeekDay dayOfWeek = getDayOfTheWeek(date);
		
		return dayOfWeek == WeekDay.FRIDAY || dayOfWeek == WeekDay.SATURDAY || dayOfWeek == WeekDay.SUNDAY;
	}
	
	public static boolean isMidWeek(Date date){
		WeekDay dayOfWeek = getDayOfTheWeek(date);
		
		return dayOfWeek != WeekDay.SATURDAY && dayOfWeek != WeekDay.SUNDAY;
	}
}
