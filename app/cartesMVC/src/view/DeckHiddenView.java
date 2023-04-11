package view;

import java.io.File;
import java.io.IOException;
import java.awt.*;

import javax.imageio.ImageIO;

import model.pack.Deck;

/**
 * Class reprensint the view for hidden deck
 * Extends DeckView
 */
public class DeckHiddenView extends DeckView {

  public DeckHiddenView(Deck deck) {
    super(deck);
  }

  public DeckHiddenView(Deck deck, String name) {
    super(deck, name);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.BLACK);
    Font font = new Font("Arial", Font.BOLD, 18);
    g.setFont(font);
    g.drawString(name, 100, 15);
    int x = 100;
    int y = 30;
    for (int i = 0; i < deck.size(); i++) {
      String imagePath = "src/images/card_hidden.png";
      try {
        image = ImageIO.read(new File(imagePath));
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
      g.drawImage(image, x, y, 72, 99, this);
      x += 2;
      y += 1;
    }
  }

  @Override
  public void somethingHasChanged(Object source) {
    repaint();
  }

}
