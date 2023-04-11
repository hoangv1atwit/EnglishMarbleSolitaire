package view;

import java.io.IOException;

public interface IView {
    void printBoard() throws IOException;

    void printMessage(String message) throws IOException;

    String toString();

}
