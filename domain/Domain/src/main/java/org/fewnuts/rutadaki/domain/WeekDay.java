package org.fewnuts.rutadaki.domain;

import java.util.Date;

import org.fewnuts.rutadaki.domain.utils.DateUtilities;

public enum WeekDay {
	
	MONDAY,
	TUESDAY,
	WEDNESDAY,
	THURSDAY,
	FRIDAY,
	SATURDAY,
	SUNDAY,
	/**
	 * from Monday to Friday
	 */
	MIDWEEK,
	/**
	 * from Friday to Sunday
	 */
	WEEKEND,
	/**
	 * from Monday to Sunday
	 */
	EVERYDAY;
	
	public boolean isWeekDay(Date date){
		
		if(this == EVERYDAY){
			return true;
		}
		
		if(this == WEEKEND)
		{
			return DateUtilities.isWeekend(date);
		}
		if(this == MIDWEEK)
		{
			return DateUtilities.isMidWeek(date);
		}
		
		WeekDay dayOfWeek = DateUtilities.getDayOfTheWeek(date);
		
		return this == dayOfWeek;
	}
}
