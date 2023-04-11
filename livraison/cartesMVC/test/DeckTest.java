package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import model.card.Card;
import model.card.Couleur;
import model.card.Hauteur;
import model.pack.Deck;

public class DeckTest {

  @Test
  public void testAddAll() {
    LinkedList<Card> cards1 = new LinkedList<>();
    cards1.add(new Card(Hauteur.ACE, Couleur.CLOVER));
    cards1.add(new Card(Hauteur.TWO, Couleur.CLOVER));
    LinkedList<Card> cards2 = new LinkedList<>();
    cards2.add(new Card(Hauteur.THREE, Couleur.SPADES));
    cards2.add(new Card(Hauteur.SEVEN, Couleur.HEART));
    LinkedList<Card> cards3 = new LinkedList<>();
    cards3.add(new Card(Hauteur.THREE, Couleur.SPADES));
    cards3.add(new Card(Hauteur.SEVEN, Couleur.HEART));
    cards3.add(new Card(Hauteur.ACE, Couleur.CLOVER));
    cards3.add(new Card(Hauteur.TWO, Couleur.CLOVER));
    Deck deck1 = new Deck(cards1);
    Deck deck2 = new Deck(cards2);
    Deck deck3 = new Deck(cards3);
    deck2.addAll(deck1);
    assertTrue(deck3.getDeck().size() == deck2.getDeck().size());
  }

  @Test
  public void testAddFirst() {
    LinkedList<Card> cards1 = new LinkedList<>();
    cards1.add(new Card(Hauteur.ACE, Couleur.CLOVER));
    cards1.add(new Card(Hauteur.TWO, Couleur.CLOVER));
    Card card = new Card(Hauteur.JACK, Couleur.SPADES);
    Deck deck1 = new Deck(cards1);
    deck1.addFirst(card);
    assertTrue(deck1.contains(card));
  }

  @Test
  public void testAddLast() {
    LinkedList<Card> cards1 = new LinkedList<>();
    cards1.add(new Card(Hauteur.ACE, Couleur.CLOVER));
    cards1.add(new Card(Hauteur.TWO, Couleur.CLOVER));
    Card card = new Card(Hauteur.JACK, Couleur.SPADES);
    Deck deck1 = new Deck(cards1);
    deck1.addLast(card);
    assertTrue(deck1.contains(card));
  }

  @Test
  public void testClear() {
    LinkedList<Card> cards1 = new LinkedList<>();
    cards1.add(new Card(Hauteur.ACE, Couleur.CLOVER));
    cards1.add(new Card(Hauteur.TWO, Couleur.CLOVER));
    Deck deck1 = new Deck(cards1);
    deck1.clear();
    assertTrue(deck1.size() == 0);
  }

  @Test
  public void testCutDeck() {
    LinkedList<Card> cards1 = new LinkedList<>();
    cards1.add(new Card(Hauteur.ACE, Couleur.CLOVER));
    cards1.add(new Card(Hauteur.TWO, Couleur.CLOVER));
    cards1.add(new Card(Hauteur.FIVE, Couleur.SPADES));
    Deck deck1 = new Deck(cards1);
    int originalSize = deck1.size();
    deck1.cutDeck();
    assertTrue(originalSize >= deck1.size());
  }

  @Test
  public void testGetCardAtIndex() {
    LinkedList<Card> cards1 = new LinkedList<>();
    cards1.add(new Card(Hauteur.ACE, Couleur.CLOVER));
    cards1.add(new Card(Hauteur.TWO, Couleur.CLOVER));
    Card card = new Card(Hauteur.FIVE, Couleur.SPADES);
    cards1.add(card);
    Deck deck1 = new Deck(cards1);
    assertEquals(deck1.getCardAtIndex(2), card);
  }

  @Test
  public void testGetDeckSize() {
    LinkedList<Card> cards1 = new LinkedList<>();
    cards1.add(new Card(Hauteur.ACE, Couleur.CLOVER));
    cards1.add(new Card(Hauteur.TWO, Couleur.CLOVER));
    cards1.add(new Card(Hauteur.FIVE, Couleur.SPADES));
    Deck deck1 = new Deck(cards1);
    assertEquals(deck1.size(), 3);
  }

  @Test
  public void testPopAtIndex() {
    LinkedList<Card> cards1 = new LinkedList<>();
    cards1.add(new Card(Hauteur.ACE, Couleur.CLOVER));
    cards1.add(new Card(Hauteur.TWO, Couleur.CLOVER));
    Card card = new Card(Hauteur.FIVE, Couleur.SPADES);
    cards1.add(card);
    Deck deck1 = new Deck(cards1);
    assertEquals(deck1.popAtIndex(2), card);
    assertEquals(deck1.size(), 2);
  }

  @Test
  public void testPopFirst() {
    LinkedList<Card> cards1 = new LinkedList<>();
    cards1.add(new Card(Hauteur.ACE, Couleur.CLOVER));
    cards1.add(new Card(Hauteur.TWO, Couleur.CLOVER));
    Card card = new Card(Hauteur.FIVE, Couleur.SPADES);
    cards1.addFirst(card);
    Deck deck1 = new Deck(cards1);
    assertEquals(deck1.popFirst(), card);
    assertEquals(deck1.size(), 2);
  }

  @Test
  public void testPopLast() {
    LinkedList<Card> cards1 = new LinkedList<>();
    cards1.add(new Card(Hauteur.ACE, Couleur.CLOVER));
    cards1.add(new Card(Hauteur.TWO, Couleur.CLOVER));
    Card card = new Card(Hauteur.FIVE, Couleur.SPADES);
    cards1.add(card);
    Deck deck1 = new Deck(cards1);
    assertEquals(deck1.popLast(), card);
    assertEquals(deck1.size(), 2);
  }
}
