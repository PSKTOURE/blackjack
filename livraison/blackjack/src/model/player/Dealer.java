package model.player;

import model.pack.Deck;

/**
 * Class representing the dealer
 */
public class Dealer extends AbstractPlayer {

  public Dealer() {
    super("Dealer");
  }

  /**
   * Method automating the moves of the dealer
   * 
   * @param deck : the game deck
   */
  public void dealerPlay(Deck deck) {
    int value = getHandvalue();
    // Delear keep taking card until the value of its hand
    // is greater than 17
    while (value < 17) {
      hit(deck);
      value = getHandvalue();
    }
    // if the value of its hand is between 17 and 21
    // dealer stand
    if (value >= 17 && value <= 21)
      stand();

    if (value > 21)
      bust();
  }

  @Override
  public void push() {
  }

}
