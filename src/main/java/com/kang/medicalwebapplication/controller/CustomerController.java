package com.kang.medicalwebapplication.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kang.medicalwebapplication.model.Customer;
import com.kang.medicalwebapplication.service.CustomerService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class CustomerController {

		private CustomerService customerService;
		
		@Autowired
		public CustomerController(CustomerService customerService) {
			this.customerService = customerService;
		}
		
		@GetMapping("/customers-ui")
		public String customers(Model model) {
			model.addAttribute("customers",customerService.getAllCustomers());
			log.error("this is the customers page");
			return "/customers";
		}
		
//		@GetMapping("/customers")
//		public Iterable<Customer> getAllCustomers(){
//			return customerService.getAllCustomers();
//		}
		
		@GetMapping(value = {"/edit-add-customer/{id}", "/edit-add-customer"})
		public String editCustomer(@PathVariable("id") Optional<String> id, Model model) {
			Customer customer = id.isPresent()?customerService.findCustomerBiId(id.get()).get(): new Customer();
			model.addAttribute("customer",customer);
			return "add-edit";
		}
}
