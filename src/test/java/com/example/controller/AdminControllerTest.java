package com.example.controller;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.model.Customer;
import com.example.model.Login;
import com.example.repo.AdminRepository;
import com.example.repo.CustomerRepository;
import com.example.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
 
@ExtendWith(MockitoExtension.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {
 
	@Autowired
	private MockMvc mockMvc;
 
	@MockBean
	private AdminService adminService;
	@MockBean
	private CustomerRepository customerRepository;
	@MockBean
	private AdminRepository adminRepository;
	@InjectMocks
	private AdminController adminController;
 
	private Customer cust1;
	private Customer cust2;
	private Login login;
 
	@BeforeEach
	void setUp() {
		cust1 = new Customer();
		cust1.setId(1);
		cust1.setName("Rohit");
		cust1.setUsername("hitman");
		cust1.setPassword("1234");
		cust1.setAge(36);
		cust1.setSsn("1234567890");
		cust1.setAddress("Mumbai");
		cust1.setEmail("hitman@mi.com");
		cust1.setPhone("9123456789");
		cust1.setBalance(100000);
		cust1.setIsadmin(false);
 
		cust2 = new Customer();
		cust2.setId(2);
		cust2.setName("Virat");
		cust2.setUsername("king");
		cust2.setPassword("1234");
		cust2.setAge(35);
		cust2.setSsn("8234567890");
		cust2.setAddress("Bangalore");
		cust2.setEmail("virat@rcb.com");
		cust2.setPhone("8123456789");
		cust2.setBalance(100000);
		cust2.setIsadmin(false);
		
		login = new Login();
		login.setUsername("Rohit");
		login.setPassword("1234");
	}
 
	@Test
	void testGetAllCustomers() throws Exception {
		List<Customer> Customers = Arrays.asList(cust1, cust2);
		when(adminService.getAllCustomers()).thenReturn(Customers);
 
		mockMvc.perform(get("/getallcustomers")).andExpect(status().isOk()).andExpect(jsonPath("$[0].name").value("Rohit"))
				.andExpect(jsonPath("$[1].name").value("Virat"));
 
	}
 
	@Test
	void testGetOneCustomer() throws Exception {
		when(adminService.getCustomerById(1)).thenReturn(Optional.of(cust1));
 
		mockMvc.perform(get("/getonecustomer/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Rohit"));
	}
 
	@Test
	void testAddcustomer() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String CustomerJson = objectMapper.writeValueAsString(cust1);
 
		mockMvc.perform(post("/addcustomer").contentType(MediaType.APPLICATION_JSON).content(CustomerJson))
				.andExpect(status().isOk());
 
		verify(adminService).addcustomer(cust1);
	}
 
	@Test
	void testUpdateCustomer() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String CustomerJson = objectMapper.writeValueAsString(cust1);
 
		mockMvc.perform(put("/updatecustomer/1").contentType(MediaType.APPLICATION_JSON).content(CustomerJson))
				.andExpect(status().isOk());
 
		verify(adminService).updateCustomer(1, cust1);
	}
	
	@Test
	void testDeleteCustomer() throws Exception{
		mockMvc.perform(delete("/deletecustomer/1"))
		.andExpect(status().isOk());
		
		verify(adminService).deleteCustomer(1);
	}
 
	
	@Test
	void testLogin() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String LoginJson = objectMapper.writeValueAsString(login);
 
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(LoginJson))
				.andExpect(status().isOk());
 
		verify(adminService).login(login);
	}
 
}
 