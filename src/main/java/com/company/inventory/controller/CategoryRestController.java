package com.company.inventory.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.inventory.model.Category;
import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.services.ICategoryService;
import com.company.inventory.util.CategoryExcelExporter;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*") 
//@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {
	
	@Autowired
	private ICategoryService service;
	
	/**
	 * get all the categories
	 * @return
	 */
	
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponseRest> searchCategories() {
		
		ResponseEntity<CategoryResponseRest> response = service.search();
		return response;
		
	}
	
	/**
	 * get categories by ID  
	 * @return
	 */
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> searchCategoriesById(@PathVariable Long id) {
		
		ResponseEntity<CategoryResponseRest> response = service.searchById(id);
		return response;
		
	} 

/**
 * Save categories  
 * @return
 */

@PostMapping("/categories")
public ResponseEntity<CategoryResponseRest> save(@RequestBody Category category) {
	
	ResponseEntity<CategoryResponseRest> response = service.save(category);
	return response;
	
}
/**
 * Upadate categories  
 * @return
 */

@PutMapping("/categories/{id}")
public ResponseEntity<CategoryResponseRest> update(@RequestBody Category category, @PathVariable Long id) {
	
	ResponseEntity<CategoryResponseRest> response = service.update(category, id);
	return response;
}
/**
 * Delete categories  
 * @return
 */
@DeleteMapping("/categories/{id}")
public ResponseEntity<CategoryResponseRest> delete(@PathVariable Long id) {
	
	ResponseEntity<CategoryResponseRest> response = service.deleteById(id);
	return response;

}


/**
 * Export a Excel  
 * @return
 */
@GetMapping("/categories/export/excel")
public void exporToExcel(HttpServletResponse response) throws IOException {
	response.setContentType("application/octet-stream");
	
	String headerKey = "Content-Disposition";
	String headerValue = "attachment; filename=result_category.xlsx";
	response.setHeader(headerKey, headerValue);
	
	ResponseEntity<CategoryResponseRest> categoryResponse = service.search();
	
	
	CategoryExcelExporter excelExporter = new CategoryExcelExporter(
			categoryResponse.getBody().getCategoryResponse().getCategory());
	
	excelExporter.export(response);
	
	
}

}


















