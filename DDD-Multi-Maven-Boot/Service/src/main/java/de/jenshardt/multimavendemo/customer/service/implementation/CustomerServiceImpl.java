package de.jenshardt.multimavendemo.customer.service.implementation;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jenshardt.multimavendemo.customer.domain.aggregate.Customer;
import de.jenshardt.multimavendemo.customer.domain.repository.CustomerRepository;
import de.jenshardt.multimavendemo.customer.domain.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Override
	public Customer getCustomerById(UUID customerId) {
		LOG.info("Seaching for customer with id {}", customerId);

		Optional<Customer> cust = customerRepo.findById(customerId);
		
		if (cust.isPresent()) {
			LOG.info("Found customer {}", cust.toString());
			
			return cust.get();
		}
		
		LOG.error("No customer found ...");
		
		return null;
	}

	@Override
	public Customer createCustomer(String name, String job) {
		LOG.info("Creating new customer with name {} and job {}", name, job);

		Customer newCustomer = new Customer(name, job);
		
		Customer cust = customerRepo.save(newCustomer);
		
		LOG.info("Created customer with id {}", cust.getId());
		
		return cust;
	}
}
