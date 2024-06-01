package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.model.Admin;
import com.example.model.Customer;
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

	public Admin login(String username, String password) {
		return cr.login(username, password);
	}

	public void depositCheck(int id, float amount) {
		Optional<Admin> cust = cr.findById(id);
		float bal = amount + cust.get().getBalance();
		cust.get().setBalance(bal);
		cr.save(cust.get());

	}

	public void withdrawalCheck(int id, float amount) {
		Optional<Admin> cust = cr.findById(id);
		float bal = cust.get().getBalance() - amount;
		cust.get().setBalance(bal);
		cr.save(cust.get());

	}

	public float checkBalance(@RequestBody int id) {
		Optional<Admin> cust = cr.findById(id);
		return cust.get().getBalance();
	}

	public void changePassword(int id, String password) {
		Optional<Admin> cust = cr.findById(id);

		cust.get().setPassword(password);
		cr.save(cust.get());
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
		Customer cust = ar.getOneCustomer(id);
		return cust;
	}
	
	public void deleteCustomer(int id){

		ar.deleteById(id);
	}
	
	
}
