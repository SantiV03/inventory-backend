package com.company.inventory.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.inventory.model.Product;
import com.company.inventory.response.ProductResponseRest;
import com.company.inventory.services.IProductService;
import com.company.inventory.util.Util;


@CrossOrigin(origins = {"http://localhost:4200"} )
@RestController
@RequestMapping("/api/v1")
public class ProductRestController {
	
	private IProductService procutService;
	
	
	
	public ProductRestController(IProductService procutService) {
		super();
		this.procutService = procutService;
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
				product.setPicture(Util.compressZLib(picture.getBytes()));
				
				ResponseEntity<ProductResponseRest> response = procutService.save(product, categoryID);
				
				
				return response;
		
	}

}