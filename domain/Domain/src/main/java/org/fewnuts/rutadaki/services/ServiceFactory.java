package org.fewnuts.rutadaki.services;

import org.fewnuts.rutadaki.services.impl.CategoryServicesImpl;
import org.fewnuts.rutadaki.services.impl.EventServicesImpl;
import org.fewnuts.rutadaki.services.impl.LocationServicesImpl;

public class ServiceFactory {
	
	public static EventServices createEventServices(){
		return new EventServicesImpl();
	}
	
	public static LocationServices createLocationServices(){
		return new LocationServicesImpl();
	}
	
	public static CategoryServices createCategoryServices(){
		return new CategoryServicesImpl();
	}
}
