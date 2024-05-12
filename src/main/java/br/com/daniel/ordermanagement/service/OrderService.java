package br.com.daniel.ordermanagement.service;

import br.com.daniel.ordermanagement.entity.Orders;
import br.com.daniel.ordermanagement.repository.OrderRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  private final OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public List<Orders> findAll() {
    return this.orderRepository.findAll();
  }

  public Orders findById(Integer id) {
    return this.orderRepository.findById(id).orElse(null);
  }

  public Orders save(Orders orders) {
    return this.orderRepository.save(orders);
  }

  public Orders update(Integer id, Orders orders) {
    Orders ordersToUpdate = this.orderRepository.findById(id).orElse(null);
    if (ordersToUpdate == null) {
      return null;
    }
    ordersToUpdate.setOrderDate(orders.getOrderDate());
    return this.orderRepository.save(ordersToUpdate);
  }

  public Boolean deleteOrder(Integer id) {
    Orders ordersToDelete = this.orderRepository.findById(id).orElse(null);
    if (ordersToDelete == null) {
      return false;
    }
    this.orderRepository.delete(ordersToDelete);
    return true;
  }
}
