package org.fewnuts.rutadaki.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "TimeTable")
public class TimeTable extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 825585569986778841L;

	@OneToMany(targetEntity = TimeTableEntry.class, fetch=FetchType.EAGER, cascade = {
			CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE }, orphanRemoval=true)
	@JoinColumn(name="timetable_id")
	private Set<TimeTableEntry> entries = new HashSet<TimeTableEntry>();
	
	

	public TimeTable() {
		super();
	}

	public List<TimeTableEntry> getEntries() {
		return new ArrayList<TimeTableEntry>(entries);
	}

	public void addEntry(TimeTableEntry entry) {
		this.entries.add(entry);
	}

	public void removeEntry(int index) {
		this.entries.remove(index);
	}

//	public int indexOf(TimeTableEntry entry) {
//		return entries.indexOf(entry);
//	}

	public void setEntries(Collection<TimeTableEntry> entries) {
		this.entries.clear();
		this.entries.addAll(entries);
	}

	public int getEntriesCount() {
		return entries.size();
	}

}
