package org.fewnuts.rutadaki.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.fewnuts.rutadaki.domain.Hour;
import org.fewnuts.rutadaki.domain.PuntualEvent;
import org.hibernate.annotations.Target;

@Entity(name = "PuntualEvent")
public class PuntualEvent extends Event {

	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = 1029602368334327666L;

	/**
	 * Date of the event
	 */
	@Temporal (TemporalType.DATE)
	private Date date;

	@Embedded
	@Target(Hour.class)
	@AttributeOverrides({
	    @AttributeOverride(name="hour",column=@Column(name="hoursStart")),
	    @AttributeOverride(name="minutes",column=@Column(name="minutesStart"))
	  })
	private Hour startHour;

	@Embedded
	@Target(Hour.class)
	@AttributeOverrides({
	    @AttributeOverride(name="hour",column=@Column(name="hoursEnd")),
	    @AttributeOverride(name="minutes",column=@Column(name="minutesEnd"))
	  })
	private Hour endHour;

	public PuntualEvent() {
		super();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

}
