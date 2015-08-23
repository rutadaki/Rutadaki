package org.fewnuts.rutadaki.api;

import java.util.Collections;
import java.util.LinkedList;
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

@Path("/events")
public class EventService {

	@GET
	@Produces("application/json")
	public Event[] getEvents() {

		EventServices repository = ServiceFactory.createEventServices();
		CategoryServices categoryRepository = ServiceFactory
				.createCategoryServices();

		//Set<Category> categories = categoryRepository.getAll();
		//SortedSet<Event> eventos = new TreeSet<Event>(/*new EventDateComparator()*/);
		
		List<Event> eventos = new LinkedList<Event>();
		
		List<PuntualEvent> eventosUnDia = repository.getAllPuntualEvents();
		List<LongEvent> eventosLargos = repository.getAllLongEvents();

		eventos.addAll(eventosLargos);
		eventos.addAll(eventosUnDia);
		
		Collections.sort(eventos, new EventDateComparator());

		return eventos.toArray(new Event[eventos.size()]);
	}

	@POST
	@Consumes("application/json")
	public void createLongEvent(LongEvent le) {

		EventServices repository = ServiceFactory.createEventServices();

		repository.saveEvent(le);
	}

	@PUT
	@Consumes("application/json")
	public void saveEvent(String jsonStr) {
		
		JSONObject json = new JSONObject(jsonStr);
		
		Location location = jsonToLocation((JSONObject) json.get("location"));
		
		json.remove("location");
		
		Event e = null;
		Gson gson = new GsonBuilder()
		   .setDateFormat("yyyy-MM-dd").create();
		if (!json.has("date")) {
			e = gson.fromJson(json.toString(), LongEvent.class);
		} else {
			e = gson.fromJson(json.toString(), PuntualEvent.class);
		}
		
		e.setLocation(location);
		
		 System.out.println("Actualizando evento: " + e.getTitle());
		 EventServices repository = ServiceFactory.createEventServices();
		
		 repository.saveEvent(e);
		  
	}

	@DELETE
	@Path("{eventId}")
	@Consumes("application/json")
	public void deleteEvent(@PathParam("eventId") Long eventId) {
		
		
		
		EventServices repository = ServiceFactory.createEventServices();

		Event e = repository.getEventById(eventId);

		System.out.println("Deleting " + e.getTitle());
		
		repository.deleteEvent(e);
	}

	@GET
	@Path("{eventId}")
	@Produces("application/json")
	public Event getEvent(@PathParam("eventId") Long eventId) {
		System.out.println("Getting event");
		EventServices repository = ServiceFactory.createEventServices();

		Event e = repository.getEventById(eventId);
		
		if(e instanceof LongEvent){
			for(TimeTableEntry entry : ((LongEvent)e).getTimeTable().getEntries()){
				System.out.println("Entry "+entry.getId());
			}
		}

		System.out.println(e.getTitle());
		return e;
	}

	private LongEvent jsonToLongEvent(JSONObject json) {

		JSONObject locationJson = json.getJSONObject("location");

		return null;
	}

	private Location jsonToLocation(JSONObject json) {
		Location result = null;
		
		if(json.has("id")){
			// It is a stored location, we have to parse the JSON due to
			// embedded locationimpl
			
			result = new Gson().fromJson(json.toString(), StoredLocation.class);
			result.setAddress(json.getString("address"));
			result.setName(json.getString("name"));
			City city = new Gson().fromJson(json.get("city").toString(), City.class);
			result.setCity(city);
		}else{
			
			// Embedded Location Impl, Gson does it all
			result = new Gson().fromJson(json.toString(), LocationImpl.class);
		}
		
		return result;
	}
}
