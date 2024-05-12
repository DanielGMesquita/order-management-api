package br.com.daniel.ordermanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.daniel.ordermanagement.TestMocks;
import br.com.daniel.ordermanagement.entity.Orders;
import br.com.daniel.ordermanagement.repository.OrderRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class OrdersServiceTest {

  @Mock private OrderRepository orderRepository;

  @InjectMocks private OrderService orderService;

  private List<Orders> orders;

  private final Orders firstOrder = TestMocks.firstOrder;

  private final Orders secondOrder = TestMocks.secondOrder;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    this.orders = Arrays.asList(this.firstOrder, this.secondOrder);
  }

  @Test
  void testFindAll() {
    when(this.orderRepository.findAll()).thenReturn(this.orders);
    List<Orders> result = this.orderService.findAll();
    assertEquals(this.orders, result);
    verify(this.orderRepository, times(1)).findAll();
  }

  @Test
  void testFindById() {
    when(this.orderRepository.findById(1))
        .thenReturn(java.util.Optional.ofNullable(this.firstOrder));
    Orders result = this.orderService.findById(1);
    assertEquals(this.firstOrder, result);
    verify(this.orderRepository, times(1)).findById(1);
  }

  @Test
  void testSave() {
    when(this.orderRepository.save(this.firstOrder)).thenReturn(this.firstOrder);
    Orders result = this.orderService.save(this.firstOrder);
    assertEquals(this.firstOrder, result);
    verify(this.orderRepository, times(1)).save(this.firstOrder);
  }

  @Test
  void testUpdate() {
    when(this.orderRepository.findById(1))
        .thenReturn(java.util.Optional.ofNullable(this.firstOrder));
    assert this.firstOrder != null;
    when(this.orderRepository.save(this.firstOrder)).thenReturn(this.firstOrder);
    Orders result = this.orderService.update(1, this.firstOrder);
    assertEquals(this.firstOrder, result);
    verify(this.orderRepository, times(1)).findById(1);
    verify(this.orderRepository, times(1)).save(this.firstOrder);
  }

  @Test
  void testDeleteOrder() {
    when(this.orderRepository.findById(1))
        .thenReturn(java.util.Optional.ofNullable(this.firstOrder));
    assert this.firstOrder != null;
    this.orderService.deleteOrder(1);
    verify(this.orderRepository, times(1)).delete(this.firstOrder);
  }
}
