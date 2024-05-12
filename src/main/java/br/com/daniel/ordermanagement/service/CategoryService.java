package br.com.daniel.ordermanagement.service;

import br.com.daniel.ordermanagement.entity.Category;
import br.com.daniel.ordermanagement.repository.CategoryRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<Category> findAll() {
    return this.categoryRepository.findAll();
  }

  public Category findById(Integer id) {
    return this.categoryRepository.findById(id).orElse(null);
  }

  public Category save(Category category) {
    return this.categoryRepository.save(category);
  }

  public Category update(Integer id, Category category) {
    Category categoryToUpdate = this.categoryRepository.findById(id).orElse(null);
    if (categoryToUpdate == null) {
      return null;
    }
    categoryToUpdate.setCategoryName(category.getCategoryName());
    return this.categoryRepository.save(categoryToUpdate);
  }

  public Boolean deleteCategory(Integer id) {
    Category categoryToDelete = this.categoryRepository.findById(id).orElse(null);
    if (categoryToDelete == null) {
      return false;
    }
    this.categoryRepository.delete(categoryToDelete);
    return true;
  }
}
