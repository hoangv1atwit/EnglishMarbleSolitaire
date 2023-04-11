package view;

import java.io.IOException;
public class Utils {
  public static void write(Appendable out, String message) {
    try {
      out.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("Something went wrong while writing to the appendable.");
    }
  }
}

