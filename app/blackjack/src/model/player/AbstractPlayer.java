package model.player;

import java.util.LinkedList;

import model.card.Card;

import model.pack.Deck;
import util.AbstractListenableModel;

/**
 * Abstract class representing a player
 * A player has a name and a hand composed of cards
 * Extends AbstractListenableModel
 * Implements PlayerInterface
 */
public abstract class AbstractPlayer extends AbstractListenableModel implements PlayerInterface {

  protected Hand hand;
  protected String name;

  /**
   * Constructor
   * 
   * @param name : the name of the player
   */
  public AbstractPlayer(String name) {
    this.hand = new Hand();
    this.name = name;
    hand.addModelListener(this);
  }

  /**
   * Getter
   * 
   * @return the hand
   */
  public Hand getHand() {
    return hand;
  }

  /**
   * Getter
   * 
   * @return the name of the player
   */
  public String getName() {
    return name;
  }

  /**
   * Setter
   * 
   * @param hand the new hand
   */
  protected void setHand(Hand hand) {
    this.hand = hand;
  }

  /**
   * Add a card to the player hand
   * 
   * @param card
   */
  public void hit(Deck deck) {
    hand.addFrom(deck);
  }

  public void stand() {
  }

  public void bust() {
  }

  /**
   * Determines if player achieved a blackjack
   */
  public boolean isBlackjack() {
    if (hand.calculateHand() == 21)
      return true;
    return false;
  }

  /**
   * Determine if the first two cards dealt is a blackjack
   * 
   * @return boolean
   */
  public boolean isNaturalBlackjack() {
    if (hand.getNumberOfCard() == 2 && hand.calculateHand() == 21)
      return true;
    return false;
  }

  /**
   * Clears the hand of a player
   */
  public void clearHand() {
    hand.clearHand();
  }

  /**
   * Return the total of player's hand
   * 
   * @return an integer representing the total
   */
  public int getHandvalue() {
    return hand.calculateHand();
  }

  @Override
  public String toString() {
    return " name=" + getName() +
        ", hand=" + getHand() +
        ", value=" + getHandvalue();
  }

  @Override
  public void somethingHasChanged(Object source) {
    fireChange();
  }

  // Delegation Methods

  public void addFrom(Deck deck) {
    hand.addFrom(deck);
  }

  public void removeFromHand() {
    hand.removeFromHand();
  }

  public int getNumberOfCard() {
    return hand.getNumberOfCard();
  }

  public LinkedList<Card> getCardsFromHand() {
    return hand.getCardsFromHand();
  }

}
