package org.fewnuts.rutadaki.domain.interfaces;

import java.util.Date;

public interface EventPromotion extends Persistent{
	
	public Event getPromotedEvent();
	
	public Date getStartDate();
	
	public Date getEndDate();
	
	public void setStartDate(Date date);
	
	public void setEndDate(Date date);
}
