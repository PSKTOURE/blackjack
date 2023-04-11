package view;

import javax.swing.table.AbstractTableModel;
import model.game.Blackjack;
import model.game.BlackjackInterface;
import model.player.Dealer;
import model.player.Player;
import model.player.PlayerInterface;
import util.ModelListener;

/**
 * Class BlackjackAdaptator
 * Class adapting the Blackjack class to a Table by
 * making use of the adaptor pattern
 * The table show se hand value, the bet and the current bank amount
 * of player and the dealer
 * The dealer has infinte amount in bank and will always double the bet of the
 * player (Arbritary choice)
 * Implements BlackjackInterface and ModelListener
 * Extends AbstractTableModel
 */
public class BlackjackAdaptator extends AbstractTableModel implements BlackjackInterface, ModelListener {

  private Blackjack blackjack;
  private static final String[] COL_NAMES = { "Player Name", "Hand Value", "Current Bet", "Bank" };

  /**
   * Constructor
   * 
   * @param blackjack
   */
  public BlackjackAdaptator(Blackjack blackjack) {
    this.blackjack = blackjack;
    blackjack.addModelListener(this);

  }

  @Override
  public int getColumnCount() {
    return COL_NAMES.length;
  }

  @Override
  public int getRowCount() {
    return 2;
  }

  @Override
  public Object getValueAt(int arg0, int arg1) {
    Player player = blackjack.getPlayer();
    Dealer dealer = blackjack.getDealer();
    if (arg0 == 0) {
      if (arg1 == 0)
        return player.getName();
      if (arg1 == 1)
        return player.getHandvalue();
      if (arg1 == 2)
        return player.getBet();
      if (arg1 == 3)
        return player.getBank();
    }
    if (arg0 == 1) {
      if (arg1 == 0)
        return dealer.getName();
      if (arg1 == 1)
        return dealer.getHandvalue();
      if (arg1 == 2)
        return player.getBet();
      if (arg1 == 3)
        return "INFINITY";
    }
    return null;
  }

  public Blackjack getBlackjack() {
    return blackjack;
  }

  @Override
  public String getColumnName(int index) {
    return COL_NAMES[index];
  }

  @Override
  public PlayerInterface getPlayer() {
    return blackjack.getPlayer();
  }

  @Override
  public PlayerInterface getDealer() {
    return blackjack.getDealer();
  }

  @Override
  public void somethingHasChanged(Object source) {
    fireTableDataChanged();
  }

}
