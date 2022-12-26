package de.hs.da.hskleinanzeigen.services;

import de.hs.da.hskleinanzeigen.domain.Category;
import de.hs.da.hskleinanzeigen.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  public Category findCategoryByName(String name)  {
    return categoryRepository.findByName(name);
  }

  public Category saveCategory(Category category) {
    return categoryRepository.save(category);
  }

  public Optional<Category> findCategoryById(int id) {
    return categoryRepository.findById(id);
  }
}
