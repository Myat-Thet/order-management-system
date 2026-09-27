package com.example.demo.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderDao extends JpaRepository<Order, Long>{

	@Query("select o from Order as o where o.customer = :customer")
	List<Order> findOrdersByCustomer(@Param("customer")String customer);
}
