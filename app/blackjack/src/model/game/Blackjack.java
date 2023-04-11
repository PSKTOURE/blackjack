package model.game;

import model.pack.Deck;
import model.pack.DeckBuilder;
import model.player.Dealer;
import model.player.Player;
import util.AbstractListenableModel;
import util.ModelListener;

/**
 * Class Blackjack
 * Model representing the blackjack game
 * A blackjack is composed by a dealer and player each
 * taking card from a deck of cards
 */
public class Blackjack extends AbstractListenableModel implements ModelListener, BlackjackInterface, Runnable {

  private Deck deck;
  private Dealer dealer;
  private Player player;
  private String winner = "";

  /**
   * Constructor
   * 
   * @param deckSize : the size of the deck
   */
  public Blackjack(int deckSize) {
    this.deck = new DeckBuilder().buildDeck(deckSize);
    this.dealer = new Dealer();
    dealer.addModelListener(this);
  }

  /**
   * Method that instantite the player
   * 
   * @param name
   */
  public void setPlayer(String name) {
    this.player = new Player(name);
  }

  /**
   * Method for initialising the game
   * Cards are dealt to dealer and player
   * and delear card is hidden
   */
  public void initialize() {
    deck.shuffle();
    try {
      dealer.addFrom(deck);
      dealer.getCardsFromHand().getFirst().setHidden(true);
      fireChange();
      Thread.sleep(1000);
      player.addFrom(deck);
      fireChange();
      Thread.sleep(1000);
      dealer.addFrom(deck);
      fireChange();
      Thread.sleep(1000);
      player.addFrom(deck);
      fireChange();
    } catch (Exception e) {
      System.out.println("Thread Interrupted");
    }
  }

  /**
   * Method setting visible dealer's card
   */
  public void delearCard() {
    dealer.getCardsFromHand().getLast().setHidden(false);
  }

  /**
   * Method for checking who won the current round
   */
  public void checkRoundWinner() {
    if (player.isNaturalBlackjack()) {
      player.blackjack();
      winner = "player";
      return;
    }
    if (player.getHandvalue() > 21) {
      player.bust();
      winner = "dealer";
      return;
    }
    if (dealer.getHandvalue() > 21) {
      player.win();
      winner = "player";
      return;
    }
    if (player.getHandvalue() == dealer.getHandvalue() || player.getHandvalue() > 21 && dealer.getHandvalue() > 21) {
      player.push();
      winner = "";
      return;
    }
    if (player.getHandvalue() > dealer.getHandvalue() && player.getHandvalue() < 21) {
      player.win();
      winner = "player";
      return;
    }
    if (player.getHandvalue() == 21) {
      player.win();
      winner = "player";
      return;
    }
    player.loss();
    winner = "dealer";
  }

  /**
   * Method that removes all cards from
   * the player and the dealer
   */
  public void clearHands() {
    deck.addAll(dealer.getHand().getHand());
    deck.addAll(player.getHand().getHand());
    dealer.clearHand();
    player.clearHand();
  }

  /**
   * Getter
   * 
   * @return the deck
   */
  public Deck getDeck() {
    return deck;
  }

  /**
   * Getter
   * 
   * @return the dealer
   */
  public Dealer getDealer() {
    return dealer;
  }

  /**
   * Getter
   * 
   * @return the player
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Getter
   * 
   * @return the name of the winner
   */
  public String getWinner() {
    return winner;
  }

  @Override
  public void somethingHasChanged(Object source) {
    fireChange();
  }

  @Override
  public void run() {
    initialize();

  }

}
