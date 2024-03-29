package de.hs.da.hskleinanzeigen.controller;

import de.hs.da.hskleinanzeigen.domain.Category;
import de.hs.da.hskleinanzeigen.dtos.CategoryDTO;
import de.hs.da.hskleinanzeigen.dtos.CreationCategoryDTO;
import de.hs.da.hskleinanzeigen.mappers.CategoryMapper;
import de.hs.da.hskleinanzeigen.services.CategoryService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper){
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category Created", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryDTO.class)) }),
            @ApiResponse(responseCode = "409", description = "Category already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid parameters", content = @Content)
    })
    @PostMapping(path="/api/categories")
    public ResponseEntity<String> addNewCategory(@Valid @RequestBody CreationCategoryDTO creationCategoryDTO) {
        // check if parent category is available
        if (creationCategoryDTO.getParentId() > 0 && getCategoryByID(creationCategoryDTO.getParentId()).getBody() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        // Check if the category already exists in database
        if(categoryService.findCategoryByName(creationCategoryDTO.getName()) != null){
            return new ResponseEntity<>("Category with the name "+creationCategoryDTO.getName()+" already exits", HttpStatus.CONFLICT);
        }

        Category category = categoryMapper.CreationCategoryDTOtoCategory(creationCategoryDTO);
        categoryService.saveCategory(category);
        return new ResponseEntity<>(categoryMapper.categoryToCategoryDTO(category).toString(), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Category", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "No category was found with the given id", content = @Content)
    })
    @GetMapping(path="/api/categories/{id}")
    public ResponseEntity<CategoryDTO> getCategoryByID(@PathVariable int id) {
        Optional<Category> category = categoryService.findCategoryById(id);
        if(category.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryMapper.categoryToCategoryDTO(category.get()), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Category", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "No category was found with the given name", content = @Content)
    })
    @GetMapping(path="/api/categories/name")
    public ResponseEntity<CategoryDTO> getCategoryByName(@RequestParam String name) {
        Category category = categoryService.findCategoryByName(name);
        if(category == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryMapper.categoryToCategoryDTO(category), HttpStatus.OK);
    }
}