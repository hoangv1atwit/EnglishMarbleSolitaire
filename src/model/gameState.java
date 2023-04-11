package model;

public interface gameState {
  int getSize();
  PosState getPosition(int row, int col) throws IllegalArgumentException;
  int getScore();
  enum PosState { Empty, Marble, Invalid }

}
