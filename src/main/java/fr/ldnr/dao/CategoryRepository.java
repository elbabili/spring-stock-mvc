package fr.ldnr.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ldnr.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
