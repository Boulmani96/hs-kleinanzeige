package de.hs.da.hskleinanzeigen.repository;

import de.hs.da.hskleinanzeigen.domain.AD;
import de.hs.da.hskleinanzeigen.domain.Type;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//By simply declaring the following AdvertisementRepository interface we automatically will be able to
//Create new Category
//Update existing ones
//Delete Category
//Find Category (one, all, or search by simple or complex properties)
@Repository  
public interface AdvertisementRepository extends JpaRepository<AD, Integer>, CrudRepository<AD, Integer> {

    List<AD> findByType(Type type, Pageable pageable);

    List<AD> findByCategory_id(int category,  Pageable pageable);

    @Query("from AD where Price between :priceFrom and :priceTo")
    List<AD> findByPriceFromTo(@Param("priceFrom") int priceFrom,@Param("priceTo") int priceTo,  Pageable pageable);
}
