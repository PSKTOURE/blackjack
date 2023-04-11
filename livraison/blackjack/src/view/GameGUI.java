package view;

import java.awt.Container;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import model.game.Blackjack;
import model.player.Player;

/**
 * The Graphical User Interface of blackjack game
 * Plays the role of the view and the controller
 */
public class GameGUI extends JFrame {

  private BlackjackView blackjackView;
  private JPanel scoreView;
  private JPanel buttonsPanel;
  private Blackjack game;
  private JButton buttonStart;
  private JButton buttonHit;
  private JButton buttonStand;
  private JButton buttonDeal;
  private JButton buttonEndGame;
  private JButton buttonPlayAgain;
  private static final String[] OPTIONS = { "YES", "NO" };

  /**
   * Constructor
   * 
   * @param game
   */
  public GameGUI(Blackjack game) {
    super("Blackjack");
    this.game = game;
    // Get main container
    Container contentPane = this.getContentPane();
    contentPane.setLayout(new BorderLayout());

    // Instantiate Panel in wich all the buttons will be
    // and setting its layout
    buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new GridLayout(1, 5));
    buttonsPanel.setPreferredSize(new Dimension(1500, 80));

    // Start button for starting the game
    buttonStart = new JButton("Start");
    buttonStart.addActionListener(this::start);
    buttonsPanel.add(buttonStart);

    // End button for quitting the game
    buttonEndGame = new JButton("End Game");
    buttonEndGame.setEnabled(false);
    buttonEndGame.addActionListener(this::end);
    buttonsPanel.add(buttonEndGame);

    // The hit button for taking one card from deck
    buttonHit = new JButton("HIT");
    buttonHit.addActionListener(this::hit);
    buttonHit.setVisible(false);
    buttonsPanel.add(buttonHit);

    // Stand button
    buttonStand = new JButton("STAND");
    buttonStand.addActionListener(this::stand);
    buttonsPanel.add(buttonStand);
    buttonStand.setVisible(false);

    // Deal button : deals cards to player and dealer
    buttonDeal = new JButton("DEAL");
    buttonDeal.addActionListener(this::deal);
    buttonsPanel.add(buttonDeal);
    buttonDeal.setVisible(false);

    // Play Again button: for continuing playing
    buttonPlayAgain = new JButton("Play Again");
    buttonPlayAgain.addActionListener(this::playAgain);
    buttonsPanel.add(buttonPlayAgain);
    buttonPlayAgain.setVisible(false);

    this.blackjackView = new BlackjackView(game);
    contentPane.add(blackjackView, BorderLayout.CENTER);
    contentPane.add(buttonsPanel, BorderLayout.SOUTH);
    setSize(1500, 1000);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  /**
   * Action that start the game by asking the player to enter
   * its name
   * 
   * @param event
   */
  public void start(ActionEvent event) {
    buttonStart.setVisible(false);
    String playerName = JOptionPane.showInputDialog(this, "Enter name");
    game.setPlayer(playerName);
    getBet();
    buttonDeal.setVisible(true);
    buttonEndGame.setEnabled(true);
    this.scoreView = new ScoreView(new BlackjackAdaptator(game));
    getContentPane().add(scoreView, BorderLayout.NORTH);
  }

  /**
   * Method asking if the player when take a card when the action is hit
   * Adds card to player hand if yes
   */
  public void ask() {
    int x = JOptionPane.showOptionDialog(null, "Do you wish take a card?",
        "Click a button",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, OPTIONS, OPTIONS[0]);
    if (x == 0)
      this.game.getPlayer().hit(game.getDeck());
  }

  /**
   * Action hit
   * 
   * @param event
   */
  public void hit(ActionEvent event) {
    ask();
    repaint();
    ask();
    play();
  }

  /**
   * Action stand
   * 
   * @param event
   */
  public void stand(ActionEvent event) {
    this.game.getPlayer().stand();
    play();
  }

  /**
   * Action deal: deals cards to player and dealer
   * 
   * @param event
   */
  public void deal(ActionEvent event) {
    buttonHit.setVisible(true);
    buttonStand.setVisible(true);
    buttonDeal.setVisible(false);
    Thread thread = new Thread(game);
    thread.start();
  }

  /**
   * Action end: end the game by exiting program
   * 
   * @param event
   */
  public void end(ActionEvent event) {
    int x = JOptionPane.showOptionDialog(null, "Are you sure you want to quit?",
        "Click a button",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, OPTIONS, OPTIONS[0]);
    if (x == 0) {
      System.exit(x);
    }
  }

  /**
   * Action playAgain: reset player's and dealer's hand
   * Reshuffle the deck, ask for player's bet
   * Reset player bank to its original value if player bank is down to zero
   * 
   * @param event
   */
  public void playAgain(ActionEvent event) {
    if (game.getPlayer().getBank() == 0)
      game.getPlayer().setBank(Player.BANK_AMOUNT);
    buttonPlayAgain.setVisible(false);
    game.clearHands();
    game.getDeck().shuffle();
    getBet();
    buttonHit.setVisible(false);
    buttonStand.setVisible(false);
    buttonDeal.setVisible(true);
  }

  /**
   * Method launching the dealer play and then
   * Verifying the winner of the round
   */
  public void play() {
    game.getDealer().getHand().getCardsFromHand().getLast().setHidden(false);
    game.getDealer().dealerPlay(game.getDeck());
    game.checkRoundWinner();
    String winner = game.getWinner();
    if (winner == "") {
      JOptionPane.showMessageDialog(null, "Tie", "No winner", JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null, winner + " has won!!", "Winner",
          JOptionPane.INFORMATION_MESSAGE);
    }
    buttonPlayAgain.setVisible(true);
    buttonHit.setVisible(false);
    buttonStand.setVisible(false);

  }

  /**
   * Method for asking the player to enter
   * 
   */

  public void getBet() {
    boolean isValid = true;
    do {
      isValid = true;
      String betS = JOptionPane.showInputDialog("Enter Bet");
      int bet = Integer.parseInt(betS);
      try {
        game.getPlayer().setBet(bet);
      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(this, "Invalid bet", "Error", JOptionPane.ERROR_MESSAGE);
        isValid = false;
      }
    } while (!isValid);
  }

}
