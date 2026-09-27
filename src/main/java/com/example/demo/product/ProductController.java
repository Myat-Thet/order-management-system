package com.example.demo.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
	
	
	private final ProductService proService;
	
	@GetMapping
	public String createCustomer(Model model) {		
		model.addAttribute("product", new Product());
		return "product-form";
	}

	@PostMapping("/save")
	public String save(Model model, Product product) {		
		model.addAttribute("product", proService.addProduct(product));
		return "redirect:/product/view";
	}
	
	@GetMapping("/view")
	public String viewAll(Model model) {		
		model.addAttribute("products", proService.viewAll());
		long totalProductCount = proService.getProductCount();
		model.addAttribute("productCount", totalProductCount);
		return "product";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model) {
		Product product= proService.findById(id).orElseThrow(() -> new RuntimeException("Not found Customer"));
		model.addAttribute("product", product);
		return "product-form";
	}
	
	@PostMapping("/update/{id}")
	public String update(@PathVariable Long id, Product product) {
		proService.update(id);
		return "redirect:/product/view";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		proService.delete(id);
		return "redirect:/product/view";
	}

}
