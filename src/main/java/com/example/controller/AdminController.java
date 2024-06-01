package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Admin;
import com.example.model.Customer;
import com.example.model.Login;
import com.example.service.AdminService;

@CrossOrigin(origins = "*")
@RestController
public class AdminController {
	@Autowired
	RestTemplate rs;

	@Autowired
	AdminService cs;

	@PostMapping("/login")
	public Admin login(@RequestBody Login obj) {

		Admin admin = cs.login(obj.getUsername(), obj.getPassword());

		return admin;

	}
	
	@PostMapping("/addcustomer")
	public Customer addcustomer(@RequestBody Customer obj) {

		Customer cust = cs.addcustomer(obj);

		return cust;

	}


	@PutMapping("/updatecustomer/{id}")
	public Customer updateCustomer(@PathVariable int id, @RequestBody Customer cust) throws ResourceNotFoundException {
		Optional.ofNullable(cs.getCustomerById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for the id " + id)));

		cs.updateCustomer(id, cust);
		return cust;
	}

	@GetMapping("/getallcustomers")
	public List<Customer> getAllCustomers() {
		return cs.getAllCustomers();
	}

	@GetMapping("/getonecustomer/{id}")
	public Customer getOneCustomer(@PathVariable int id) throws ResourceNotFoundException {

		Optional.ofNullable(cs.getCustomerById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for the id " + id)));

		return cs.getOneCustomer(id);
	}
	
	@DeleteMapping("/deletecustomer/{id}")
	public void deleteCustomer(@PathVariable int id) throws ResourceNotFoundException {

		Optional.ofNullable(cs.getCustomerById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for the id " + id)));

		cs.deleteCustomer(id);
	}

}
