package org.fewnuts.rutadaki.api;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.fewnuts.rutadaki.domain.Category;
import org.fewnuts.rutadaki.domain.City;
import org.fewnuts.rutadaki.domain.Event;
import org.fewnuts.rutadaki.domain.Location;
import org.fewnuts.rutadaki.domain.LocationImpl;
import org.fewnuts.rutadaki.domain.LongEvent;
import org.fewnuts.rutadaki.domain.PuntualEvent;
import org.fewnuts.rutadaki.domain.StoredLocation;
import org.fewnuts.rutadaki.domain.TimeTableEntry;
import org.fewnuts.rutadaki.domain.utils.EventDateComparator;
import org.fewnuts.rutadaki.services.CategoryServices;
import org.fewnuts.rutadaki.services.EventServices;
import org.fewnuts.rutadaki.services.ServiceFactory;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/outstanding")
public class OutstandingService {

	@GET
	@Produces("application/json")
	public Event[] getEvents() {
		
		EventServices repository = ServiceFactory.createEventServices();
		CategoryServices categoryRepository = ServiceFactory
				.createCategoryServices();

		SortedSet<Event> eventos = new TreeSet<Event>(new EventDateComparator());
		
		eventos.addAll(repository.getOutStandingEvents());

		return eventos.toArray(new Event[eventos.size()]);
	}

	@POST
	@Path("{eventId}")
	@Consumes("application/json")
	public void promoteEvent(@PathParam("eventId") Long eventId) {
		
		System.out.println("Event to promote: "+eventId);
		EventServices repository = ServiceFactory.createEventServices();
		
		Event e = repository.getEventById(eventId);
		System.out.println("Event to promote: "+e.getTitle());
		repository.addPromotion(e);
	}

	@DELETE
	@Path("{eventId}")
	@Consumes("application/json")
	public void deletePromotion(@PathParam("eventId") Long eventId) {

		EventServices repository = ServiceFactory.createEventServices();

		Event e = repository.getEventById(eventId);

		repository.removePromotion(e);
	}
}
