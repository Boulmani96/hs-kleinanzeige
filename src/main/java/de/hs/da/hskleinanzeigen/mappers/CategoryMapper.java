package de.hs.da.hskleinanzeigen.mappers;

import de.hs.da.hskleinanzeigen.dtos.CategoryDTO;
import de.hs.da.hskleinanzeigen.dtos.CreationCategoryDTO;
import de.hs.da.hskleinanzeigen.domain.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO categoryToCategoryDTO(Category category);

    Category CreationCategoryDTOtoCategory(CreationCategoryDTO creationCategoryDTO);
}
