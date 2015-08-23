package org.fewnuts.rutadaki.domain.interfaces;

public interface Organizer extends Persistent{
	
	public String getName();
	
	public StoredLocation getLocation();
	
	public String getTwitterUser();
	
	public String getFacebookUser();
	
	public void setLocation(StoredLocation location);
	
	public void setTwitterUser(String twitter);
	
	public void setFacebookUser(String facebook);
	
	public void setName(String name);

}
