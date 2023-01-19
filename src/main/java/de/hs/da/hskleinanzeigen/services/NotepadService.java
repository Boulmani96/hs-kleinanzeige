package de.hs.da.hskleinanzeigen.services;

import de.hs.da.hskleinanzeigen.domain.Notepad;
import de.hs.da.hskleinanzeigen.repository.NotepadRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotepadService {

  @Autowired
  private NotepadRepository notepadRepository ;

  public Notepad findByUser_idAndByAd_id(Integer userId, Integer advertisementId) {
    return notepadRepository.findByUser_idAndByAd_id(userId,advertisementId);
  }

  public Notepad saveNotepad(Notepad notepad) {
    return notepadRepository.save(notepad);
  }

  public List<Notepad> findNotepadByUser_id(Integer userId) {
    return notepadRepository.findByUser_id(userId);
  }

  public void deleteNotepad(Notepad notepad) {
    notepadRepository.delete(notepad);
  }
}