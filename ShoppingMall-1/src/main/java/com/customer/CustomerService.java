package com.customer;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



	@Service
	@Transactional
	public class CustomerService {
	    
		@Autowired
		private CustomerRepository repo;
		public List<Customer> listAll()
		{
			return repo.findAll();
		}
		 public void save(Customer customer) 
	 {
	 repo.save(customer);
	 }
	 
	 public Customer get(Integer id) 
	 {
	 return repo.findById(id).get();
	 }
	 
	 public void delete(Integer id) 
	 {
	 repo.deleteById(id);
	 }
	
	}



	