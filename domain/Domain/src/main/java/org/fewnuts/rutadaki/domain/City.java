package org.fewnuts.rutadaki.domain;

import javax.persistence.Entity;

import org.fewnuts.rutadaki.domain.City;

@Entity(name = "City")
public class City extends Persistent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8008767206354294723L;
	
	private String name = null;
	
	

	public City() {
		super();
	}

	public City(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
