package org.fewnuts.rutadaki.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fewnuts.rutadaki.domain.City;
import org.fewnuts.rutadaki.domain.LocationImpl;
import org.fewnuts.rutadaki.domain.StoredLocation;
import org.fewnuts.rutadaki.services.LocationServices;
import org.fewnuts.rutadaki.services.ServiceFactory;

@Path("/locations")
public class LocationService {

	@GET
	@Produces("application/json")
	public StoredLocation[] getLocations() {
		LocationServices repository = ServiceFactory.createLocationServices();

		List<StoredLocation> locations = repository.getStoredLocations();

		return locations.toArray(new StoredLocation[locations.size()]);
	}

	@Path("{locId}")
	@GET
	@Produces("application/json")
	public StoredLocation getLocation(@PathParam("locId") String locationId) {

		Long locId = Long.parseLong(locationId);

		LocationServices repository = ServiceFactory.createLocationServices();

		StoredLocation result = repository.getStoredLocation(locId);

		return result;

	}

	@GET
	@Path("/cities")
	@Produces("application/json")
	public City[] getCities() {
		LocationServices repository = ServiceFactory.createLocationServices();

		List<City> cities = repository.getAllCities();

		return cities.toArray(new City[cities.size()]);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createLocation(LocationImpl location) {

		LocationServices repository = ServiceFactory.createLocationServices();

		repository.saveLocation(location);
	}

	@PUT
	// @Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/json")
	public void updateLocation(StoredLocation location) {

		System.out.println(location.getName());

		System.out.println(location);
		LocationServices repository = ServiceFactory.createLocationServices();

		repository.saveLocation(location);

		// return Response.ok().build();
	}

	@Path("{locId}")
	@DELETE
	@Consumes("application/json")
	public void deleteLongEvent(@PathParam("locId") Long locId) {
		LocationServices repository = ServiceFactory.createLocationServices();
		
		System.out.println("Delete Location " + locId);
		
		StoredLocation loc = repository.getStoredLocation(locId);

		repository.deleteLocation(loc);
	}

}
