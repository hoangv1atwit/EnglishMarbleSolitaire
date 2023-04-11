package controller;

import model.gameModel;
import view.IView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class gameController implements IController {

    private final gameModel model;
    private final IView view;
    private Readable r;

    public gameController(gameModel model, IView view, Readable r) {
        this.model = model;
        this.view = view;
        this.r = r;
        if (model == null || view == null || r == null) {
            throw new IllegalArgumentException("Cannot be null.");
        }
    }

    @Override
    public void playGame() {
        this.printBoard();
        this.printMessage("\nScore: " + model.getScore() + "\n");
        List<Integer> storeInput = new ArrayList<>();
        Scanner scanner = new Scanner(this.r);
        try {
            while (!model.gameOver()) {
                try {
                    storeInput = this.playGameHelper(scanner);
                } catch (IllegalArgumentException e) {
                    this.printMessage("Game quit!" + "\n");
                    this.printBoard();
                    this.printMessage("\nScore: " + model.getScore() + "\n");
                    return;
                }
                if (storeInput.size() == 4) {
                    try {
                        model.move(storeInput.get(0), storeInput.get(1), storeInput.get(2), storeInput.get(3));
                    } catch (IllegalArgumentException e) {
                        this.printMessage("Invalid move. Please play again." + "\n");
                    }
                }
                if (model.gameOver()) {
                    break;
                }
                this.printBoard();
                this.printMessage("\nScore: " + model.getScore() + "\n");
            }
            this.printBoard();
            this.printMessage("\nScore: " + model.getScore() + "\n");

        } catch (NoSuchElementException e) {
            throw new IllegalStateException("There is no such element.");
        }
    }

    private List<Integer> playGameHelper(Scanner sc) {
        List<Integer> storeInput = new ArrayList<>();
        while (storeInput.size() < 4) {
            String input = sc.next();
            if (checkQuitGame(input)) {
                throw new IllegalArgumentException();
            }
            if (checkNumber(input)) {
                int number = Integer.valueOf(input);
                if (number > 0) {
                    storeInput.add(number - 1);
                }
            }
        }
        return storeInput;
    }

    private boolean checkQuitGame(String i) {
        return i.equalsIgnoreCase("q");
    }

    private boolean checkNumber(String s) {
        if (s == null) {
            return false;
        }
        if (s.equals("")) {
            return false;
        }
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This helper method help to render the Board.
     */
    private void printBoard() {
        try {
            view.printBoard();
        } catch (IOException e) {
            throw new IllegalStateException("Illegal State Exception.");
        }
    }

    /**
     * This helper method help to render Message.
     *
     * @param s String s
     */
    private void printMessage(String s) {
        try {
            view.printMessage(s);
        } catch (IOException e) {
            throw new IllegalStateException("Illegal State Exception.");
        }
    }
}
