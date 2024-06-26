package com.company.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventory.dao.ICategoryDao;
import com.company.inventory.dao.IProductDao;
import com.company.inventory.model.Category;
import com.company.inventory.model.Product;
import com.company.inventory.response.ProductResponse;
import com.company.inventory.response.ProductResponseRest;
import com.company.inventory.response.ResponceRest;
import com.company.inventory.util.Util;

@Service
public class ProductServicesImpl implements IProductService {
	
	private ICategoryDao categoryDao;
	private IProductDao productDao;

	public ProductServicesImpl(ICategoryDao categoryDao, IProductDao productDao ) {
		super();
		this.categoryDao = categoryDao;
		this.productDao = productDao;
	}

	@Override
	@Transactional
	
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
			Product productSaved = productDao.save(product);
			
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

	@Override
	@Transactional (readOnly = true)
	public ResponseEntity<ProductResponseRest> searchById(long id) {
		
		ProductResponseRest response =new ProductResponseRest();
		List<Product> list = new ArrayList<>();
			
		try {
			
			//buscar productos por ID
			Optional<Product> product = productDao.findById(id);
			
			if (product.isPresent()) {
				
				//byte[] imageDescompressed = Util.decompressZLib(product.get().getPicture());
				//product.get().setPicture(imageDescompressed);
				list.add(product.get());
				response.getProduct().setProducts(list);
				response.setMetadata("respuesta positiva", "00", "Producto encontrado");
				
			} else {
				response.setMetadata("respuesta negativa", "-1", "Producto no encontrado");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			
		} catch (Exception e) {
			
			e.getStackTrace();
			response.setMetadata("respuesta negativa", "-1", "Error al guardar producto");
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		
	
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional (readOnly = true)
	public ResponseEntity<ProductResponseRest> searchByName(String name) {
		
		ProductResponseRest response =new ProductResponseRest();
		List<Product> list = new ArrayList<>();
		
		try {
			
			//buscar productos por Nombre
			list = productDao.findByNameContainingIgnoreCase(name);
			
			if (list.size() > 0) {
				
				/*listAux.stream().forEach( (p) -> {
					byte[] imageDescompressed = Util.decompressZLib(p.getPicture());
					p.setPicture(imageDescompressed);
					list.add(p);
				});*/
				
				
				response.getProduct().setProducts(list);
				response.setMetadata("respuesta positiva", "00", "Nombre del producto encontrado");
				
			} else {
				response.setMetadata("respuesta negativa", "-1", "Producto no encontrado");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			
		} catch (Exception e) {
			
			e.getStackTrace();
			response.setMetadata("respuesta negativa", "-1", "Error al buscar producto");
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		
	
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest> deleteById(long id) {
		ProductResponseRest response =new ProductResponseRest();
			
		try {
			
			//eliminar productos por ID
			productDao.deleteById(id);
			response.setMetadata("respuesta positiva", "00", "Producto eliminado");
			
			
		} catch (Exception e) {
			
			e.getStackTrace();
			response.setMetadata("respuesta negativa", "-1", "Error al eliminar producto");
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		
	
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest> search() {
	    List<Product> list = new ArrayList<>();
	    
	    ProductResponseRest response = new ProductResponseRest();

	    try {
	        // Buscar productos
	        list = (List<Product>) productDao.findAll();

	        if (list.size() > 0) {
	        	 /* listAux.forEach((p) -> {
	                byte[] imageDescompressed = Util.decompressZLib(p.getPicture());
	                p.setPicture(imageDescompressed);
	                list.add(p);
	            }); */

	            
	            response.getProduct().setProducts(list);
	            response.setMetadata("respuesta positiva", "00", "Producto encontrado");

	        } else {
	            response.setMetadata("respuesta negativa", "-1", "Producto no encontrado");
	            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
	        	//ProductResponse productResponse = new ProductResponse();
	        	//productResponse.setProducts(new ArrayList<>());
	        	//response.setProduct(productResponse);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        response.setMetadata("respuesta negativa", "-1", "Error al buscar producto");
	        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest> update(Product product, Long categoryId, Long id) {
		ProductResponseRest response =new ProductResponseRest();
		List<Product> list = new ArrayList<>();
			
		try {
			
			
			//actualizar producto
			Optional<Category> category = categoryDao.findById(categoryId);
			
			if (category.isPresent()) {
				product.setCategory(category.get());
			} else {
				response.setMetadata("respuesta negativa", "-1", "Categoria no asociada al producto");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			//buscar producto y actualizarlo
			Optional<Product> productSearch = productDao.findById(id);
			
			if (productSearch.isPresent())  {
				
				productSearch.get().setAccount(product.getAccount());
				productSearch.get().setCategory(product.getCategory());
				productSearch.get().setName(product.getName());
				productSearch.get().setPicture(product.getPicture());
				productSearch.get().setPrice(product.getPrice());
				
				Product productToUpdate = productDao.save(productSearch.get());
				
				if (productToUpdate != null) {
					list.add(productToUpdate);
					response.getProduct().setProducts(list);
					response.setMetadata("respuesta positiva", "00", "Producto actualizado");
				}
				
			} else {
				response.setMetadata("respuesta negativa", "-1", "Producto no actualizado");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			
			e.getStackTrace();
			response.setMetadata("respuesta negativa", "-1", "Error al actualizar producto");
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}
	
}
