package model.game;

import model.player.PlayerInterface;

/**
 * Interface  representing the blackjack game
 */
public interface BlackjackInterface {
  
  public PlayerInterface getPlayer();
  public PlayerInterface getDealer();
}
