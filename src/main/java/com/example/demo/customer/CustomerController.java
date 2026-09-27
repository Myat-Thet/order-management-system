package com.example.demo.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController { 
	
	private final CustomerService cusService;
	@GetMapping
	public String createCustomer(Model model) {		
		model.addAttribute("customer", new Customer());
		return "customer-form";
	}

	@PostMapping("/save")
	public String save(Model model, Customer customer) {		
		model.addAttribute("customer", cusService.addCustomer(customer));
		return "redirect:/customer/view";
	}
	
	@GetMapping("/view")
	public String viewAll(Model model) {		
		model.addAttribute("customers", cusService.viewAll());
		return "customer";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model) {
		Customer customer= cusService.findById(id).orElseThrow(() -> new RuntimeException("Not found Customer"));
		model.addAttribute("customer", customer);
		return "customer-form";
	}
	
	@PostMapping("/update/{id}")
	public String update(@PathVariable Long id, Customer customer) {
		cusService.update(id);
		return "redirect:/customer/view";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		cusService.delete(id);
		return "redirect:/customer/view";
	}
}
