package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


import org.junit.BeforeClass;
import org.junit.Test;

import model.card.*;

public class CardTest {

  static Card card;

  @BeforeClass
  public static void setUp(){
    card = new Card(Hauteur.ACE, Couleur.DIAMOND);
  }

  @Test
  public void testGetCardColor() {
    assertEquals(Couleur.DIAMOND, card.getCardColor());
  }

  @Test
  public void testGetValue() {
    assertEquals(1, card.getValue());
  }

  @Test
  public void testGethauteur() {
    assertEquals(Hauteur.ACE, card.gethauteur());
  }

  @Test
  public void testIsHidden() {
    assertFalse(card.isHidden());
  }
}
