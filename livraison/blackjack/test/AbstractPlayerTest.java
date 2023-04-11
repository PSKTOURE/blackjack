package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import org.junit.Test;

import model.card.Card;
import model.card.Couleur;
import model.card.Hauteur;
import model.pack.Deck;
import model.player.Player;

public class AbstractPlayerTest {



  @Test
  public void testAddFrom() {
    Player player = new Player("TestPlayer");
    LinkedList<Card> cards1 = new LinkedList<>();
    cards1.add(new Card(Hauteur.ACE, Couleur.CLOVER));
    cards1.add(new Card(Hauteur.TWO, Couleur.CLOVER));
    Deck deck = new Deck(cards1);
    player.addFrom(deck);
    assertEquals(deck.size(), 1);
    assertEquals(player.getHand().getHand().size(), 1);
  }

  @Test
  public void testBust() {
    Player player = new Player("TestPlayer");
    player.setBet(500);
    player.bust();
    assertTrue(player.getBank() == 500);
  }



  @Test
  public void testClearHand() {
    Player player = new Player("TestPlayer");
    LinkedList<Card> cards1 = new LinkedList<>();
    cards1.add(new Card(Hauteur.ACE, Couleur.CLOVER));
    cards1.add(new Card(Hauteur.TWO, Couleur.CLOVER));
    Deck deck = new Deck(cards1);
    player.addFrom(deck);
    player.addFrom(deck);
    player.clearHand();
    assertEquals(0, player.getHand().getHand().size());
  }

  @Test
  public void testGetHandvalue() {
    Player player = new Player("TestPlayer");
    LinkedList<Card> cards1 = new LinkedList<>();
    cards1.add(new Card(Hauteur.ACE, Couleur.CLOVER));
    cards1.add(new Card(Hauteur.TWO, Couleur.CLOVER));
    Deck deck = new Deck(cards1);
    player.addFrom(deck);
    player.addFrom(deck);
    assertEquals(13, player.getHandvalue());
  }

  @Test
  public void testGetNumberOfCard() {
    Player player = new Player("TestPlayer");
    LinkedList<Card> cards1 = new LinkedList<>();
    cards1.add(new Card(Hauteur.ACE, Couleur.CLOVER));
    cards1.add(new Card(Hauteur.TWO, Couleur.CLOVER));
    Deck deck = new Deck(cards1);
    player.addFrom(deck);
    player.addFrom(deck);
    assertEquals(2, player.getNumberOfCard());
  }


  @Test
  public void testIsBlackjack() {
    Player player = new Player("TestPlayer");
    LinkedList<Card> cards1 = new LinkedList<>();
    cards1.add(new Card(Hauteur.ACE, Couleur.CLOVER));
    cards1.add(new Card(Hauteur.TEN, Couleur.CLOVER));
    Deck deck = new Deck(cards1);
    player.addFrom(deck);
    player.addFrom(deck);
    assertEquals(true, player.isBlackjack());
  }

}
