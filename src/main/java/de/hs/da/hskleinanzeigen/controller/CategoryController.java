package de.hs.da.hskleinanzeigen.controller;

import de.hs.da.hskleinanzeigen.DTOs.CategoryDTO;
import de.hs.da.hskleinanzeigen.DTOs.CreationCategoryDTO;
import de.hs.da.hskleinanzeigen.domain.Category;
import de.hs.da.hskleinanzeigen.mappers.CategoryMapper;
import de.hs.da.hskleinanzeigen.repository.CategoryRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {
	@Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    // @ResponseBody means the returned String is the response, not a view name
	@PostMapping(path="/api/categories") // Map ONLY POST Requests
    public ResponseEntity<Category> addNewCategory(@RequestBody CreationCategoryDTO creationCategoryDTO) {
        //category.getParent() == null || getCategoryByID(category.getParent().getID()).isEmpty()
        if(creationCategoryDTO.getName() == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        // check if parent category is available
        if(creationCategoryDTO.getParentId() > 0) {
            if (getCategoryByID(creationCategoryDTO.getParentId()).getBody() == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }
        // Check if the category already exists in database
        if(categoryRepository.findByName(creationCategoryDTO.getName()) != null){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        //category just with name
        if(creationCategoryDTO.getParentId() == 0){
            Category category = new Category(creationCategoryDTO.getName(), null);
            categoryRepository.save(category);
            return new ResponseEntity<>(category, HttpStatus.CREATED);
        }
        //category with name and parent
        Category category = new Category(creationCategoryDTO.getName(), categoryRepository.findById(creationCategoryDTO.getParentId()).get());
        categoryRepository.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping(path="/api/categories/{id}")
    public ResponseEntity<CategoryDTO> getCategoryByID(@PathVariable int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryMapper.categoryToCategoryDTO(category.get()), HttpStatus.OK);
    }

    @GetMapping(path="/api/categories/name")
    public ResponseEntity<Category> getCategoryByName(@RequestParam String name) {
       return new ResponseEntity<>(categoryRepository.findByName(name), HttpStatus.OK);
    }

    @GetMapping(path="/api/categories/all")
    public List<Category> getAllCategories() {
        // This returns a JSON or XML with the advertisement
        return categoryRepository.findAll();
    }
}
