package org.fewnuts.rutadaki.dummy_tests;

import java.util.List;

import org.fewnuts.rutadaki.domain.Category;
import org.fewnuts.rutadaki.domain.Event;
import org.fewnuts.rutadaki.persistence.dao.CategoryDao;
import org.fewnuts.rutadaki.services.CategoryServices;
import org.fewnuts.rutadaki.services.EventServices;
import org.fewnuts.rutadaki.services.ServiceFactory;

public class DummyTests {

	public static void main(String[] args) {
		
//		DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();
//		
//		daoFactory.beginConectionScope();
//		daoFactory.beginTransaction();
//		
//		CategoryDao categoryDao = daoFactory.createCategoryDao();
//		PuntualEventDao puntualDao = daoFactory.createPuntualEventDao();
//		LongEventDao longDao = daoFactory.createLongEventDao();
//		StoredLocationDao locationDao = daoFactory.createStoredLocationDao();
//		
//		//initCategories(categoryDao);
//		
//		daoFactory.commitTransaction();
//		daoFactory.endConectionScope();
		
		EventServices eventService = ServiceFactory.createEventServices();
		CategoryServices categoryService = ServiceFactory.createCategoryServices();
		List<Event> eventos = eventService.searchByText("wapo");
		
		for(Event e : eventos){
			System.out.println(e.getTitle() +": "+e.getDescription());
		}
	}

	private static void initCategories(CategoryDao categoryDao) {
		Category MUSICA = new Category("MUSICA");
		Category ESPECTACULOS = new Category("ESPECTACULOS");
		Category ARTE_CULTURA = new Category("ARTE Y CULTURA");
		Category SALIR = new Category("SALIR");
		Category ACTIVIDADES = new Category("ACTIVIDADES");
		categoryDao.create(MUSICA);
		categoryDao.create(ESPECTACULOS);
		categoryDao.create(ARTE_CULTURA);
		categoryDao.create(SALIR);
		categoryDao.create(ACTIVIDADES);
	}

}
