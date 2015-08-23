package org.fewnuts.rutadaki.domain;

import javax.persistence.Entity;

import org.fewnuts.rutadaki.domain.Category;

/**
 * This class represents a category the events are cataloged according to. It
 * may be for instance "MUSIC", or "ARTS", etc. An event can belong to several
 * categories.
 * 
 * @author Fernando
 *
 */
@Entity(name = "Category")
public class Category extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2803478060627172678L;

	private String name;

	public Category() {
		super();
	}

	public Category(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		if(obj instanceof Category){
//			return this.getId() == ((Category) obj).getId();
//		}
//		return false;
//	}

}
