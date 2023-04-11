package view;

import java.util.Scanner;

import model.game.Blackjack;

/**
 * Class representing the console(terminal) view of the
 * blackjack game
 */
public class BlackjackConsoleView {

  private Blackjack game;
  private Scanner scanner;
  private boolean gameOver;

  public BlackjackConsoleView(int deckSize) {
    game = new Blackjack(deckSize);
    this.scanner = new Scanner(System.in);
    gameOver = false;
  }

  /**
   * Method for asking the player's name
   * and instantiate the player
   */
  public void setPlayer() {
    System.out.println("Enter player name");
    String playerName = scanner.next();
    game.setPlayer(playerName);
  }

  /**
   * Method for initialising the game by
   * dealing the card to the player and dealer
   */
  public void initialize() {
    game.getDeck().shuffle();
    game.getDealer().addFrom(game.getDeck());
    game.getDealer().getCardsFromHand().getFirst().setHidden(true);
    game.getPlayer().addFrom(game.getDeck());
    game.getDealer().addFrom(game.getDeck());
    game.getPlayer().addFrom(game.getDeck());

    System.out.println(game.getDealer());
    System.out.println(game.getPlayer());
    return;
  }

  /**
   * Method asking for the player bet
   */
  public void getBet() {
    boolean isValid = true;
    do {
      isValid = true;
      System.out.println("How much do you want to bet?");
      String betString = scanner.next();
      try {
        int bet = Integer.parseInt(betString);
        game.getPlayer().setBet(bet);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid bet");
        isValid = false;
      }
    } while (!isValid);
  }

  /**
   * Method for asking if player wants to hit
   * or stand
   */
  public void playerDecision() {
    char decision = '0';
    while (!(decision == 'H' || decision == 'S')) {
      System.out.println("What is your next move? (H)it or (S)tand ?");
      String input = scanner.next();
      decision = input.toUpperCase().charAt(0);
      if (decision == 'H')
        game.getPlayer().hit(game.getDeck());
      if (decision == 'S')
        game.getPlayer().stand();
    }
    System.out.println(game.getPlayer());
  }

  /**
   * Method revealing the dealer's hidden card
   */
  public void delearCard() {
    game.delearCard();
    System.out.println(game.getDealer());
  }

  /**
   * Method for checking who has won the round
   */
  public void checkRoundWinner() {
    game.checkRoundWinner();
    String winner = game.getWinner();
    if (game.getPlayer().getHandvalue() > 21 && winner == "dealer") {
      System.out.println("player busted, you loose");
      return;
    }
    if (game.getDealer().getHandvalue() > 21 && winner == "player") {
      System.out.println("Dealer busted, you won");
      return;
    }
    if (winner == "") {
      System.out.println("player and dealer are tied");
      return;
    }
    if (winner == "player") {
      System.out.println("You won");
      return;
    }
    if (game.getPlayer().getHandvalue() == 21 && winner == "player") {
      System.out.println("Player blackjack");
      return;
    }
    System.out.println("Your hand is less than of the dealer, you loose");
  }

  /**
   * Method for computing how much is left in
   * the player's bank
   * 
   * @return true if bank not empty else false
   */
  public boolean checkPlayerBank() {
    if (game.getPlayer().getBank() > 0)
      return true;
    System.out.println("Your bank is empty game over");
    game.getPlayer().removeFromGame();
    gameOver = true;
    return false;
  }

  /**
   * Method for asking if the player wants
   * keep playing or not
   */
  public void keepPlaying() {
    if (!checkPlayerBank())
      return;
    char decision = '0';
    while (!(decision == 'Y' || decision == 'N')) {
      System.out.println("You have " + game.getPlayer().getBank() + " currently");
      System.out.println("Do you wish to continue? (Y)es or (N)o");
      String input = scanner.next();
      decision = input.toUpperCase().charAt(0);
      if (decision == 'Y')
        gameOver = false;
      if (decision == 'N')
        gameOver = true;
    }
  }

  /**
   * Getter
   * 
   * @return if the game is over
   */
  public boolean isGameOver() {
    return gameOver;
  }

  /**
   * Delegation removing all cards from
   * player and delaer hands
   */
  public void clearHands() {
    game.clearHands();
  }

  /**
   * The game Loop
   */
  public void play() {
    setPlayer();
    while (!isGameOver()) {
      initialize();
      getBet();
      playerDecision();
      delearCard();
      System.out.println("Dealer is playing...");
      game.getDealer().dealerPlay(game.getDeck());
      System.out.println(game.getDealer());
      checkRoundWinner();
      clearHands();
      checkPlayerBank();
      keepPlaying();
      isGameOver();
    }
  }

}
