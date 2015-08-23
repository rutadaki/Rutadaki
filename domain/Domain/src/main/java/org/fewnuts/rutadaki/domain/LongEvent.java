package org.fewnuts.rutadaki.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.fewnuts.rutadaki.domain.LongEvent;
import org.fewnuts.rutadaki.domain.Persistent;
import org.fewnuts.rutadaki.domain.PuntualEvent;
import org.fewnuts.rutadaki.domain.TimeTable;
import org.fewnuts.rutadaki.domain.TimeTableEntry;

@Entity(name = "LongEvent")
public class LongEvent extends Event {

	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = 8902942692750555393L;

	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	private Date endDate;

	@OneToOne(targetEntity = TimeTable.class, fetch = FetchType.EAGER, cascade = {
			CascadeType.MERGE, CascadeType.REMOVE })
	private TimeTable timeTable = null;

	public LongEvent() {
		super();
		
		this.timeTable = new TimeTable();
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setStartDate(Date date) {
		this.startDate = date;
	}

	public void setEndDate(Date date) {
		this.endDate = date;
	}

	public TimeTable getTimeTable() {
		return timeTable;
	}

	public void setTimeTable(TimeTable timeTable) {
		this.timeTable = timeTable;
	}

	public Set<TimeTableEntry> getTimeTableDay(Date date) {
		if (getTimeTable() == null) {
			return null;
		}

		Set<TimeTableEntry> result = new HashSet<TimeTableEntry>();

		for (TimeTableEntry entry : getTimeTable().getEntries()) {
			if (entry.getDayOfTheWeek().isWeekDay(date)) {
				result.add(entry);
			}
		}

		return result;
	}

	public PuntualEvent getAsPuntualEvent(Date date) {
		Set<TimeTableEntry> entries = getTimeTableDay(date);

		if (entries != null && entries.size() != 0) {
			PuntualEvent result = new PuntualEvent();
			result.addCategories(getCategories());
			result.setDate(date);
			result.setTitle(getTitle());
			result.setLocation(getLocation());
			result.setDescription(getDescription());
			result.setPrice(getPrice());

			TimeTableEntry entry = entries.iterator().next();
			result.setStartHour(entry.getStartHour());
			result.setEndHour(entry.getEndHour());
			//((PersistentImpl) result).setId(getId());
			return result;
		}

		return null;
	}

	public boolean isThereEvent(Date date) {
		Set<TimeTableEntry> timeTableDay = getTimeTableDay(date);
		return timeTable == null || timeTableDay != null
				&& timeTableDay.size() != 0;
	}

}
