import controller.IController;
import controller.gameController;
import model.EnglishSolitaire;
import model.gameModel;
import view.IView;
import view.gameView;

import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        InputStreamReader rd = new InputStreamReader(System.in);

        gameModel mod = new EnglishSolitaire(3, 3, 3);

        IView vie = new gameView(mod);

        IController con = new gameController(mod, vie, rd);

        con.playGame();
    }
}
