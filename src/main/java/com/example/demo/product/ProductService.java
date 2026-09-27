package com.example.demo.product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
	
		Product addProduct(Product product);
		List<Product> viewAll();
		Optional<Product> update(Long id);
		void delete(Long id);
		Optional<Product> findById(Long id);
		Long getProductCount();
		
}
