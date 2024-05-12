package br.com.daniel.ordermanagement.controller;

import br.com.daniel.ordermanagement.entity.Product;
import br.com.daniel.ordermanagement.service.ProductService;
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
@RequestMapping("/products")
public class ProductController {
  private final ProductService service;
  private static final String PRODUCT_NOT_FOUND = "Product not found";

  public ProductController(ProductService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Product>> getAll() {
    List<Product> products = this.service.findAll();
    if (products.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products found in the database");
    }
    return ResponseEntity.ok(products);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getById(@PathVariable Integer id) {
    Product product = this.service.findProductById(id);
    if (product == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, PRODUCT_NOT_FOUND);
    }
    return ResponseEntity.ok(product);
  }

  @PostMapping("/create")
  public ResponseEntity<Product> save(@RequestBody Product product) {
    Product savedProduct = this.service.save(product);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Product> update(@PathVariable Integer id, @RequestBody Product product) {
    Product updatedProduct = this.service.updateProduct(id, product);
    if (updatedProduct == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, PRODUCT_NOT_FOUND);
    }
    return ResponseEntity.ok(updatedProduct);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
    Boolean deleted = this.service.deleteProduct(id);
    if (Boolean.FALSE.equals(deleted)) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(deleted);
  }
}
