package br.com.daniel.springapi.repository;

import br.com.daniel.springapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {}
