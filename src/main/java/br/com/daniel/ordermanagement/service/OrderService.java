package br.com.daniel.ordermanagement.service;

import br.com.daniel.ordermanagement.entity.Order;
import br.com.daniel.ordermanagement.repository.OrderRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  private final OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public List<Order> findAll() {
    return this.orderRepository.findAll();
  }

  public Order findById(Integer id) {
    return this.orderRepository.findById(id).orElse(null);
  }

  public Order save(Order order) {
    return this.orderRepository.save(order);
  }

  public Order update(Integer id, Order order) {
    Order orderToUpdate = this.orderRepository.findById(id).orElse(null);
    if (orderToUpdate == null) {
      return null;
    }
    orderToUpdate.setOrderDate(order.getOrderDate());
    return this.orderRepository.save(orderToUpdate);
  }

  public Boolean deleteOrder(Integer id) {
    Order orderToDelete = this.orderRepository.findById(id).orElse(null);
    if (orderToDelete == null) {
      return false;
    }
    this.orderRepository.delete(orderToDelete);
    return true;
  }
}
