package org.fewnuts.rutadaki.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import org.fewnuts.rutadaki.domain.City;
import org.fewnuts.rutadaki.domain.StoredLocation;

@Entity(name = "StoredLocation")
public class StoredLocation extends Persistent implements Location{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8759268333105373794L;

	@Embedded
	LocationImpl embeddedLocation;

	public StoredLocation() {
		super();
		embeddedLocation = new LocationImpl();
	}

	public String getName() {
		return embeddedLocation.getName();
	}

	public String getAddress() {
		return embeddedLocation.getAddress();
	}

	public City getCity() {
		return embeddedLocation.getCity();
	}

	public void setName(String name) {
		embeddedLocation.setName(name);
	}

	public void setAddress(String address) {
		embeddedLocation.setAddress(address);
	}

	public void setCity(City city) {
		embeddedLocation.setCity(city);
	}

	@Override
	public String toString() {
		return embeddedLocation.toString();
	} 

}
