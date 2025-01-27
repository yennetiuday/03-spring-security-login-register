package com.uday.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uday.entity.Customer;
import com.uday.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@Autowired
	private AuthenticationManager manager;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerCustomer(@RequestBody Customer c) {
		boolean status = service.saveCustomer(c);
		
		if(status) {
			return new ResponseEntity<>("Success", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Customer c) {
		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(c.getEmail(), c.getPassword());
		Authentication authentication = manager.authenticate(token);
		boolean status = authentication.isAuthenticated();
		if(status) {
			return new ResponseEntity<>("Welcome", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
}
