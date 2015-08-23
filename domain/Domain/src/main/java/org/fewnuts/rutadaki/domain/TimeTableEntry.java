package org.fewnuts.rutadaki.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import org.fewnuts.rutadaki.domain.Hour;
import org.fewnuts.rutadaki.domain.TimeTableEntry;
import org.fewnuts.rutadaki.domain.WeekDay;
import org.hibernate.annotations.Target;

@Entity(name = "TimeTableEntry")
public class TimeTableEntry extends Persistent {
	
	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = -4054080480079405807L;

	@Embedded
	@Target(Hour.class)
	@AttributeOverrides({
	    @AttributeOverride(name="hour",column=@Column(name="hoursStart")),
	    @AttributeOverride(name="minutes",column=@Column(name="minutesStart"))
	  })
	private Hour startHour = null;
	
	@Embedded 
	@Target(Hour.class)
	@AttributeOverrides({
	    @AttributeOverride(name="hour",column=@Column(name="hoursEnd")),
	    @AttributeOverride(name="minutes",column=@Column(name="minutesEnd"))
	  })
	private Hour endHour = null;
	
	private WeekDay dayOfTheWeek = WeekDay.EVERYDAY;
	
	
	
	/**
	 * Default Constructor
	 */
	public TimeTableEntry() {
		super();
	}
	
	public TimeTableEntry(Hour startHour, Hour endHour, WeekDay dayOfTheWeek) {
		super();
		this.startHour = startHour;
		this.endHour = endHour;
		this.dayOfTheWeek = dayOfTheWeek;
	}



	public Hour getStartHour() {
		return startHour;
	}

	
	public Hour getEndHour() {
		return endHour;
	}

	
	public WeekDay getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	
	public void setStartHour(Hour start) {
		this.startHour = start;
	}

	
	public void setEndHour(Hour end) {
		this.endHour = end;
	}

	
	public void setDayOfTheWeek(WeekDay weekDay) {
		this.dayOfTheWeek = weekDay;
	}

}
