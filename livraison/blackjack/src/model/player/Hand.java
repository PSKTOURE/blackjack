package model.player;

import java.util.LinkedList;

import model.card.Card;
import model.pack.Deck;
import util.AbstractListenableModel;
import util.ModelListener;

/**
 * Class representing the hand of a player
 * in the game of blackjack
 * A hand is composed of one or mutiples card
 * Extends AbstractListenableM%odel
 * Implements ModelListener
 */
public class Hand extends AbstractListenableModel implements ModelListener {

  private Deck hand;

  /**
   * Constructor
   */
  public Hand() {
    hand = new Deck(new LinkedList<Card>());
  }

  /**
   * Method calculating the value of the hand
   * 
   * @return value
   */
  public int calculateHand() {
    int total = 0;
    boolean aceFlag = false;
    for (Card card : hand.getDeck()) {
      int value = card.isHidden() ? 0 : card.getValue();
      if (value >= 10)
        value = 10;
      if (value == 1)
        aceFlag = true;
      total += value;
    }
    if (aceFlag && total + 10 <= 21)
      total += 10;
    return total;
  }

  /**
   * Method that add a card to the hand
   * 
   * @param deck : deck from wich the card is taken
   */
  public void addFrom(Deck deck) {
    Card card = deck.popFirst();
    card.addModelListener(this);
    hand.addFirst(card);
    fireChange();
  }

  /**
   * Method that clear the hand
   */
  public void clearHand() {
    hand.clear();
    fireChange();
  }

  /**
   * Method that remove the last card from the hand
   */
  public void removeFromHand() {
    hand.getDeck().removeLast();
  }

  /**
   * Method that compute the number of card in hand
   * 
   * @return the number of card
   */
  public int getNumberOfCard() {
    return hand.getDeckSize();
  }

  /**
   * Method that return the cards in the hand
   * 
   * @return
   */
  public LinkedList<Card> getCardsFromHand() {
    return hand.getDeck();
  }

  /**
   * Getter
   * 
   * @return the hand
   */
  public Deck getHand() {
    return hand;
  }

  @Override
  public String toString() {
    return getHand().toString();
  }

  @Override
  public void somethingHasChanged(Object source) {
    fireChange();
  }

}
