package com.uday.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uday.entity.Customer;
import com.uday.repo.CustomerRepository;

@Service
public class CustomerService implements UserDetailsService{
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private CustomerRepository repository;
	
	public boolean saveCustomer(Customer c) {
		String encodedPassword = encoder.encode(c.getPassword());
		c.setPassword(encodedPassword);
		Customer savedCustomer = repository.save(c);
		return savedCustomer.getCustomerId()!=null;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer =repository.findByEmail(email);
		return new User(customer.getEmail(), customer.getPassword(), Collections.emptyList());
	}
}
