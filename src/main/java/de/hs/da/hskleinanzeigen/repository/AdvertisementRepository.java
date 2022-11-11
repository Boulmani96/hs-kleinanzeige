package de.hs.da.hskleinanzeigen.repository;

import de.hs.da.hskleinanzeigen.domain.AD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//By simply declaring the following AdvertisementRepository interface we automatically will be able to
//Create new Category
//Update existing ones
//Delete Category
//Find Category (one, all, or search by simple or complex properties)
@Repository  
public interface AdvertisementRepository extends JpaRepository<AD, Integer> {
}
