package br.com.daniel.ordermanagement.repository;

import br.com.daniel.ordermanagement.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer> {}
