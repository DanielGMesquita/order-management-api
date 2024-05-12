package br.com.daniel.springapi.repository;

import br.com.daniel.springapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {}
