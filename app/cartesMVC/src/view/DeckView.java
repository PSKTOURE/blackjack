package view;

import javax.swing.JPanel;

import model.pack.Deck;
import util.ModelListener;
import java.awt.*;

/**
 * Abstract class representing the view for the deck
 * Extends JPanel
 * Implements ModelListener
 */
public abstract class DeckView extends JPanel implements ModelListener {

  protected Deck deck;
  protected Image image;
  protected String name;

  public DeckView(Deck _deck) {
    this(_deck, "");
  }

  /**
   * Construtor
   * 
   * @param _deck : the deck
   * @param _name : the view name
   */
  public DeckView(Deck _deck, String _name) {
    deck = _deck;
    name = _name;
    this.deck.addModelListener(this);
  }

  @Override
  public void somethingHasChanged(Object source) {
    repaint();
  }
}
