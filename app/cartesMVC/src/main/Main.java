package main;


import model.pack.Deck;
import model.pack.DeckBuilder;
import view.DeckGUI;

public class Main {
  
  public static void main(String[] args){

    Deck pack = new DeckBuilder().buildDeck(32);
    DeckGUI gui = new DeckGUI(pack);

  }
  
}
