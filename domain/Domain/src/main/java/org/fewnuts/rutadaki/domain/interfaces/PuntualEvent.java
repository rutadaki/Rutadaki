package org.fewnuts.rutadaki.domain.interfaces;

import java.util.Date;

public interface PuntualEvent extends Event{
	/**
	 * Gets the start hour of the event
	 * 
	 * @return
	 */
	public Hour getStartHour();
	
	/**
	 * Gets the end hour of the event
	 * 
	 * @return
	 */
	public Hour getEndHour();
	
	/**
	 * Gets the date of the event
	 * 
	 * @return
	 */
	public Date getDate();
	
	/**
	 * Sets the date of the event
	 * 
	 * @param date
	 */
	public void setDate(Date date);
	
	/** Sets the start hour of the event
	 * @param start
	 * @return
	 */
	public void setStartHour(Hour start);
	
	/** Sets the end hour of the event
	 * @param end
	 * @return
	 */
	public void setEndHour(Hour end);
}
