package model;

public class EnglishSolitaire implements gameModel {
    private final int length;
    private PosState[][] game;

    public EnglishSolitaire(int length, int sRow, int sCol) {
        if (length <= 0 || length % 2 == 0 || sRow >= 3 * length - 2 || sCol >= 3 * length - 2) {
            throw new IllegalArgumentException("length thickness must a positive odd number and cell position must be valid!");
        }
        int size = 3 * length - 2;
        game = new PosState[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                game[i][j] = PosState.Marble;
            }
        }
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1; j++) {
                game[i][j] = PosState.Invalid;
            }
        }
        for (int i = 2 * length - 1; i < size; i++) {
            for (int j = 2 * length - 1; j < size; j++) {
                game[i][j] = PosState.Invalid;
            }
        }
        for (int i = 0; i < length - 1; i++) {
            for (int j = 2 * length - 1; j < size; j++) {
                game[i][j] = PosState.Invalid;
            }
        }
        for (int i = 2 * length - 1; i < size; i++) {
            for (int j = 0; j < length - 1; j++) {
                game[i][j] = PosState.Invalid;
            }
        }
        if (game[sRow][sCol] == PosState.Marble) {
            game[sRow][sCol] = PosState.Empty;
        } else {
            throw new IllegalArgumentException("This is an invalid position.");
        }
        this.length = length;
    }

    @Override
    public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
        if (fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0) {
            throw new IllegalArgumentException("Must be a positive number!");
        }
        if (!legalMove(fromRow, fromCol, toRow, toCol)) {
            throw new IllegalArgumentException("Must move horizontally or vertically!");
        }
        int midRow = (fromRow + toRow) / 2;
        int midCol = (fromCol + toCol) / 2;

        game[fromRow][fromCol] = PosState.Empty;
        game[midRow][midCol] = PosState.Empty;
        game[toRow][toCol] = PosState.Marble;
    }

    public boolean legalMove(int fromRow, int fromCol, int toRow, int toCol) {
        int r = Math.abs(toRow - fromRow);
        int c = Math.abs(toCol - fromCol);
        int midRow = (fromRow + toRow) / 2;
        int midCol = (fromCol + toCol) / 2;
        int size = 3 * length - 2;

        return (fromRow >= 0 && fromCol >= 0 && toRow >= 0 && toCol >= 0
                && fromRow < size && fromCol < size && toRow < size && toCol < size
                && game[fromRow][fromCol] == PosState.Marble
                && game[midRow][midCol] == PosState.Marble
                && game[toRow][toCol] == PosState.Empty
                && ((r == 2 && c == 0) || (r == 0 && c == 2)));
    }


    @Override
    public boolean gameOver() {
        int size = 3 * length - 2;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (canMove(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public PosState getPosition(int row, int col) throws IllegalArgumentException {
        int size = 3 * length - 2;
        if (row < 0 || col < 0 && row > size || col > size) {
            throw new IllegalArgumentException("The row or the column must be within the dimensions of the board");
        }
        return game[row][col];
    }

    @Override
    public int getScore() {
        int score = 0;
        int size = 3 * length - 2;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (game[i][j] == PosState.Marble) {
                    score++;
                }
            }
        }
        return score;
    }

    public boolean canMove(int row, int col) {
        int size = 3 * length - 2;
        if (row < 0 || row > size || col < 0 || col > size) {
            throw new IllegalArgumentException("Illegal move!");
        }
        return (legalMove(row, col, row, col + 2)
                || legalMove(row, col, row, col - 2)
                || legalMove(row, col, row + 2, col)
                || legalMove(row, col, row - 2, col));
    }

    @Override
    public int getSize() {
        int size = 3 * length - 2;
        return size;
    }


}
