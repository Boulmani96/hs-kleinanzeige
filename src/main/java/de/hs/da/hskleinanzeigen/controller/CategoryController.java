package de.hs.da.hskleinanzeigen.controller;

import de.hs.da.hskleinanzeigen.domain.Category;
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
    public ResponseEntity<Category> addNewCategory(@RequestBody Category category) {
        //category.getParent() == null || getCategoryByID(category.getParent().getID()).isEmpty()
        if(category.getName() == null){
            return new ResponseEntity<>(category, HttpStatus.BAD_REQUEST);
        }
        // Check if the category already exists in database
        if(categoryRepository.findByName(category.getName()) != null){
            return new ResponseEntity<>(category, HttpStatus.CONFLICT);
        }
        categoryRepository.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping(path="/api/categories/{id}")
    public Optional<Category> getCategoryByID(@PathVariable int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new NotFoundException("Category with the id: "+id+" is not found");
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
