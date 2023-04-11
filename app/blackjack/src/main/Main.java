package main;


import view.BlackjackConsoleView;


public class Main {

  public static void main(String[] args) {
    BlackjackConsoleView cv = new BlackjackConsoleView(52);
    cv.play();
  }
}
