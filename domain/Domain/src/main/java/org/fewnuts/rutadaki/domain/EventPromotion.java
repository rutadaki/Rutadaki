package org.fewnuts.rutadaki.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.fewnuts.rutadaki.domain.Event;
import org.fewnuts.rutadaki.domain.EventPromotion;

@Entity (name="EventPromotion")
public class EventPromotion extends Persistent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1625116334701213867L;

	@ManyToOne(targetEntity = Event.class , fetch = FetchType.EAGER, cascade = { CascadeType.MERGE } )
	private Event promotedEvent;
	
	private Date startDate;
	
	private Date endDate;

	public EventPromotion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EventPromotion(Event promotedEvent, Date startDate, Date endDate) {
		super();
		this.promotedEvent = promotedEvent;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Event getPromotedEvent() {
		return promotedEvent;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setStartDate(Date date) {
		startDate = date;
	}

	public void setEndDate(Date date) {
		endDate = date;
	}

}
