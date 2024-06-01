package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query(value = "select * from customer where username = ?1 and password = ?2", nativeQuery = true)
	public Customer login(String username, String password);
	
	@Query(value = "select * from customer", nativeQuery = true)
	public List<Customer> getAllCustomers();
	
	@Query(value = "select * from customer c where c.id = ?", nativeQuery = true)
	public Customer getOneCustomer(int id) ;
}
