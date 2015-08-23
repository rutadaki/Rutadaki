package org.fewnuts.rutadaki.domain.interfaces;

import java.util.Collection;
import java.util.List;

public interface TimeTable extends Persistent{
	
	List<TimeTableEntry> getEntries();
	
	void addEntry(TimeTableEntry entry);
	
	void removeEntry(int index);
	
	int indexOf(TimeTableEntry entry);
	
	void setEntries(Collection<TimeTableEntry> entries);
	
	int getEntriesCount();
}