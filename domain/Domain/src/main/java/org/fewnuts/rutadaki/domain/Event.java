package org.fewnuts.rutadaki.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Target;

//@MappedSuperclass
@Entity(name = "Event")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Event extends Persistent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8940121635829959127L;

	private String title;

	@Lob
	@Column(length = 512)
	private String description;

	@Column(precision = 5, scale = 2)
	private BigDecimal price;

	@ManyToMany(targetEntity = Category.class, fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	private Set<Category> categories = new HashSet<Category>();

	@Embedded
	@Target(LocationImpl.class)
	private LocationImpl location;

	@ManyToOne(targetEntity = StoredLocation.class, fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	private StoredLocation stored_location;

	private String imageFileName;

	private boolean published = true;

	@Transient 
	private String link;

	public Event() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(BigDecimal precio) {
		this.price = precio;
	}

	public void addCategory(Category category) {
		categories.add(category);
	}

	public void addCategories(Collection<Category> categories) {
		if (categories != null) {
			this.categories.addAll(categories);
		}
	}

	public void setCategories(Collection<Category> categories) {
		if (categories != null) {
			this.categories.clear();
			this.categories.addAll(categories);
		}
	}

	public void removeCategory(Category category) {
		categories.remove(category);
	}

	public Location getLocation() {
		if (stored_location != null) {
			return stored_location;
		} else {
			return location;
		}
	}

	public void setLocation(Location location) {
		if (location instanceof StoredLocation) {
			this.stored_location = (StoredLocation) location;
		} else {
			this.location = (LocationImpl) location;
			this.stored_location = null;
		}
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean publish) {
		this.published = publish;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
