package de.hs.da.hskleinanzeigen.controller;

import de.hs.da.hskleinanzeigen.domain.Category;
import de.hs.da.hskleinanzeigen.domain.CategoryApi;
import de.hs.da.hskleinanzeigen.domain.NotFoundException;
import de.hs.da.hskleinanzeigen.repository.CategoryRepository;
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

    // @ResponseBody means the returned String is the response, not a view name
	@PostMapping(path="/api/categories") // Map ONLY POST Requests
    public ResponseEntity<Category> addNewCategory(@RequestBody CategoryApi categoryApi) {
        //category.getParent() == null || getCategoryByID(category.getParent().getID()).isEmpty()
        if(categoryApi.getName() == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        // check if parent category is available
        if(categoryApi.getParentId() > 0) {
            if (getCategoryByID(categoryApi.getParentId()) == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }
        // Check if the category already exists in database
        if(categoryRepository.findByName(categoryApi.getName()) != null){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        //category just with name
        if(categoryApi.getParentId() == 0){
            Category category = new Category(categoryApi.getName(), null);
            categoryRepository.save(category);
            return new ResponseEntity<>(category, HttpStatus.CREATED);
        }
        Category category = new Category(categoryApi.getName(), categoryRepository.findById(categoryApi.getParentId()).get());
        categoryRepository.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping(path="/api/categories/{id}")
    public Optional<Category> getCategoryByID(@PathVariable int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            return null;
        }
        return category;
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
