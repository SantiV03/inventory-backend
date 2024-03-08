package com.company.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.inventory.dao.ICategoryDao;
import com.company.inventory.dao.IProductDao;
import com.company.inventory.model.Category;
import com.company.inventory.model.Product;
import com.company.inventory.response.ProductResponseRest;
import com.company.inventory.response.ResponceRest;

@Service
public class ProductServicesImpl implements IProductService {
	
	private ICategoryDao categoryDao;
	private IProductDao propductDao;

	public ProductServicesImpl(ICategoryDao categoryDao, IProductDao propductDao ) {
		super();
		this.categoryDao = categoryDao;
		this.propductDao = propductDao;
	}

	@Override
	public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId) {
		
		
		ProductResponseRest response =new ProductResponseRest();
		List<Product> list = new ArrayList<>();
			
		try {
			
			//buscar categorias para setearla en el objeto producto
			Optional<Category> category = categoryDao.findById(categoryId);
			
			if (category.isPresent()) {
				product.setCategory(category.get());
			} else {
				response.setMetadata("respuesta negativa", "-1", "Categoria no asociada al producto");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			//guardar producto
			Product productSaved = propductDao.save(product);
			
			if (productSaved != null) {
				list.add(productSaved);
				response.getProduct().setProducts(list);
				response.setMetadata("respuesta positiva", "00", "Producto guardado");
			} else {
				response.setMetadata("respuesta negativa", "-1", "Producto no guardado");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			
			e.getStackTrace();
			response.setMetadata("respuesta negativa", "-1", "Error al guardar producto");
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		
	
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}

}
