package com.example.demo.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	
	private final ProductDao proDao;

	@Override
	public Product addProduct(Product product) {
		return proDao.save(product);
	}

	@Override
	public List<Product> viewAll() {
		return proDao.findAll();
	}

	@Override
	public Optional<Product> update(Long id) {
		return proDao.findById(id);
	}

	@Override
	public void delete(Long id) {
		proDao.deleteById(id);	
	}

	@Override
	public Optional<Product> findById(Long id) {
		return proDao.findById(id);
	}
	
	@Override
	public Long getProductCount() {
		return proDao.count();
	}

}
