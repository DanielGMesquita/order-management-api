package br.com.daniel.ordermanagement.controller;

import br.com.daniel.ordermanagement.entity.Orders;
import br.com.daniel.ordermanagement.service.OrderService;
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
  public ResponseEntity<List<Orders>> getAll() {
    List<Orders> orders = this.service.findAll();
    if (orders.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No orders found in the database");
    }
    return new ResponseEntity<>(orders, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Orders> getById(@PathVariable Integer id) {
    Orders orders = this.service.findById(id);
    if (orders == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ORDER_NOT_FOUND);
    }
    return new ResponseEntity<>(orders, HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<Orders> save(Orders orders) {
    Orders savedOrders = this.service.save(orders);
    return new ResponseEntity<>(savedOrders, HttpStatus.CREATED);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Orders> update(@PathVariable Integer id, @RequestBody Orders orders) {
    Orders updatedOrders = this.service.update(id, orders);
    if (updatedOrders == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ORDER_NOT_FOUND);
    }
    return new ResponseEntity<>(updatedOrders, HttpStatus.OK);
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
