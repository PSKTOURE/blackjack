package model.player;

import model.pack.Deck;

/**
 * Class representing the Player
 * A player has a certain amount of money in its bank
 * and can bet a certain amount
 * Extends AbstractPlayer
 */
public class Player extends AbstractPlayer {

  private float bank;
  private int bet;
  public static int BANK_AMOUNT = 1000;

  public Player(String name) {
    super(name);
    bank = BANK_AMOUNT;
    bet = 0;
  }

  // Removes a player's bet from their bank if they bust. Sets bet to zero
  // afterwards.
  public void bust() {
    setBank(bank - bet);
    setBet(0);
  }

  // Adds a player's bet from their bank if they win. Sets bet to zero afterwards.
  public void win() {
    setBank(bank + bet);
    setBet(0);
  }

  // Sets a player's bet to 0 if he "push".
  public void push() {
    bet = 0;
  }

  /**
   * Adds a card to player hand
   * when player decide to hit
   */
  @Override
  public void hit(Deck deck) {
    hand.addFrom(deck);
  }

  // Removes a player's bet from their bank if they lose. Sets bet to zero
  // afterwards.
  public void loss() {
    setBank(bank - bet);
    setBet(0);
  }

  // This sets the player bank to -1. -1 is unreachable and they are removed from
  // the game
  public void removeFromGame() {
    bank = -1;
  }

  // This calculate the gain for a player who has a Blackjack
  public void blackjack() {
    setBank((float) (bank + 1.5 * bet));
    bet = 0;
  }

  /**
   * Set the bet of the player
   * Throws an illegal an exception if bet is invalid
   * 
   * @param newBet
   */
  public void setBet(int newBet) throws IllegalArgumentException {
    if (newBet < 0 || newBet > bank) {
      throw new IllegalArgumentException("The bet is invalid");
    }
    bet = newBet;
    fireChange();
  }

  /**
   * Getter
   * 
   * @return the bet of the player
   */
  public int getBet() {
    return this.bet;
  }

  /**
   * Getter
   * 
   * @return the total amount present in palyer's bank
   */
  public float getBank() {
    return bank;
  }

  /**
   * Setter
   * 
   * @param amount : the new value of the player bank
   */
  public void setBank(float amount) {
    this.bank = amount;
    fireChange();
  }

  @Override
  public String toString() {
    return super.toString() + ", bet= " + bet + " ,bank = " + bank;
  }

}
