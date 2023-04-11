package model;

public interface gameModel extends gameState {
    void move(int fromRow, int fromCol, int toRow, int toCol) throws
            IllegalArgumentException;

    boolean gameOver();


}
