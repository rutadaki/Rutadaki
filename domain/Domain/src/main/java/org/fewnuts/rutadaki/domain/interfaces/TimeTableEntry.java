package org.fewnuts.rutadaki.domain.interfaces;

import org.fewnuts.rutadaki.domain.WeekDay;

public interface TimeTableEntry extends Persistent{
	
	/**
	 * Gets the start hour of the entry
	 * 
	 * @return
	 */
	public Hour getStartHour();
	
	/**
	 * Gets the end hour of the entry
	 * 
	 * @return
	 */
	public Hour getEndHour();
	
	/** 
	 * Day of the week
	 * @return
	 */
	public WeekDay getDayOfTheWeek();
	
	public void setStartHour(Hour start);
	
	public void setEndHour(Hour end);
	
	public void setDayOfTheWeek(WeekDay weekDay);
}
