package br.com.daniel.ordermanagement.repository;

import br.com.daniel.ordermanagement.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {}
