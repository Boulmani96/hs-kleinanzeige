package de.hs.da.hskleinanzeigen.services;

import de.hs.da.hskleinanzeigen.domain.AD;
import de.hs.da.hskleinanzeigen.domain.Type;
import de.hs.da.hskleinanzeigen.repository.AdvertisementRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementService {
  @Autowired
  private AdvertisementRepository advertisementRepository;

  public void saveAdvertisement(AD advertisement) {
    advertisementRepository.save(advertisement);
  }

  public Optional<AD> findADById(int id) {
    return advertisementRepository.findById(id);
  }

  public List<AD> findAllAD() {
    return advertisementRepository.findAll();
  }

  public List<AD> findByType(Type type, PageRequest pr) {
    return advertisementRepository.findByType(type,pr);
  }

  public List<AD> findByCategory_id(int category, PageRequest pr) {
    return advertisementRepository.findByCategory_id(category,pr);
  }

  public List<AD> findByPriceFromTo(int priceFrom, int priceTo, PageRequest pr) {
    return advertisementRepository.findByPriceFromTo(priceFrom,priceTo,pr);
  }
}
