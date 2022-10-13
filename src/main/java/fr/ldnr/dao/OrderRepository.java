package fr.ldnr.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ldnr.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
