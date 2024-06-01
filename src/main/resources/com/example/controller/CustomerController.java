package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.model.Customer;
import com.example.model.Login;
import com.example.service.CustomerService;

@RestController
public class CustomerController {
//	@Autowired
	RestTemplate rs = new RestTemplate();

	@Autowired
	CustomerService cs;

//	@RequestMapping("/home")
//	public String getDevices() {
//		return "index.html";
//	}
//
//	@RequestMapping("/samsung")
//	public String getSamsung() {
//		return "samsung.html";
//	}

//	@GetMapping("/userlogin")
//	public String userlogin() {
////		ModelAndView mv = new ModelAndView();
////		Device d1 = new Device(1, "Samsung note");
////		Device d2 = new Device(2, "Samsung tab");
////		
////		List<Device> listOfDevices = new ArrayList<>();
////		listOfDevices.add(d1);
////		listOfDevices.add(d2);
////		Devices devices = new Devices(listOfDevices, "Samsung");
////		Devices devices = rs.getForObject("http://SAMSUNG/samsung/devices", Devices.class);
////		mv.addObject("devices", devices);
//
//		return "userlogin";
//	}

	@PostMapping("/login")
	public Login login(@RequestBody Login obj) {

		String check = cs.login(obj.getUsername(), obj.getPassword());
		System.out.println(check);
		
		return obj;

	}

//	@GetMapping("/dashboard")
//	public ModelAndView dashboard(@RequestParam("id") int id) {
//		ModelAndView mv = new ModelAndView("dashboard");
//
//		mv.addObject("id", id);
//		return mv;
//	}
//
//	@GetMapping("/deposit")
//	public ModelAndView deposit(@RequestParam("id") int id) {
//		ModelAndView mv = new ModelAndView("deposit");
//
//		mv.addObject("id", id);
//		return mv;
//	}
//
	@PutMapping("/deposit/{id}/{amount}")
	public String depositCheck(@PathVariable int id, @PathVariable float amount) {
		cs.depositCheck(id, amount);
		return "Yes";
	}
	
	@PutMapping("/withdrawal/{id}/{amount}")
	public String withdrawalCheck(@PathVariable int id, @PathVariable float amount) {
		cs.depositCheck(id, amount);
		return "Yes";
	}
//
////	@RequestMapping(value = "/senddati", method = RequestMethod.POST)
////    public String getDataFromForm() {
////        // Process the form data (e.g., save it to a database)
//////        System.out.println("Received data: " + nome);
////        return "success"; // Return a view name or a redirect
////    }
	
	@PostMapping("/checkbalance/{id}")
	public float checkBalance(@PathVariable int id) {
		return cs.checkBalance(id);
	}
	
	@PutMapping("/changepassword/{id}/{password}")
	public void changePassword(@PathVariable int id, @PathVariable String  password) {
		cs.changePassword(id, password);
	}
	
	@PutMapping("/updatecustomer/{id}")
	public void updateCustomer(@PathVariable int id, @RequestBody Customer cust) {
		cs.updateCustomer(id, cust);
	}

}
