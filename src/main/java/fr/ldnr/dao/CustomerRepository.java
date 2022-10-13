package fr.ldnr.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ldnr.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
