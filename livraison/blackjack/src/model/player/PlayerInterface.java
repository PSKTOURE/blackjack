package model.player;

import util.ModelListener;
/**
 * Interface representing a player in the game Blackjack
 * Extends ModelListener
 */
public interface PlayerInterface extends ModelListener{
  
  public void bust();
  
  public void push();

  public void stand();

  public int getHandvalue();

  public Hand getHand();

  public boolean isBlackjack();

  public String getName();

}
