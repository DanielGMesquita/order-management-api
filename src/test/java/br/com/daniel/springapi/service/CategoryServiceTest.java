package br.com.daniel.springapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.daniel.springapi.TestMocks;
import br.com.daniel.springapi.entity.Category;
import br.com.daniel.springapi.repository.CategoryRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CategoryServiceTest {

  @Mock private CategoryRepository categoryRepository;

  @InjectMocks private CategoryService categoryService;

  private List<Category> categories;

  private final Category foodCategory = TestMocks.foodCategory;

  private final Category healthCategory = TestMocks.healthCategory;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    this.categories = Arrays.asList(this.foodCategory, this.healthCategory);
  }

  @Test
  void testFindAll() {
    when(this.categoryRepository.findAll()).thenReturn(this.categories);
    List<Category> result = this.categoryService.findAll();
    assertEquals(this.categories, result);
    verify(this.categoryRepository, times(1)).findAll();
  }

  @Test
  void testFindById() {
    when(this.categoryRepository.findById(1))
        .thenReturn(java.util.Optional.ofNullable(this.foodCategory));
    Category result = this.categoryService.findById(1);
    assertEquals(this.foodCategory, result);
    verify(this.categoryRepository, times(1)).findById(1);
  }

  @Test
  void testSave() {
    when(this.categoryRepository.save(this.foodCategory)).thenReturn(this.foodCategory);
    Category result = this.categoryService.save(this.foodCategory);
    assertEquals(this.foodCategory, result);
    verify(this.categoryRepository, times(1)).save(this.foodCategory);
  }

  @Test
  void testUpdate() {
    when(this.categoryRepository.findById(1))
        .thenReturn(java.util.Optional.ofNullable(this.foodCategory));
    assert this.foodCategory != null;
    when(this.categoryRepository.save(this.foodCategory)).thenReturn(this.foodCategory);
    Category result = this.categoryService.update(1, this.foodCategory);
    assertEquals(this.foodCategory, result);
    verify(this.categoryRepository, times(1)).findById(1);
    verify(this.categoryRepository, times(1)).save(this.foodCategory);
  }

  @Test
  void testDeleteCategory() {
    when(this.categoryRepository.findById(1))
        .thenReturn(java.util.Optional.ofNullable(this.foodCategory));
    assert this.foodCategory != null;
    this.categoryService.deleteCategory(1);
    verify(this.categoryRepository, times(1)).delete(this.foodCategory);
  }
}
