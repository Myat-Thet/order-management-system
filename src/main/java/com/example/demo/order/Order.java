package com.example.demo.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.customer.Customer;
import com.example.demo.product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor 
@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime orderDate;
	private int quantity;
	private double totalAmount;
	
	@ManyToOne
	private Customer customer;
	
	public void addCustomer(Customer customer) {
		this.customer = customer;
	}
		
	@ManyToOne
	private Product product;	
	public void addProduct(Product product) {
		this.product = product;
	}
	
	public Order(LocalDateTime orderDate,int quantity, double totalAmount, Customer customer, Product product) {
		super();
		this.orderDate = orderDate;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.customer = customer;
		this.product = product;
	}	
}
