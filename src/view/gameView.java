package view;

import java.io.IOException;
import model.gameState;

public class gameView implements IView {
  private final gameState type;
  private final Appendable output;
  public gameView(gameState type) {
    if (type == null) {
      throw new IllegalArgumentException("Type cannot be null.");
    }
    this.type = type;
    output = System.out;
  }

  @Override
  public String toString() {
    int l = ((this.type.getSize() + 2) / 3) * 2 - 2;
    int s = this.type.getSize();
    int a = (this.type.getSize() + 2) / 3 - 1;

    Appendable stringBuilder = new StringBuilder();
    for (int row = 0; row < s; row++) {
      for (int col = 0; col < s; col++) {
        if (this.type.getPosition(row, col) == gameState.PosState.Marble) {
          if ((row > l && col == l) || (row < a && col == l) || col == s - 1) {
            Utils.write(stringBuilder, "O");
          } else {
            Utils.write(stringBuilder, "O ");
          }
        } else if (this.type.getPosition(row, col) == gameState.PosState.Invalid
                && col < this.type.getSize() / 2) {
          Utils.write(stringBuilder, "  ");
        } else if (this.type.getPosition(row, col) == gameState.PosState.Empty) {
          if ((row > l && col == l) || (row < a && col == l) || col == s - 1) {
            Utils.write(stringBuilder, "_");
          } else {
            Utils.write(stringBuilder, "_ ");
          }
        }
      }
      if (row < s - 1) {
        Utils.write(stringBuilder, "\n");
      }
    }
    return stringBuilder.toString();
  }

  @Override
  public void printBoard() throws IOException {
    output.append(this.toString());
  }

  @Override
  public void printMessage(String message) throws IOException {
    output.append(message);
  }
}


