package com.example.demo.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerService;
import com.example.demo.product.Product;
import com.example.demo.product.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

	private final OrderService orderService;
	private final CustomerService cusService;
	private final ProductService proService;
	
	@GetMapping
	public String createOrder(Model model) {
		model.addAttribute("order", new Order());
		model.addAttribute("customers", cusService.viewAll());
		model.addAttribute("products", proService.viewAll());
		return "order-form";
	}
	
	@PostMapping("/save")
	public String saveOrder(Model model, Order order, @RequestParam Long customerId, @RequestParam Long productId) {		
		model.addAttribute("order",  orderService.addOrder(order, customerId, productId));
		return "redirect:/order/view";
	}
	
	@GetMapping("/view")
	public String viewAll(Model model) {
		model.addAttribute("orders", orderService.viewAll());
		
		long orderCount = orderService.getOrderCount();
		model.addAttribute("orderCount", orderCount);		
		return "order";
	}
	
	@GetMapping("/view/{id}")
	public String viewOrderById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("order", orderService.viewOrderById(id));
		return "order-detail";
	}
	
	@PostMapping("/delete/{id}")
	public String deleteOrder(@PathVariable("id") Long id) {
		orderService.delete(id);
		return "redirect:/order/view";
	}
	
	@GetMapping("/viewOrdersByCustomer")
	public String viewOrdersByCustomer(@RequestParam("customer")String customer, Model model) {
		model.addAttribute("order", orderService.findOrdersByCustomer(customer));
		return "order";
	}
}
