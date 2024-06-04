package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.model.Admin;
import com.example.model.Customer;
import com.example.model.Login;
import com.example.repo.AdminRepository;
import com.example.repo.CustomerRepository;
 
 
@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
 
	@Mock
	private CustomerRepository customerRepository;
	
	@Mock
	private AdminRepository adminRepository;
	
	@InjectMocks
	private AdminService adminService;
 
	private Customer cust1;
	private Customer cust2;
	private Login login;
	private Admin admin;
 
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
		
		admin = new Admin();
		admin.setId(1);
		admin.setName("Rohit");
		admin.setUsername("hitman");
		admin.setPassword("1234");
		admin.setAge(36);
		admin.setSsn("1234567890");
		admin.setAddress("Mumbai");
		admin.setEmail("hitman@mi.com");
		admin.setPhone("9123456789");
		admin.setBalance(100000);
		admin.setIsadmin(false);
		
		login = new Login();
		login.setUsername("Rohit");
		login.setPassword("1234");
 
	}
 
	

	@Test
	void testAddcustomer() {
		adminService.addcustomer(cust1);
		verify(customerRepository).save(cust1);
	}
	
	
	@Test
	void testUpdateCustomer() {
		Customer updatedCustomer = new Customer();
		updatedCustomer.setName("Updated Customer A");
		updatedCustomer.setAddress("Updated Address A");
		updatedCustomer.setAge(39);
		updatedCustomer.setEmail("rohitman@gmail.com");
		updatedCustomer.setPhone("1312345676");
		when(customerRepository.findById(1)).thenReturn(Optional.of(cust1));
		adminService.updateCustomer(1, updatedCustomer);
		assertEquals("Updated Customer A", cust1.getName());
		assertEquals("Updated Address A", cust1.getAddress());
		assertEquals("1312345676", cust1.getPhone());
		assertEquals(39, cust1.getAge());
		verify(customerRepository).save(cust1);
		
	}
	
	@Test
	void testDeleteCustomer() {
		adminService.deleteCustomer(1);
		verify(customerRepository).deleteById(1);
	}
	
	@Test
	void testGetAllCustomers() {
		List<Customer> customers = Arrays.asList(cust1, cust2);
		when(customerRepository.getAllCustomers()).thenReturn(customers);
 
		List<Customer> result = adminService.getAllCustomers();
		assertEquals(2, result.size());
		assertEquals("Rohit", result.get(0).getName());
		assertEquals("Virat", result.get(1).getName());
	}
	
	@Test
	void testGetOneCustomer() {
		when(customerRepository.findById(1)).thenReturn(Optional.of(cust1));
		
		Optional<Customer> result = adminService.getCustomerById(1);
		assertTrue (result.isPresent());
		assertEquals("Rohit", result.get().getName());
		verify(customerRepository).findById(1);
	}
	
	
	@Test
	void testLogin() {
		when(adminRepository.login(login.getUsername(), login.getPassword())).thenReturn(admin);
		Admin result = adminService.login(login);
		assertEquals(admin.getName(), result.getName());
	}
	
	
	
	
	
	
	@Test
	void testGetCustomerById() {
		adminService.getCustomerById(1);
		verify(customerRepository).findById(1);
	}
	
	
 
}
	
//	@Test
//    void testLogin() throws Exception {
//        // Prepare test data
//        Login login = new Login();
//        login.setUsername("hitman");
//        login.setPassword("1234");
//        Admin admin = new Admin(); // Create an instance of Admin, or mock it if needed
//        
//        admin.setUsername("hitman");
//        admin.setPassword("1234");
//
//        // Mock service behavior
//        when(adminService.login(login.getUsername(), login.getPassword())).thenReturn(admin);
//
//        // Perform the request and verify the response
//        mockMvc.perform(MockMvcRequestBuilders.post("/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(login)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.someProperty").value("expectedValue")); // adjust the property and value as per your Admin class
//
//        // You can add more assertions based on what you expect in the response.
//    }
	
	

 