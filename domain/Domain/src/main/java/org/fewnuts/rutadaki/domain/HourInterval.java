package org.fewnuts.rutadaki.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import org.fewnuts.rutadaki.domain.Hour;
import org.hibernate.annotations.Target;

/**
 * This class represents an interval between two hours.
 * 
 * @author Fernando
 *
 */
@Embeddable
public class HourInterval {
	@Embedded
	@Target(Hour.class)
	@AttributeOverrides({
	    @AttributeOverride(name="hour",column=@Column(name="hoursStart")),
	    @AttributeOverride(name="minutes",column=@Column(name="minutesStart"))
	  })
	private Hour startHour = new Hour();

	@Embedded
	@Target(Hour.class)
	@AttributeOverrides({
	    @AttributeOverride(name="hour",column=@Column(name="hoursEnd")),
	    @AttributeOverride(name="minutes",column=@Column(name="minutesEnd"))
	  })
	private Hour endHour = new Hour();
	
	public HourInterval(Hour startHour, Hour endHour) {
		super();
		this.startHour = startHour;
		this.endHour = endHour;
	}


	public HourInterval() {
		super();
	}


	public Hour getStartHour() {
		return startHour;
	}

	
	public Hour getEndHour() {
		return endHour;
	}
	
	public void setStartHour(Hour start) {
		this.startHour = start;
	}

	
	public void setEndHour(Hour end) {
		this.endHour = end;
	}
	
	@Override
	public String toString() {
		return startHour + " - " + endHour;
	}
}
