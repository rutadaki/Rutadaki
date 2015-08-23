package org.fewnuts.rutadaki.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.fewnuts.rutadaki.domain.City;
import org.fewnuts.rutadaki.domain.Location;
import org.fewnuts.rutadaki.domain.StoredLocation;
import org.fewnuts.rutadaki.persistence.dao.CityDao;
import org.fewnuts.rutadaki.persistence.dao.DaoFactory;
import org.fewnuts.rutadaki.persistence.dao.DaoFactoryBuilder;
import org.fewnuts.rutadaki.persistence.dao.StoredLocationDao;
import org.fewnuts.rutadaki.services.LocationServices;

public class LocationServicesImpl implements LocationServices {

	public List<StoredLocation> getStoredLocations() {
		DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();

		daoFactory.beginConectionScope();
		daoFactory.beginTransaction();

		StoredLocationDao dao = daoFactory.createStoredLocationDao();
		List<StoredLocation> locations = dao.getAll();
		List<StoredLocation> result = new ArrayList<StoredLocation>(locations);
		
		daoFactory.commitTransaction();
		daoFactory.endConectionScope();
		
		return result;
	}

	public void saveLocation(Location location) {
		DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();

		daoFactory.beginConectionScope();
		daoFactory.beginTransaction();

		StoredLocationDao dao = daoFactory.createStoredLocationDao();
		
		if(location instanceof StoredLocation){
			dao.update((StoredLocation) location);
		} else {
			StoredLocation storedL = new StoredLocation();
			storedL.setAddress(location.getAddress());
			storedL.setCity(location.getCity());
			storedL.setName(location.getName());
			
			dao.create(storedL);
		}
		
		daoFactory.commitTransaction();
		daoFactory.endConectionScope();
	}

	public void deleteLocation(StoredLocation location) {
		DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();

		daoFactory.beginConectionScope();
		daoFactory.beginTransaction();

		StoredLocationDao dao = daoFactory.createStoredLocationDao();
		dao.delete(location);
		
		daoFactory.commitTransaction();
		daoFactory.endConectionScope();
	}

	public List<City> getAllCities() {
		DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();

		daoFactory.beginConectionScope();
		daoFactory.beginTransaction();

		CityDao dao = daoFactory.createCityDao();
		List<City> cities = dao.getAll();
		
		daoFactory.commitTransaction();
		daoFactory.endConectionScope();
		
		return cities;
	}

	public StoredLocation getStoredLocation(Long locId) {
		DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();

		daoFactory.beginConectionScope();
		daoFactory.beginTransaction();

		StoredLocationDao dao = daoFactory.createStoredLocationDao();
		StoredLocation loc = dao.read(locId);
		
		daoFactory.commitTransaction();
		daoFactory.endConectionScope();
		
		return loc;
	}

}
