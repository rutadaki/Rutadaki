package org.fewnuts.rutadaki.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.fewnuts.rutadaki.domain.City;
import org.fewnuts.rutadaki.domain.LocationImpl;

@Embeddable 
@Access (AccessType.FIELD)
public class LocationImpl implements Location{

	private String name;

	private String address;

	@ManyToOne (targetEntity=City.class)
	private City city;
	
	public LocationImpl() {
		super();
	}

	public LocationImpl(String locationName, String locationAddres) {
		super();
		this.name = locationName;
		this.address = locationAddres;
	}
	
	public LocationImpl(String locationName, String locationAddres, City locationCity) {
		super();
		this.name = locationName;
		this.address = locationAddres;
		this.city = (City) locationCity;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public City getCity() {
		return city;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(City city) {
		this.city = (City) city;
	}
	
	@Override
	public String toString() {
		String result = this.name;
		
		if(address != null && !address.isEmpty()){
			result = result + "(" + address +")";
		}
		
		return result;
	}

}
