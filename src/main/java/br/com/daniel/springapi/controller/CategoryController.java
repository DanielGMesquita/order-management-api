package br.com.daniel.springapi.controller;

import br.com.daniel.springapi.entity.Category;
import br.com.daniel.springapi.service.CategoryService;
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
@RequestMapping("/categories")
public class CategoryController {
  private final CategoryService service;
  private static final String CATEGORY_NOT_FOUND = "Category not found";

  public CategoryController(CategoryService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Category>> getAll() {
    List<Category> categories = this.service.findAll();
    if (categories.isEmpty()) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "No categories found in the database");
    }
    return new ResponseEntity<>(categories, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Category> getById(@PathVariable Integer id) {
    Category category = this.service.findById(id);
    if (category == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, CATEGORY_NOT_FOUND);
    }
    return new ResponseEntity<>(category, HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<Category> save(@RequestBody Category category) {
    Category savedCategory = this.service.save(category);
    return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
  }

  @PutMapping("update/{id}")
  public ResponseEntity<Category> update(@PathVariable Integer id, @RequestBody Category category) {
    Category updatedCategory = this.service.update(id, category);
    if (updatedCategory == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, CATEGORY_NOT_FOUND);
    }
    return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
    Boolean deleted = this.service.deleteCategory(id);
    if (Boolean.FALSE.equals(deleted)) {
      return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(true, HttpStatus.OK);
  }
}
