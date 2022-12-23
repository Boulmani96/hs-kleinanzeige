package de.hs.da.hskleinanzeigen.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import liquibase.pro.packaged.A;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NotepadTest {


  @Test
  public void testNotepadConstructor() {
    Notepad notepad = new Notepad();
    assertNotNull(notepad);
    assertEquals(null, notepad.getId());
    assertNull(notepad.getUser());
    assertNull(notepad.getAd());
    assertNull(notepad.getNote());
    assertNull(notepad.getCreated());
  }
  @Test
  public void testNotEquals() {
    User user= new User();
    AD ad=new AD();
    Notepad notepad1 = new Notepad();
    notepad1.setId(1);
    notepad1.setUser(user);
    notepad1.setAd(ad);
    notepad1.setNote("Test note 1");
    notepad1.setCreated(LocalDateTime.now());

    Notepad notepad2 = new Notepad();
    notepad2.setId(1);
    notepad2.setUser(user);
    notepad2.setAd(ad);
    notepad2.setNote("Test note");
    notepad2.setCreated(LocalDateTime.now());

    // Verify that the two Notepad objects are equal
    assertFalse(notepad1.equals(notepad2));
  }
  @Test
  public void testEquals() {
    User user= new User();
    AD ad=new AD();
    Notepad notepad1 = new Notepad();
    notepad1.setId(1);
    notepad1.setUser(user);
    notepad1.setAd(ad);
    notepad1.setNote("Test note");


    Notepad notepad2 = new Notepad();
    notepad2.setId(1);
    notepad2.setUser(user);
    notepad2.setAd(ad);
    notepad2.setNote("Test note");


    // Verify that the two Notepad objects are equal
    assertTrue(notepad1.equals(notepad2));
    assertEquals(notepad1.hashCode(), notepad2.hashCode());
  }

}