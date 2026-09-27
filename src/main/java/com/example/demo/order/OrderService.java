package com.example.demo.order;

import java.util.List;
import java.util.Optional;

import com.example.demo.customer.Customer;
import com.example.demo.product.Product;

public interface OrderService {

	Order addOrder(Order order, Long customerId, Long productId);
	List<Order> viewAll();
	Optional<Order> viewOrderById(Long id);
	void delete(Long id);
	List<Order> findOrdersByCustomer(String customer);
	
	Long getOrderCount();
}
