package br.com.daniel.springapi.repository;

import br.com.daniel.springapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {}
