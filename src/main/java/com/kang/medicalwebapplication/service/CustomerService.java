package com.kang.medicalwebapplication.service;

import org.springframework.stereotype.Service;

import com.kang.medicalwebapplication.model.Customer;
import com.kang.medicalwebapplication.repository.CustomerRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CustomerService {
	private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository=customerRepository;
	}
	
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Iterable<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}
	
	public void deleteCustomerById(String id) {
		customerRepository.deleteById(id);
	}
	
	public Optional<Customer> findCustomerBiId(String id){
		return customerRepository.findById(id);
	}
}
