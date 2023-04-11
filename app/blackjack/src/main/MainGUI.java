package main;

import model.game.Blackjack;
import view.GameGUI;

public class MainGUI {
  
  public static void main(String[] args){
    Blackjack blackjack = new Blackjack(52);
    GameGUI gui = new GameGUI(blackjack);
  }

}
