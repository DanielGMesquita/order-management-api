package br.com.daniel.ordermanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.daniel.ordermanagement.TestMocks;
import br.com.daniel.ordermanagement.entity.Product;
import br.com.daniel.ordermanagement.repository.ProductRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ProductServiceTest {

  @Mock private ProductRepository productRepository;

  @InjectMocks private ProductService productService;

  private List<Product> products;

  private final Product beer = TestMocks.beer;

  private final Product toothPaste = TestMocks.toothPaste;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    this.products = Arrays.asList(this.beer, this.toothPaste);
  }

  @Test
  void testFindAll() {
    when(this.productRepository.findAll()).thenReturn(this.products);
    List<Product> result = this.productService.findAll();
    assertEquals(this.products.size(), 2);
    verify(this.productRepository, times(1)).findAll();
  }

  @Test
  void testFindById() {
    when(this.productRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(this.beer));
    Product result = this.productService.findProductById(1);
    assertEquals(this.beer, result);
    verify(this.productRepository, times(1)).findById(1);
  }

  @Test
  void testSave() {
    when(this.productRepository.save(this.beer)).thenReturn(this.beer);
    Product result = this.productService.save(this.beer);
    assertEquals(this.beer, result);
    verify(this.productRepository, times(1)).save(this.beer);
  }

  @Test
  void testUpdate() {
    when(this.productRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(this.beer));
    assert this.beer != null;
    when(this.productRepository.save(this.beer)).thenReturn(this.beer);
    Product result = this.productService.updateProduct(1, this.beer);
    assertEquals(this.beer, result);
    verify(this.productRepository, times(1)).findById(1);
    verify(this.productRepository, times(1)).save(this.beer);
  }

  @Test
  void testDeleteProduct() {
    when(this.productRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(this.beer));
    assert this.beer != null;
    this.productService.deleteProduct(1);
    verify(this.productRepository, times(1)).delete(this.beer);
  }
}
