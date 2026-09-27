package com.example.demo.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
	
	private final CustomerDao cusDao;	
	
	@Override
	public Customer addCustomer(Customer customer) {
		return cusDao.save(customer);
	}

	@Override
	public List<Customer> viewAll() {
		return cusDao.findAll();
	}

	@Override
	public Optional<Customer> update(Long id) {
		return cusDao.findById(id);
	}

	@Override
	public void delete(Long id) {
		cusDao.deleteById(id);
		
	}

	@Override
	public Optional<Customer> findById(Long id) {
		return cusDao.findById(id);
	}
}
