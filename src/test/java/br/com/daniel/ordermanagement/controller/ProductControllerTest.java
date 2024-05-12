package br.com.daniel.ordermanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import br.com.daniel.ordermanagement.TestMocks;
import br.com.daniel.ordermanagement.entity.Product;
import br.com.daniel.ordermanagement.service.ProductService;
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

class ProductControllerTest {

  @Mock private ProductService productService;

  @InjectMocks private ProductController productController;

  private final Product firstProduct = TestMocks.beer;

  private final Product secondProduct = TestMocks.toothPaste;

  private List<Product> products;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    this.products = Arrays.asList(this.firstProduct, this.secondProduct);
  }

  @Test
  void testGetAll() {
    when(this.productService.findAll()).thenReturn(this.products);
    ResponseEntity<List<Product>> result = this.productController.getAll();
    assertEquals(this.products, result.getBody());
    assertEquals(HttpStatus.OK, result.getStatusCode());
  }

  @Test
  void testGetAllWhenNoProductsFound() {
    when(this.productService.findAll()).thenReturn(List.of());

    assertThrows(ResponseStatusException.class, () -> this.productController.getAll());
  }

  @Test
  void testGetById() {
    when(this.productService.findProductById(1)).thenReturn(this.firstProduct);

    ResponseEntity<Product> responseEntity = this.productController.getById(1);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(this.firstProduct, responseEntity.getBody());
  }

  @Test
  void testGetByIdWhenProductNotFound() {
    when(this.productService.findProductById(1)).thenReturn(null);

    assertThrows(ResponseStatusException.class, () -> this.productController.getById(1));
  }

  @Test
  void testSave() {
    when(this.productService.save(this.firstProduct)).thenReturn(this.firstProduct);

    ResponseEntity<Product> responseEntity = this.productController.save(this.firstProduct);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertEquals(this.firstProduct, responseEntity.getBody());
  }

  @Test
  void testUpdate() {
    when(this.productService.updateProduct(1, this.firstProduct)).thenReturn(this.firstProduct);

    ResponseEntity<Product> responseEntity = this.productController.update(1, this.firstProduct);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(this.firstProduct, responseEntity.getBody());
  }

  @Test
  void testUpdateWhenProductNotFound() {
    when(this.productService.updateProduct(1, this.firstProduct)).thenReturn(null);

    assertThrows(
        ResponseStatusException.class, () -> this.productController.update(1, this.firstProduct));
  }

  @Test
  void testDelete() {
    when(this.productService.deleteProduct(1)).thenReturn(true);

    ResponseEntity<Boolean> responseEntity = this.productController.delete(1);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(Boolean.TRUE, responseEntity.getBody());
  }

  @Test
  void testDeleteWhenProductNotFound() {
    when(this.productService.deleteProduct(1)).thenReturn(false);

    ResponseEntity<Boolean> responseEntity = this.productController.delete(1);

    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    assertNotEquals(Boolean.TRUE, responseEntity.getBody());
  }
}
