package test;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import model.card.Card;
import model.card.Couleur;
import model.card.Hauteur;
import model.pack.Deck;
import model.player.Dealer;


public class DealerTest {
  
  @Test
  public void testDealerPlay() {
    Dealer dealer = new Dealer();
    LinkedList<Card> cards1 = new LinkedList<>();
    cards1.add(new Card(Hauteur.ACE, Couleur.CLOVER));
    cards1.add(new Card(Hauteur.TWO, Couleur.CLOVER));
    cards1.add(new Card(Hauteur.JACK, Couleur.HEART));
    cards1.add(new Card(Hauteur.QUEEN, Couleur.SPADES));
    cards1.add(new Card(Hauteur.FIVE, Couleur.DIAMOND));
    Deck deck = new Deck(cards1);
    dealer.addFrom(deck);
    dealer.addFrom(deck);
    dealer.dealerPlay(deck);
    assertTrue(dealer.getHandvalue() >= 17);
  }
}
