package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.game.Blackjack;
import model.player.PlayerInterface;
import util.ModelListener;

/**
 * Class representing the view of the blackjack game
 * Extends JPanel
 * Implements ModelListener
 */
public class BlackjackView extends JPanel implements ModelListener {
	private Blackjack game;
	private Image img = null;
	private Image imgBack = null;

	/**
	 * Construtor
	 * 
	 * @param game : reference to the game
	 */
	public BlackjackView(Blackjack game) {
		this.game = game;
		game.addModelListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			imgBack = ImageIO.read(new File("src/images/background.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		g.drawImage(imgBack, 0, 0, this.getWidth(), this.getHeight(), this);

		int x = 20;
		int y = 20;

		for (int i = 0; i < game.getDeck().size(); i++) {
			String imagePath = "src/images/card_hidden.png";

			try {
				img = ImageIO.read(new File(imagePath));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			g.drawImage(img, x, y, 72, 99, this);

			x += 2;
			y += 1;
		}
		if (this.game.getDealer().getHand().getNumberOfCard() != 0) {
			handsView(g);
		}
	}

	/**
	 * Method for painting the hands of dealer and player
	 * 
	 * @param g
	 */
	public void handsView(Graphics g) {
		g.setColor(Color.white);
		Font font = new Font("Arial", Font.BOLD, 18);
		g.setFont(font);
		g.drawString("Dealer", 400, 30);
		g.drawString(this.game.getPlayer().getName(), 400, 210);

		drawHand(g, game.getDealer(), 50);
		drawHand(g, game.getPlayer(), 250);

	}

	/**
	 * Method for painting one hand
	 * 
	 * @param g
	 * @param player
	 * @param y
	 */
	private void drawHand(Graphics g, PlayerInterface player, int y) {
		int x = 400;
		String imagePath = "";
		for (int i = 0; i < player.getHand().getCardsFromHand().size(); i++) {
			boolean isHidden = player.getHand().getCardsFromHand().get(i).isHidden();
			String s1 = "src/images/card_hidden.png";
			String s2 = "src/images/" + player.getHand().getCardsFromHand().get(i).toString() + ".gif";
			imagePath = isHidden ? s1 : s2;
			try {
				img = ImageIO.read(new File(imagePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.drawImage(img, x, y, 72, 99, this);
			x += 80;
		}
	}

	@Override
	public void somethingHasChanged(Object source) {
		repaint();
	}

}
