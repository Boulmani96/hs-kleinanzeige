package de.hs.da.hskleinanzeigen.services;

import de.hs.da.hskleinanzeigen.domain.Category;
import de.hs.da.hskleinanzeigen.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  public Category findCategoryByName(String name)  {
    return categoryRepository.findByName(name);
  }

  public void saveCategory(Category category) {
    categoryRepository.save(category);
  }

  public Category findCategoryById(int id) throws Exception{
    return categoryRepository.findById(id).orElseThrow(()->new Exception("No Category Found"));
  }
}
