package view;

import java.awt.Container;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;

import model.card.Card;
import model.pack.Deck;

/**
 * GUI for demonstrating the differents views
 */
public class DeckGUI extends JFrame {

  private DeckHiddenView hiddenView;
  private DeckVisibleView visibleView;
  private ViewCard cardView;
  private JPanel panelSouth = new JPanel();
  private JPanel panelCenter = new JPanel();
  private JButton addButton = new JButton("Add");
  private JButton popButton = new JButton("Pop");
  private JButton flipButton = new JButton("Flip");
  private Deck deck;
  private Card card;

  public DeckGUI(Deck _deck) {
    super("Deck View");
    deck = _deck;
    card = deck.getRandomCarte();
    hiddenView = new DeckHiddenView(deck, "Hidden Deck");
    hiddenView.setPreferredSize(new Dimension(700, 170));
    visibleView = new DeckVisibleView(deck, "Visible Deck");
    visibleView.setPreferredSize(new Dimension(700, 700));
    cardView = new ViewCard(card);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setPreferredSize(new Dimension(700, 70));
    buttonPanel.add(addButton);
    buttonPanel.add(popButton);
    buttonPanel.add(flipButton);

    panelCenter.setLayout(new BorderLayout());
    panelSouth.setPreferredSize(new Dimension(700, 200));
    panelSouth.add(cardView);

    Container container = this.getContentPane();
    container.setLayout(new BorderLayout());
    JScrollPane scrollPane = new JScrollPane(visibleView, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setPreferredSize(new Dimension(400, 300));

    panelCenter.add(scrollPane, BorderLayout.CENTER);
    panelCenter.add(hiddenView, BorderLayout.NORTH);
    panelCenter.add(buttonPanel, BorderLayout.SOUTH);

    container.add(panelCenter, BorderLayout.CENTER);
    container.add(panelSouth, BorderLayout.SOUTH);

    addButton.addActionListener(this::add);
    popButton.addActionListener(this::pop);
    flipButton.addActionListener(this::flip);

    setSize(700, 1000);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void add(ActionEvent event) {
    deck.addFirst(deck.popLast());
  }

  private void pop(ActionEvent event) {
    deck.popFirst();
  }

  private void flip(ActionEvent flip) {
    card.setHidden(!card.isHidden());
  }
}
