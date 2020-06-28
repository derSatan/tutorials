package de.jenshardt.multimavendemo.customer.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.jenshardt.multimavendemo.customer.domain.aggregate.Customer;
import de.jenshardt.multimavendemo.customer.domain.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);
	
	@GetMapping("customer/{customerId}")
	public Customer getCustomer(@PathVariable UUID customerId) {
		LOG.info("Get Customer with id {}, customerId");
		
		return customerService.getCustomerById(customerId); // TODO use proper http codes
	}
}
