package model.pack;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import model.card.Card;
import util.AbstractListenableModel;
import util.ModelListener;

/**
 * Class Deck
 * Class representing a deck of cards
 * A deck of cards is list of cards.
 * Extends AbstractListenableModel
 */
public class Deck extends AbstractListenableModel implements ModelListener {

  private LinkedList<Card> deck;

  /**
   * Constructor
   * 
   * @param deck : the list of deck
   */
  public Deck(LinkedList<Card> deck) {
    this.deck = deck;
    for (Card card : deck) {
      card.addModelListener(this);
    }
  }

  /**
   * Method for adding a card at the bottom of the deck
   * 
   * @param card : the card to be added
   */
  public void addFirst(Card card) {
    deck.addFirst(card);
    fireChange();

  }

  /**
   * Method for adding a card at the top of the deck
   * 
   * @param card : the card to be added
   */
  public void addLast(Card card) {
    deck.addLast(card);
    fireChange();
  }

  /**
   * Method for adding a list cards to the deck
   * 
   * @param deck : the cards to be added
   */
  public void addAll(Deck deck) {
    this.deck.addAll(deck.getDeck());
  }

  /**
   * Method for retrieving the first card of the deck
   * 
   * @return first card of the deck
   */
  public Card popFirst() {
    if (deck.size() == 0) {
      throw new IndexOutOfBoundsException("Deck is empty");
    }
    Card firstCard = deck.removeFirst();
    fireChange();
    return firstCard;
  }

  /**
   * Method for retrieving the last card of the deck
   * 
   * @return return the last card of the deck
   */
  public Card popLast() {
    if (deck.size() == 0) {
      throw new IndexOutOfBoundsException("Deck is empty");
    }
    Card lastCard = deck.removeLast();
    fireChange();
    return lastCard;
  }

  /**
   * Method for retrieving a card at a given index
   * 
   * @param index : the given index
   * @return card at position index
   */
  public Card popAtIndex(int index) {

    if (index < 0 || index >= deck.size()) {
      throw new ArrayIndexOutOfBoundsException("Index not valid");
    }
    Card card = deck.get(index);
    deck.remove(index);
    fireChange();
    return card;
  }

  /**
   * Getter for the card at a given index
   * 
   * @param index : the given index
   * @return card at position index
   */
  public Card getCardAtIndex(int index) {
    if (index < 0 || index >= deck.size()) {
      throw new ArrayIndexOutOfBoundsException("Index not valid");
    }
    return deck.get(index);
  }

  /**
   * Getter for a random in card in the deck
   * 
   * @return a random card
   */
  public Card getRandomCarte() {
    int size = deck.size();
    Random rand = new Random();
    int randomIndex = rand.nextInt(size);
    return deck.get(randomIndex);
  }

  /**
   * Method for shuffling the deck
   */
  public void shuffle() {
    Collections.shuffle(deck);
    fireChange();
  }

  /**
   * Method that divide the deck in two at a
   * random index
   * 
   * @return one part of the deck
   */
  public Deck cutDeck() {
    Deck cards = new Deck(new LinkedList<>());
    LinkedList<Card> cardsToRemoveFromDeck = new LinkedList<>();
    int size = deck.size();
    Random rand = new Random();
    int randomIndex = rand.nextInt(size);
    for (int i = 0; i <= randomIndex; i++) {
      cards.addLast(deck.get(i));
      cardsToRemoveFromDeck.add(deck.get(i));
    }
    deck.removeAll(cardsToRemoveFromDeck);
    fireChange();
    return cards;
  }

  /**
   * Method removing all cards from deck
   */
  public void clear() {
    deck.removeAll(deck);
  }

  /**
   * Method returning if the deck contains a certain card
   * 
   * @param card
   * @return true or false
   */
  public boolean contains(Card card) {
    return deck.contains(card);
  }

  /**
   * Method returning the size of the deck
   * 
   * @return the size of deck
   */
  public int size() {
    return deck.size();
  }

  /**
   * Getter
   * 
   * @return the deck
   */
  public LinkedList<Card> getDeck() {
    return this.deck;
  }

  /**
   * Getter
   * 
   * @return the size of deck
   */
  public int getDeckSize() {
    return deck.size();
  }

  /**
   * Setter
   * 
   * @param deck : the new value
   */
  public void setDeck(LinkedList<Card> deck) {
    this.deck = deck;
  }

  @Override
  public String toString() {
    return getDeck().toString();
  }

  @Override
  public void somethingHasChanged(Object source) {
    fireChange();
  }

}
