package view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;

import model.card.Card;
import util.ModelListener;

/**
 * Class representing the view for a single card
 * A card has two view: visible or hidden
 */
public class ViewCard extends JLabel implements ModelListener {

  private Card card;
  private String path;
  private String path2;

  public ViewCard(Card _card) {
    card = _card;
    path = "src/images/" + card.toString() + ".gif";
    path2 = "src/images/card_hidden.png";
    card.addModelListener(this);
    setIcon(new ImageIcon(path));
  }

  @Override
  public void somethingHasChanged(Object source) {
    String newPath = card.isHidden() ? path2 : path;
    setIcon(new ImageIcon(new ImageIcon(newPath).getImage().getScaledInstance(72, 99, Image.SCALE_DEFAULT)));
  }

}
