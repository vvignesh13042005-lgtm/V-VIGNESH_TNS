package com.customer;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
	
@RestController
@CrossOrigin(origins = "http://localhost:4200") // angular
@RequestMapping("/customerdetails") 
public class CustomerController {
	@Autowired
	private CustomerService Service;

	// Retrieve operation (retrieving all customers)
	@GetMapping    //("/Customers")
	public List<Customer> listAll() {
		return Service.listAll();
	}

	// Retrieve Operation (retrieving customer by id)
	@GetMapping("/{id}")
	public ResponseEntity<Customer> get(@PathVariable Integer id) {
		try {
			Customer customer = Service.get(id);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Customer>(HttpStatus.CREATED);
		}

	}

	// Create Operation
	 @PostMapping
	    public ResponseEntity<Customer> add(@RequestBody Customer customer) {
	        Service.save(customer);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	// Update Operation
	@PutMapping("/{id}") // It maps the HTTP PUT requests
	public ResponseEntity<Customer> update(@RequestBody Customer customer, @PathVariable Integer id) {
		try {
			Service.get(id);
			Service.save(customer);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}

	}

	// Delete Operation // It Maps the HTTP DELETE requests
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            Service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}

