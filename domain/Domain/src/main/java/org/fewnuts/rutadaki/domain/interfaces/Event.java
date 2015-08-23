package org.fewnuts.rutadaki.domain.interfaces;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

import org.fewnuts.rutadaki.domain.Location;

public interface Event extends Persistent{

	/**
	 * The title of the event
	 * 
	 * @return
	 */
	public String getTitle();

	/**
	 * Description of the event
	 * 
	 * @return
	 */
	public String getDescription();

	/**
	 * Price of the event
	 * 
	 * @return
	 */
	public BigDecimal getPrice();

	/**
	 * Gets the list of categories the event belongs to
	 * 
	 * @return
	 */
	public Set<Category> getCategories();

	/**
	 * Sets the title of the event
	 * 
	 * @param title
	 */
	public void setTitle(String title);

	/**
	 * Sets the description of the event
	 * 
	 * @param description
	 */
	public void setDescription(String description);

	/**
	 * Sets the price of the event
	 * 
	 * @param precio
	 */
	public void setPrice(BigDecimal precio);

	/**
	 * Adds a category to the event
	 * 
	 * @param category
	 */
	public void addCategory(Category category);
	
	/**
	 * Adds a category list to the event
	 * 
	 * @param categories
	 */
	public void addCategories(Collection<Category> categories);
	
	/**
	 * Adds a category list to the event
	 * 
	 * @param categories
	 */
	public void setCategories(Collection<Category> categories);

	/**
	 * Removes a category from the event
	 * 
	 * @param category
	 */
	public void removeCategory(Category category);

	public Location getLocation();

	public void setLocation(Location location);
	
	public String getImageFileName();
	
	public void setImageFileName(String imageFileName);
	
	public boolean isPublished();
	
	public void setPublished(boolean publish);
	
	public String getLink();
	
	public void setLink(String link);

}
