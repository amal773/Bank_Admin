package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.model.Admin;
import com.example.model.Customer;
import com.example.model.Login;
import com.example.repo.AdminRepository;
import com.example.repo.CustomerRepository;

@Service
public class AdminService {
	@Autowired
	AdminRepository cr;
	
	@Autowired
	CustomerRepository ar;
	
	public Customer addcustomer(@RequestBody Customer cust) {

		ar.save(cust);

		return cust;

	}
	
	public Optional<Customer> getCustomerById(int id) {
		return ar.findById(id);
	}

	public Admin login(Login login) {
		return cr.login(login.getUsername(), login.getPassword());
	}

	

	public void updateCustomer(int id, Customer cust) {
		Optional<Customer> oldCustomer = ar.findById(id);
		oldCustomer.get().setAddress(cust.getAddress());
		oldCustomer.get().setAge(cust.getAge());
		oldCustomer.get().setEmail(cust.getEmail());
		oldCustomer.get().setPhone(cust.getPhone());		
		oldCustomer.get().setName(cust.getName());
		
		
		ar.save(oldCustomer.get());
	}
	
	public List<Customer> getAllCustomers(){
		return ar.getAllCustomers();
	}
	
	public Customer getOneCustomer(int id) {
		return ar.findById(id).get();
	}
	
	public void deleteCustomer(int id){

		ar.deleteById(id);
	}
	
	
}
