package fr.ldnr.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ldnr.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
