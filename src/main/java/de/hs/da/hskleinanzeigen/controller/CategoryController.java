package de.hs.da.hskleinanzeigen.controller;

import de.hs.da.hskleinanzeigen.domain.Category;
import de.hs.da.hskleinanzeigen.domain.NotFoundException;
import de.hs.da.hskleinanzeigen.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController  
public class CategoryController {
	@Autowired
    private CategoryRepository categoryRepository;

    // @ResponseBody means the returned String is the response, not a view name
	@PostMapping(path="/api/category") // Map ONLY POST Requests
    @ResponseBody
    public Category addNewCategory(@RequestBody Category category) {
        if(category.getParent() != null){
            //System.out.println(category.getParent().getName());
            if(getCategoryByID(category.getParent().getID()).isEmpty()) {
                throw new NotFoundException("Category with the id: " + getCategoryByID(category.getParent().getID()).get().getParent().getID() + " is not found");
            }
            category.setParent(getCategoryByID(category.getParent().getID()).get());
        }
        return categoryRepository.save(category);
    }

    @GetMapping("/api/category/{id}")
    public Optional<Category> getCategoryByID(@PathVariable int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new NotFoundException("Category with the id: "+id+" is not found");
        }
        return category;
    }

    @GetMapping(path="/api/category/all")
    @ResponseBody
    public List<Category> getAllCategories() {
        // This returns a JSON or XML with the advertisement
        return categoryRepository.findAll();
    }
}
