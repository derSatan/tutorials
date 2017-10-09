package de.derSatan.vaadinTutorial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.derSatan.vaadinTutorial.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);
}