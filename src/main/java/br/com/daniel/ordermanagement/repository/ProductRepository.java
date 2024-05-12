package br.com.daniel.ordermanagement.repository;

import br.com.daniel.ordermanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {}
