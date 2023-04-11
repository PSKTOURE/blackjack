package view;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import util.ModelListener;

/**
 * ScoreView Class
 * Represent the view of current game state in a
 * form of a table displaying the value of the hands of player
 * and dealer and how much player and dealer have betted and how
 * much is left in the player's bank
 */
public class ScoreView extends JPanel implements ModelListener {

  BlackjackAdaptator adaptator;

  public ScoreView(BlackjackAdaptator adaptator) {
    this.adaptator = adaptator;
    adaptator.getBlackjack().addModelListener(this);
    JTable table = new JTable(adaptator) {
      @Override
      public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        Color lightBlue = new Color(51, 153, 255);
        Color lightGreen = new Color(102, 255, 102);
        if (!c.getBackground().equals(getSelectionBackground())) {
          Color colour = (row % 2 == 0 ? lightBlue : lightGreen);
          c.setBackground(colour);
        }
        return c;
      }
    };

    table.setRowHeight(table.getRowHeight() + 30);
    JScrollPane scroolPanel = new JScrollPane(table);
    scroolPanel.setPreferredSize(new Dimension(1500, 200));
    this.add(scroolPanel);
  }

  @Override
  public void somethingHasChanged(Object source) {
    repaint();
  }

}
