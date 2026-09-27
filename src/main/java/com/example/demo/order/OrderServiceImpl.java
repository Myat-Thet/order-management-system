package com.example.demo.order;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerDao;
import com.example.demo.product.Product;
import com.example.demo.product.ProductDao;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

	private final OrderDao orderDao;
	private final CustomerDao cusDao;
	private final ProductDao proDao;

	@Transactional
	@Override
	public Order addOrder(Order order, Long customerId, Long productId) {
		Customer customer = cusDao.findById(customerId).orElseThrow(() -> new IllegalArgumentException("There is no Customer"));
		Product product = proDao.findById(productId).orElseThrow(() -> new IllegalArgumentException("There is no Product"));
		
		if(order.getQuantity() > product.getStock()) {
			throw new IllegalArgumentException("RemaingStock is insufficient");
		}
		
		order.setCustomer(customer);
		order.setProduct(product);
		double totalAmount = product.getPrice() * order.getQuantity();
		order.setTotalAmount(totalAmount);

		double remaingStock = product.getStock() - order.getQuantity();
		product.setStock(remaingStock);		
		proDao.save(product);
		return orderDao.save(order);
	}

	@Override
	public List<Order> viewAll() {
		return orderDao.findAll();
	}

	@Override
	public Optional<Order> viewOrderById(Long id) {
		return orderDao.findById(id);
	}

	@Override
	public void delete(Long id) {
		orderDao.deleteById(id);
	}

	@Override
	public List<Order> findOrdersByCustomer(String customer) {
		return orderDao.findOrdersByCustomer(customer);
	}
	
	@Override
	public Long getOrderCount() {
		return orderDao.count();
	}
	
}
