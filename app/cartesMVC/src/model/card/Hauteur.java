package model.card;

public enum Hauteur {

  TWO(2),
  THREE(3),
  FOUR(4), 
  FIVE(5), 
  SIX(6), 
  SEVEN(7), 
  EIGHT(8), 
  NINE(9), 
  TEN(10), 
  JACK(11), 
  QUEEN(12), 
  KING(13), 
  ACE(1);

  private int value;

  Hauteur(int value) {
    this.value = value;
  }

  public int getValue(){
    return value;
  }

  public static int getNumberOflevel(){
    return Hauteur.values().length;
  }
}
