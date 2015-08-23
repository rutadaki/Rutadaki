package org.fewnuts.rutadaki.services;

import java.util.List;

import org.fewnuts.rutadaki.domain.City;
import org.fewnuts.rutadaki.domain.Location;
import org.fewnuts.rutadaki.domain.StoredLocation;

public interface LocationServices {
	
	List<StoredLocation> getStoredLocations();
	
	void saveLocation(Location location);
	
	void deleteLocation(StoredLocation location);
	
	StoredLocation getStoredLocation(Long locId);
	
	List<City> getAllCities();
}
