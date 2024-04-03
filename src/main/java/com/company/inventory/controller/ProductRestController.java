package com.company.inventory.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.inventory.model.Product;
import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.response.ProductResponseRest;
import com.company.inventory.services.IProductService;
import com.company.inventory.util.CategoryExcelExporter;
import com.company.inventory.util.ProductExcelExport;
import com.company.inventory.util.Util;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1")
public class ProductRestController {
	
	private IProductService productService;
	
	
	
	public ProductRestController(IProductService productService) {
		super();
		this.productService = productService;
	}



	@PostMapping("/products")
	public ResponseEntity<ProductResponseRest> save(
			@RequestParam("picture") MultipartFile picture,
			@RequestParam("name") String name,
			@RequestParam("price") int price,
			@RequestParam("account") int aacount,
			@RequestParam("categoryId") Long categoryID) throws IOException
			
			{
		
				Product product = new Product();
				product.setName(name);
				product.setAccount(aacount);
				product.setPrice(price);
				product.setPicture(Base64.getEncoder().encodeToString(picture.getBytes()));
				
				ResponseEntity<ProductResponseRest> response = productService.save(product, categoryID);
				
				
				return response;
		
	}
	
	
	/**
	 * buscar producto por ID
	 * @param id
	 * @return
	 */
	@GetMapping("/products/{id}") 
	public ResponseEntity<ProductResponseRest> searchById(@PathVariable Long id){
		ResponseEntity<ProductResponseRest> response = productService.searchById(id);
		return response;
		
	}
	
	/**
	 * buscar producto por NOMBRE
	 * @param id
	 * @return
	 */
	@GetMapping("/products/filter/{name}") 
	public ResponseEntity<ProductResponseRest> searchByName(@PathVariable String name){
		ResponseEntity<ProductResponseRest> response = productService.searchByName(name);
		return response;
		
	}
	
	
	/**
	 * eliminar producto por Id
	 * @param id
	 * @return
	 */
	@DeleteMapping("/products/{id}") 
	public ResponseEntity<ProductResponseRest> deleteById(@PathVariable Long id){
		ResponseEntity<ProductResponseRest> response = productService.deleteById(id);
		return response;
		
	}
	
	/**
	 * buscar productos
	 * @param id
	 * @return
	 */
	@GetMapping("/products") 
	public ResponseEntity<ProductResponseRest> search(){
		ResponseEntity<ProductResponseRest> response = productService.search();
		
		return response;
		
	}
	/**
	 * Actualizar productos
	 * 
	 */
	
	@PutMapping ("/products/{id}")
	public ResponseEntity<ProductResponseRest> update(
			@RequestParam("picture") MultipartFile picture,
			@RequestParam("name") String name,
			@RequestParam("price") int price,
			@RequestParam("account") int aacount,
			@RequestParam("categoryId") Long categoryID,
			@PathVariable Long id) throws IOException
			
			{
		
				Product product = new Product();
				product.setName(name);
				product.setAccount(aacount);
				product.setPrice(price);
				product.setPicture(Base64.getEncoder().encodeToString(picture.getBytes()));
				
				ResponseEntity<ProductResponseRest> response = productService.update(product, categoryID, id);
				
				
				return response;
				
	}
	
	/**
	 * Exportar a excel
	 * 
	 */
	
	@GetMapping("/products/export/excel")
	public void exporToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=result_product.xlsx";
		response.setHeader(headerKey, headerValue);
		
		ResponseEntity<ProductResponseRest> products = productService.search();
		
		
		ProductExcelExport excelExporter = new ProductExcelExport(
				products.getBody().getProduct().getProducts());
		
		excelExporter.export(response);
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
