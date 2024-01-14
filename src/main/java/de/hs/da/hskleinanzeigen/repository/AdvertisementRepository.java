package de.hs.da.hskleinanzeigen.repository;

import de.hs.da.hskleinanzeigen.domain.AD;
import de.hs.da.hskleinanzeigen.domain.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  
public interface AdvertisementRepository extends JpaRepository<AD, Integer> {

    List<AD> findByType(Type type, Pageable pageable);

    List<AD> findByCategory_id(int category,  Pageable pageable);

    @Query("from AD where price between :priceFrom and :priceTo")
    List<AD> findByPriceFromTo(@Param("priceFrom") int priceFrom, @Param("priceTo") int priceTo, Pageable pageable);
}
