package view;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;

import model.card.Card;
import model.pack.Deck;

/**
 * Class representing the view for visible deck
 * of cards
 */
public class DeckVisibleView extends DeckView {

  public DeckVisibleView(Deck deck) {
    super(deck);
  }

  public DeckVisibleView(Deck deck, String name) {
    super(deck, name);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.BLACK);
    Font font = new Font("Arial", Font.BOLD, 18);
    g.setFont(font);
    g.drawString(name, 100, 30);
    int x = 60;
    int y = 60;
    for (int i = 0; i < deck.size(); i++) {
      Card card = deck.getCardAtIndex(i);
      String imagePath = card.isHidden() ? "src/images/card_hidden.png" : "src/images/" + card + ".gif";

      try {
        image = ImageIO.read(new File(imagePath));
      } catch (IOException e) {
        e.printStackTrace();
      }
      g.drawImage(image, x, y, 72, 99, this);

      x += 80;
      if (x >= 600) {
        x = 60;
        y += 100;
      }
    }
  }

}
