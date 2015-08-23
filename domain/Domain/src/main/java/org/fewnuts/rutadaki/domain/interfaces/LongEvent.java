package org.fewnuts.rutadaki.domain.interfaces;

import java.util.Date;
import java.util.Set;

public interface LongEvent extends Event{
	/**
	 * The date when the event starts
	 * 
	 * @return
	 */
	public Date getStartDate();
	
	/**
	 * The date when the event ends
	 * @return
	 */
	public Date getEndDate();
	
	/**
	 * Gets the timetable of the event, if it longs more than a single day
	 * 
	 * @return
	 */
	public TimeTable getTimeTable();
	
	/**
	 * Sets the timetable of the event.
	 * 
	 * @param timeTable
	 */
	public void setTimeTable(TimeTable timeTable);
	
	/**
	 * Sets the star date of the event
	 * 
	 * @param date
	 */
	public void setStartDate(Date date);
	
	/**
	 * Sets the end date of the event
	 * 
	 * @param date
	 */
	public void setEndDate(Date date);
	
	public Set<TimeTableEntry> getTimeTableDay(Date date);
	
	public PuntualEvent getAsPuntualEvent(Date date);
	
	public boolean isThereEvent(Date date);
}
