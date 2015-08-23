package org.fewnuts.rutadaki.services.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.fewnuts.rutadaki.domain.Category;
import org.fewnuts.rutadaki.domain.Event;
import org.fewnuts.rutadaki.domain.EventPromotion;
import org.fewnuts.rutadaki.domain.LongEvent;
import org.fewnuts.rutadaki.domain.PuntualEvent;
import org.fewnuts.rutadaki.persistence.dao.CategoryDao;
import org.fewnuts.rutadaki.persistence.dao.DaoFactory;
import org.fewnuts.rutadaki.persistence.dao.DaoFactoryBuilder;
import org.fewnuts.rutadaki.persistence.dao.EventDao;
import org.fewnuts.rutadaki.persistence.dao.EventPromotionDao;
import org.fewnuts.rutadaki.persistence.dao.LongEventDao;
import org.fewnuts.rutadaki.persistence.dao.PuntualEventDao;
import org.fewnuts.rutadaki.services.EventServices;

public class EventServicesImpl implements EventServices {

	public List<Event> getEventsDay(int day, int month, int year) {
		DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();

		daoFactory.beginConectionScope();
		daoFactory.beginTransaction();

		PuntualEventDao puntualDao = daoFactory.createPuntualEventDao();
		LongEventDao longDao = daoFactory.createLongEventDao();

		List<PuntualEvent> puntualEvents = puntualDao.getPuntualEventsByDay(
				day, month, year);
		List<LongEvent> longEvents = longDao.getLongEventsByDay(day, month,
				year);

		List<Event> result = new LinkedList<Event>();
		for (PuntualEvent e : puntualEvents) {
			result.add(e);
		}
		for (LongEvent e : longEvents) {
			result.add(e);
		}
		daoFactory.commitTransaction();
		daoFactory.endConectionScope();

		return result;
	}

	public List<Event> getEventsMonth(int month, int year) {
		DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();

		daoFactory.beginConectionScope();
		daoFactory.beginTransaction();

		PuntualEventDao puntualDao = daoFactory.createPuntualEventDao();
		LongEventDao longDao = daoFactory.createLongEventDao();

		List<PuntualEvent> puntualEvents = puntualDao.getPuntualEventsByMonth(
				month, year);
		List<LongEvent> longEvents = longDao.getLongEventsByMonth(month, year);

		List<Event> result = new LinkedList<Event>();
		for (PuntualEvent e : puntualEvents) {
			result.add(e);
		}
		for (LongEvent e : longEvents) {
			result.add(e);
		}
		daoFactory.commitTransaction();
		daoFactory.endConectionScope();

		return result;
	}

	public void deleteEvent(Event e) {
		DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();

		daoFactory.beginConectionScope();
		daoFactory.beginTransaction();

		if (e instanceof PuntualEvent) {
			PuntualEventDao dao = daoFactory.createPuntualEventDao();
			dao.delete((PuntualEvent) e);
		}

		if (e instanceof LongEvent) {
			LongEventDao dao = daoFactory.createLongEventDao();
			dao.delete((LongEvent) e);
		}

		daoFactory.commitTransaction();
		daoFactory.endConectionScope();
	}

	public void saveEvent(Event e) {
		DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();

		daoFactory.beginConectionScope();
		daoFactory.beginTransaction();

		if (e instanceof PuntualEvent) {
			PuntualEventDao dao = daoFactory.createPuntualEventDao();
			dao.update((PuntualEvent) e);
		}

		if (e instanceof LongEvent) {
			LongEventDao dao = daoFactory.createLongEventDao();
			dao.update((LongEvent) e);
		}

		daoFactory.commitTransaction();
		daoFactory.endConectionScope();
	}

	public PuntualEvent getPuntualEvent(Long eventId) {
		DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();

		daoFactory.beginConectionScope();
		daoFactory.beginTransaction();

		PuntualEventDao dao = daoFactory.createPuntualEventDao();
		PuntualEvent result = null;
		result = dao.read(eventId);

		daoFactory.commitTransaction();
		daoFactory.endConectionScope();

		return result;
	}

	public LongEvent getLongEvent(Long eventId) {
		DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();

		daoFactory.beginConectionScope();
		daoFactory.beginTransaction();

		LongEventDao dao = daoFactory.createLongEventDao();
		LongEvent result = null;
		result = dao.read(eventId);

		daoFactory.commitTransaction();
		daoFactory.endConectionScope();

		return result;
	}

	public List<Event> getOnComingEventsByCategory(Long categoryId) {
		DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();

		daoFactory.beginConectionScope();
		daoFactory.beginTransaction();

		PuntualEventDao puntualDao = daoFactory.createPuntualEventDao();
		LongEventDao longDao = daoFactory.createLongEventDao();
		CategoryDao categoryDao = daoFactory.createCategoryDao();

		Category category = categoryDao.read(categoryId);

		List<PuntualEvent> puntualEvents = puntualDao
				.getOnComingEventsByCategory(category);
		List<LongEvent> longEvents = longDao
				.getOnComingEventsByCategory(category);
		List<Event> result = new LinkedList<Event>();
		for (PuntualEvent e : puntualEvents) {
			result.add(e);
		}
		for (LongEvent e : longEvents) {
			result.add(e);
		}
		daoFactory.commitTransaction();
		daoFactory.endConectionScope();

		return result;
	}

	public List<PuntualEvent> getAllPuntualEvents() {
		DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();

		daoFactory.beginConectionScope();
		daoFactory.beginTransaction();

		PuntualEventDao puntualDao = daoFactory.createPuntualEventDao();

		List<PuntualEvent> puntualEvents = puntualDao.getAll();

		daoFactory.commitTransaction();
		daoFactory.endConectionScope();

		return puntualEvents;
	}

	public List<LongEvent> getAllLongEvents() {
		DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();

		daoFactory.beginConectionScope();
		daoFactory.beginTransaction();

		LongEventDao dao = daoFactory.createLongEventDao();

		List<LongEvent> result = dao.getAll();

		daoFactory.commitTransaction();
		daoFactory.endConectionScope();

		return result;
	}

	public List<Event> searchByText(String text_search) {
		DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();

		daoFactory.beginConectionScope();
		daoFactory.beginTransaction();

		LongEventDao longDao = daoFactory.createLongEventDao();
		PuntualEventDao puntualDao = daoFactory.createPuntualEventDao();

		List<Event> result = new LinkedList<Event>();
		String[] tokens = text_search.split(" ");
		for (int i = 0; i < tokens.length; i++) {
			System.out.println("token: " + tokens[i]);
			result.addAll(longDao.searchByText(tokens[i]));
			result.addAll(puntualDao.searchByText(tokens[i]));
		}

		daoFactory.commitTransaction();
		daoFactory.endConectionScope();

		return result;
	}

	public Event getEventById(Long idEvent) {
		DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();

		daoFactory.beginConectionScope();
		daoFactory.beginTransaction();

		EventDao dao = daoFactory.createEventDao();
		Event result = null;
		result = dao.read(idEvent);

		daoFactory.commitTransaction();
		daoFactory.endConectionScope();

		return result;
	}

	public List<Event> getOutStandingEvents() {
		DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();

		daoFactory.beginConectionScope();
		daoFactory.beginTransaction();

		EventDao dao = daoFactory.createEventDao();

		List<Event> result = dao.getPromotedEvents(new Date());

		daoFactory.commitTransaction();
		daoFactory.endConectionScope();

		return result;
	}

	public void addPromotion(Event e) {
		if (!getOutStandingEvents().contains(e)) {
			
			DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();

			daoFactory.beginConectionScope();
			daoFactory.beginTransaction();

			EventPromotionDao dao = daoFactory.createEventPromotionDao();

			dao.create(new EventPromotion(e, null, null));

			daoFactory.commitTransaction();
			daoFactory.endConectionScope();
			
		}

	}

	public void removePromotion(Event e) {
		DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();

		daoFactory.beginConectionScope();
		daoFactory.beginTransaction();

		EventPromotionDao dao = daoFactory.createEventPromotionDao();
		
		List<EventPromotion> promotions = dao.getPromotionsEvent(e);
		
		for(EventPromotion ep : promotions){
			dao.delete(ep);
		}

		daoFactory.commitTransaction();
		daoFactory.endConectionScope();
		
	}

}
