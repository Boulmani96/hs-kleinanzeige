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
import java.util.Optional;
import javax.validation.Valid;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
	@Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);


    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category Created", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryDTO.class)) }),
            @ApiResponse(responseCode = "409", description = "Category already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid parameters", content = @Content)
    })
    // @ResponseBody means the returned String is the response, not a view name
	@PostMapping(path="/api/categories" ) // Map ONLY POST Requests
    public ResponseEntity<CategoryDTO> addNewCategory(@Valid @RequestBody CreationCategoryDTO creationCategoryDTO)
        throws Exception {
        //category.getParent() == null || getCategoryByID(category.getParent().getID()).isEmpty()
        if(creationCategoryDTO.getName() == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        // check if parent category is available
        if (creationCategoryDTO.getParentId() > 0 && getCategoryByID(creationCategoryDTO.getParentId()).getBody() == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        // Check if the category already exists in database
        if(categoryService.findCategoryByName(creationCategoryDTO.getName()) != null){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        Category category = categoryMapper.CreationCategoryDTOtoCategory(creationCategoryDTO);
        categoryService.saveCategory(category);
        return new ResponseEntity<>(categoryMapper.categoryToCategoryDTO(category), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Category", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "No category was found with the given id", content = @Content)
    })
    @GetMapping(path="/api/categories/{id}")
    public ResponseEntity<CategoryDTO> getCategoryByID(@PathVariable int id) throws Exception {
        Optional<Category> category = Optional.ofNullable(categoryService.findCategoryById(id));
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

    @GetMapping(path="/api/categories/all")
    public String getHello() {

        return "Hello";
    }
}
