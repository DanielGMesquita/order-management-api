package br.com.daniel.springapi.service;

import br.com.daniel.springapi.entity.Product;
import br.com.daniel.springapi.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> findAll() {
    return this.productRepository.findAll();
  }

  public Product findProductById(Integer id) {
    return this.productRepository.findById(id).orElse(null);
  }

  public Product save(Product product) {
    return this.productRepository.save(product);
  }

  public Product updateProduct(Integer id, Product product) {
    Product productToUpdate = this.productRepository.findById(id).orElse(null);
    if (productToUpdate == null) {
      return null;
    }
    productToUpdate.setProductName(product.getProductName());
    return this.productRepository.save(productToUpdate);
  }

  public Boolean deleteProduct(Integer id) {
    Product productToDelete = this.productRepository.findById(id).orElse(null);
    if (productToDelete == null) {
      return false;
    }
    this.productRepository.delete(productToDelete);
    return true;
  }
}
