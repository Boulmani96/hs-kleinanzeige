package de.hs.da.hskleinanzeigen.repository;

import de.hs.da.hskleinanzeigen.domain.Notepad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface NotepadRepository extends JpaRepository<Notepad, Integer> {
    List<Notepad> findByUser_id(Integer userId);

    @Query("from Notepad n where n.user.id = ?1 AND n.ad.id = ?2")
    Notepad findByUser_idAndByAd_id(@PathVariable Integer userId, @PathVariable Integer adId);
}