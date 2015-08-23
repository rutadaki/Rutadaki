package org.fewnuts.rutadaki.domain.interfaces;

/**
 * @author Fernando
 *
 */
public interface Hour{
	
	/**
	 * Gets the hour number (00 - 23)
	 * @return
	 */
	public int getHour();
	
	/**
	 * Gets the minutes number (00 - 59)
	 * 
	 * @return
	 */
	public int getMinutes();
	
	/**
	 * Set the hour value
	 * 
	 * @param hours the hour number (00 - 23)
	 * @param minutes the minutes number (00 - 59)
	 */
	public void set(int hours, int minutes);
	
	/**
	 * Sets the hours number
	 * 
	 * @param hours the hour number (00 - 23)
	 */
	public void setHours(int hours);
	
	/** 
	 * Sets the minutes
	 * 
	 * @param minutes the minute number (00 - 59)
	 */
	public void setMinutes(int minutes);
}
