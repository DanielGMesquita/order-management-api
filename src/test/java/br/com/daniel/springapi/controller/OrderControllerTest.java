package br.com.daniel.springapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import br.com.daniel.springapi.TestMocks;
import br.com.daniel.springapi.entity.Order;
import br.com.daniel.springapi.service.OrderService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

class OrderControllerTest {

  @Mock private OrderService orderService;

  @InjectMocks private OrderController orderController;

  private final Order firstOrder = TestMocks.firstOrder;

  private final Order secondOrder = TestMocks.secondOrder;

  private List<Order> orders;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    this.orders = Arrays.asList(this.firstOrder, this.secondOrder);
  }

  @Test
  void testGetAll() {
    when(this.orderService.findAll()).thenReturn(this.orders);
    ResponseEntity<List<Order>> result = this.orderController.getAll();
    assertEquals(this.orders, result.getBody());
    assertEquals(HttpStatus.OK, result.getStatusCode());
  }

  @Test
  void testGetAll_whenNoOrdersFound() {
    when(this.orderService.findAll()).thenReturn(List.of());

    assertThrows(ResponseStatusException.class, () -> this.orderController.getAll());
  }

  @Test
  void testGetById() {
    when(this.orderService.findById(1)).thenReturn(this.firstOrder);

    ResponseEntity<Order> responseEntity = this.orderController.getById(1);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(this.firstOrder, responseEntity.getBody());
  }

  @Test
  void testGetById_whenOrderNotFound() {
    when(this.orderService.findById(1)).thenReturn(null);

    assertThrows(ResponseStatusException.class, () -> this.orderController.getById(1));
  }

  @Test
  void testSave() {
    when(this.orderService.save(this.firstOrder)).thenReturn(this.firstOrder);

    ResponseEntity<Order> responseEntity = this.orderController.save(this.firstOrder);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertEquals(this.firstOrder, responseEntity.getBody());
  }

  @Test
  void testUpdate() {
    when(this.orderService.update(1, this.firstOrder)).thenReturn(this.firstOrder);

    ResponseEntity<Order> responseEntity = this.orderController.update(1, this.firstOrder);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(this.firstOrder, responseEntity.getBody());
  }

  @Test
  void testUpdate_whenOrderNotFound() {
    when(this.orderService.update(1, this.firstOrder)).thenReturn(null);

    assertThrows(
        ResponseStatusException.class, () -> this.orderController.update(1, this.firstOrder));
  }

  @Test
  void testDelete() {
    when(this.orderService.deleteOrder(1)).thenReturn(true);

    ResponseEntity<Boolean> responseEntity = this.orderController.delete(1);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(Boolean.TRUE, responseEntity.getBody());
  }

  @Test
  void testDelete_whenOrderNotFound() {
    when(this.orderService.deleteOrder(1)).thenReturn(false);

    ResponseEntity<Boolean> responseEntity = this.orderController.delete(1);

    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    assertNotEquals(Boolean.TRUE, responseEntity.getBody());
  }
}
