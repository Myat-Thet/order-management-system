package com.example.demo.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

	Customer addCustomer(Customer customer);
	List<Customer> viewAll();
	Optional<Customer> update(Long id);
	void delete(Long id);
	Optional<Customer> findById(Long id);
}
