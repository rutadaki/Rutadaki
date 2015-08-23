package org.fewnuts.rutadaki.api;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.fewnuts.rutadaki.domain.Category;
import org.fewnuts.rutadaki.services.CategoryServices;
import org.fewnuts.rutadaki.services.ServiceFactory;

@Path("/categories")
public class CategoryService {
	
	@GET
	@Produces("application/json")
	public Category[] getCategories() {
		 
		CategoryServices categoryRepository = ServiceFactory.createCategoryServices();
		
		Set<Category> categories = categoryRepository.getAll();
		
		Category[] result = categories.toArray(new Category[categories.size()]);
		
		return result;
	}
	
}
