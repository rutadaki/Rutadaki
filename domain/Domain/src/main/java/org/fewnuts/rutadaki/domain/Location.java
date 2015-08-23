package org.fewnuts.rutadaki.domain;

public interface Location {
	
	public String getName();
	
	public String getAddress();
	
	public City getCity();
	
	public void setName(String name);
	
	public void setAddress(String address);
	
	public void setCity(City city);
}
