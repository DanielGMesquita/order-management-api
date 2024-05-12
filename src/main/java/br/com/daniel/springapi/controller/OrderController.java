package br.com.daniel.springapi.controller;

import br.com.daniel.springapi.entity.Order;
import br.com.daniel.springapi.service.OrderService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/orders")
public class OrderController {
  private final OrderService service;

  public OrderController(OrderService service) {
    this.service = service;
  }

  private static final String ORDER_NOT_FOUND = "Order not found";

  @GetMapping
  public ResponseEntity<List<Order>> getAll() {
    List<Order> orders = this.service.findAll();
    if (orders.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No orders found in the database");
    }
    return new ResponseEntity<>(orders, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Order> getById(@PathVariable Integer id) {
    Order order = this.service.findById(id);
    if (order == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ORDER_NOT_FOUND);
    }
    return new ResponseEntity<>(order, HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<Order> save(Order order) {
    Order savedOrder = this.service.save(order);
    return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Order> update(@PathVariable Integer id, @RequestBody Order order) {
    Order updatedOrder = this.service.update(id, order);
    if (updatedOrder == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ORDER_NOT_FOUND);
    }
    return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
    Boolean deleted = this.service.deleteOrder(id);
    if (Boolean.FALSE.equals(deleted)) {
      return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(true, HttpStatus.OK);
  }
}
