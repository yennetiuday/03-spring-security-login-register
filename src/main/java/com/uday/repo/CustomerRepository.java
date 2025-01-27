package com.uday.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uday.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	public Customer findByEmail(String email);
}
