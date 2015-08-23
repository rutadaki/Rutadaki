package org.fewnuts.rutadaki.domain;

import java.text.DecimalFormat;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import org.fewnuts.rutadaki.domain.Hour;

@Embeddable 
@Access (AccessType.FIELD)
public class Hour {

	private int hour = 0;

	private int minutes = 0;

	/**
	 * Default Constructor
	 */
	public Hour() {
		super();
	}

	/**
	 * Creates an hour with the valus given in parameters
	 * 
	 * @param hour
	 * @param minutes
	 */
	public Hour(int hour, int minutes) {
		super();
		if(hour<0 || hour > 23 || minutes<0 || minutes > 59)
			throw new IllegalArgumentException("Invalid hour or minutes parameters");
		this.hour = hour;
		this.minutes = minutes;
	}

	public int getHour() {
		return hour;
	}

	public int getMinutes() {
		return minutes;
	}

	public void set(int hours, int minutes) {
		if(hour<0 || hour > 23 || minutes<0 || minutes > 59)
			throw new IllegalArgumentException("Invalid hour or minutes parameters");
		this.hour = hours;
		this.minutes = minutes;
	}

	public void setHours(int hours) {
		if(hours<0 || hours > 23)
			throw new IllegalArgumentException("Invalid hour parameter");
		this.hour = hours;
	}

	public void setMinutes(int minutes) {
		if(minutes<0 || minutes > 59)
			throw new IllegalArgumentException("Invalid minutes parameter");
		this.minutes = minutes;
	}
	
	@Override
	public String toString() {
		DecimalFormat myFormatter = new DecimalFormat("00");
		//return String.format("%02d%n", hour)+":"+String.format("%02d%n", minutes);
		return myFormatter.format(hour)+":" + myFormatter.format(minutes);
	}

}
