package org.fewnuts.rutadaki.domain.utils;

import java.util.Comparator;
import java.util.Date;

import org.fewnuts.rutadaki.domain.Event;
import org.fewnuts.rutadaki.domain.LongEvent;
import org.fewnuts.rutadaki.domain.PuntualEvent;

public class EventDateComparator implements Comparator<Event> {

	public int compare(Event arg0, Event arg1) {
		int result = 0;
		
		if(arg0 instanceof PuntualEvent && arg1 instanceof PuntualEvent){
			Date date1=((PuntualEvent) arg0).getDate();
			Date date2=((PuntualEvent) arg1).getDate();
			return date1.compareTo(date2);
		}
		
		if(arg0 instanceof LongEvent && arg1 instanceof LongEvent){
			Date date1=((LongEvent) arg0).getStartDate();
			Date date2=((LongEvent) arg1).getStartDate();
			if (date1 == null) {
				return -1;
			}
			if (date2 == null) {
				return 1;
			}
			return date1.compareTo(date2);
		}
		
		if(arg0 instanceof PuntualEvent && arg1 instanceof LongEvent){
			Date date1=((PuntualEvent) arg0).getDate();
			Date date2=((LongEvent) arg1).getStartDate();
//			System.out.println(arg1.getTitle()+":" + date2);
			if(date2==null){
				return -100;
			}
				return date1.compareTo(date2);
//			return -100;
		}
		
		if(arg0 instanceof LongEvent && arg1 instanceof PuntualEvent){
			Date date1=((PuntualEvent) arg1).getDate();
			Date date2=((LongEvent) arg0).getStartDate();
//			System.out.println(arg0.getTitle()+":" + date2);
			if(date2==null){
				return 100;
			}
				return - date1.compareTo(date2);
//			return 100;
		}
		
		
		return 0;
	}

}
