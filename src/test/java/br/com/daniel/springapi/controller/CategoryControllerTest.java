package br.com.daniel.springapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import br.com.daniel.springapi.TestMocks;
import br.com.daniel.springapi.entity.Category;
import br.com.daniel.springapi.service.CategoryService;
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

class CategoryControllerTest {

  @Mock private CategoryService categoryService;

  @InjectMocks private CategoryController categoryController;

  private final Category firstCategory = TestMocks.foodCategory;

  private final Category secondCategory = TestMocks.healthCategory;

  private List<Category> categories;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    this.categories = Arrays.asList(this.firstCategory, this.secondCategory);
  }

  @Test
  void testGetAll() {
    when(this.categoryService.findAll()).thenReturn(this.categories);
    ResponseEntity<List<Category>> result = this.categoryController.getAll();
    assertEquals(this.categories, result.getBody());
    assertEquals(HttpStatus.OK, result.getStatusCode());
  }

  @Test
  void testGetAllWhenNoCategoriesFound() {
    when(this.categoryService.findAll()).thenReturn(List.of());

    assertThrows(ResponseStatusException.class, () -> this.categoryController.getAll());
  }

  @Test
  void testGetById() {
    when(this.categoryService.findById(1)).thenReturn(this.firstCategory);

    ResponseEntity<Category> responseEntity = this.categoryController.getById(1);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(this.firstCategory, responseEntity.getBody());
  }

  @Test
  void testGetByIdWhenCategoryNotFound() {
    when(this.categoryService.findById(1)).thenReturn(null);

    assertThrows(ResponseStatusException.class, () -> this.categoryController.getById(1));
  }

  @Test
  void testSave() {
    when(this.categoryService.save(this.firstCategory)).thenReturn(this.firstCategory);

    ResponseEntity<Category> responseEntity = this.categoryController.save(this.firstCategory);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertEquals(this.firstCategory, responseEntity.getBody());
  }

  @Test
  void testUpdate() {
    when(this.categoryService.update(1, this.firstCategory)).thenReturn(this.firstCategory);

    ResponseEntity<Category> responseEntity = this.categoryController.update(1, this.firstCategory);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(this.firstCategory, responseEntity.getBody());
  }

  @Test
  void testUpdateWhenCategoryNotFound() {
    when(this.categoryService.update(1, this.firstCategory)).thenReturn(null);

    assertThrows(
        ResponseStatusException.class, () -> this.categoryController.update(1, this.firstCategory));
  }

  @Test
  void testDelete() {
    when(this.categoryService.deleteCategory(1)).thenReturn(true);

    ResponseEntity<Boolean> responseEntity = this.categoryController.delete(1);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(Boolean.TRUE, responseEntity.getBody());
  }

  @Test
  void testDeleteWhenCategoryNotFound() {
    when(this.categoryService.deleteCategory(1)).thenReturn(false);

    ResponseEntity<Boolean> responseEntity = this.categoryController.delete(1);

    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    assertNotEquals(Boolean.TRUE, responseEntity.getBody());
  }
}
