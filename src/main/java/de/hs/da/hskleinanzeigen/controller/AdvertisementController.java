package de.hs.da.hskleinanzeigen.controller;

import de.hs.da.hskleinanzeigen.domain.AD;
import de.hs.da.hskleinanzeigen.domain.Type;
import de.hs.da.hskleinanzeigen.dtos.AdDTO;
import de.hs.da.hskleinanzeigen.dtos.CategoryDTO;
import de.hs.da.hskleinanzeigen.dtos.CreationAdDTO;
import de.hs.da.hskleinanzeigen.dtos.UserDTO;
import de.hs.da.hskleinanzeigen.mappers.AdvertisementMapper;
import de.hs.da.hskleinanzeigen.services.AdvertisementService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class AdvertisementController{

    private final AdvertisementService advertisementService;

    private final CategoryController categoryController;

    private final UserController userController;

    private final AdvertisementMapper advertisementMapper;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService, CategoryController categoryController, UserController userController, AdvertisementMapper advertisementMapper){
        this.advertisementService = advertisementService;
        this.categoryController = categoryController;
        this.userController = userController;
        this.advertisementMapper = advertisementMapper;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Advertisement Created", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid parameters", content = @Content)
    })
    @PostMapping(path="/api/advertisements") // Map ONLY POST Requests
    @ResponseBody
    public ResponseEntity<AdDTO> addNewAdvertisement(@Valid @RequestBody CreationAdDTO creationAdDTO) {
        if(creationAdDTO.getType() == null || creationAdDTO.getCategory() == null
                || creationAdDTO.getCategory().getId() == null || creationAdDTO.getTitle() == null
                || creationAdDTO.getDescription() == null || creationAdDTO.getUser() == null
                || creationAdDTO.getUser().getId() == null ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // If Category or user does not exist
        if(categoryController.getCategoryByID(creationAdDTO.getCategory().getId()) == null
                || userController.getUserByID(creationAdDTO.getUser().getId()) == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        CategoryDTO categoryDTO = categoryController.getCategoryByID(creationAdDTO.getCategory().getId()).getBody();
        UserDTO userDTO = userController.getUserByID(creationAdDTO.getUser().getId()).getBody();

        creationAdDTO.setCategory(categoryDTO);
        creationAdDTO.setUser(userDTO);
        AD advertisement = advertisementMapper.creationAdDTOtoAd(creationAdDTO);
        Optional.ofNullable(advertisement).ifPresent(ad -> ad.setCreated(LocalDateTime.now()));
        advertisementService.saveAdvertisement(advertisement);
        return new ResponseEntity<>(advertisementMapper.adToAdDTO(advertisement), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Advertisement", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AdDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Advertisement not found", content = @Content)
    })
    @GetMapping("/api/advertisements/{id}")
    public ResponseEntity<AdDTO> getAdvertisementById(@PathVariable int id) {
        Optional<AD> advertisement = advertisementService.findADById(id);
        // The advertisement does not found
        if(advertisement.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(advertisementMapper.adToAdDTO(advertisement.get()), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found advertisements", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Page.class)) }),
            @ApiResponse(responseCode = "204", description = "No content", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid Parameters", content = @Content)
    })
    @GetMapping(path="/api/advertisements")
    @ResponseBody
    public ResponseEntity<Page> getAdvertisements(@RequestParam(required = false) Type type,
                                                  @RequestParam(required = false , defaultValue = "-1") int category,
                                                  @RequestParam(required = false , defaultValue = "-1") int priceFrom,
                                                  @RequestParam(required = false, defaultValue = "-1") int priceTo,
                                                  @RequestParam(defaultValue = "-1") int pageStart,
                                                  @RequestParam(defaultValue = "-1") int pageSize) {
        if(pageSize < 0 || pageStart < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        PageRequest pr = PageRequest.of(pageStart, pageSize);
        List<AD> filteredList = advertisementService.findAllAD();

        if(type != null){
            List<AD> filterType = advertisementService.findByType(type,pr);
            filteredList.retainAll(filterType);
        }

        if(category != -1){
            List<AD> filterCategory = advertisementService.findByCategory_id(category,pr);
            filteredList.retainAll(filterCategory);
        }

        if(priceFrom != -1 && priceTo != -1){
            List<AD> filterPriceFromTo = advertisementService.findByPriceFromTo(priceFrom,priceTo,pr);
            filteredList.retainAll(filterPriceFromTo);
        }

        if (filteredList.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        Page<AdDTO> page = new PageImpl<>(advertisementMapper.listAdToAdDTO(filteredList));
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}

