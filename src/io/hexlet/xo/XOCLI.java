package io.hexlet.xo;


import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.Game;
import io.hexlet.xo.model.Player;
import io.hexlet.xo.view.ConsoleView;

public class XOCLI {

    public static void main(final String[] args) {

        final Game gameXO = new Game(new Field(3), "XO");
        final ConsoleView consoleView = new ConsoleView();
        consoleView.getPlayersNames(gameXO);
        consoleView.show(gameXO);
        while(consoleView.move(gameXO)) {
            consoleView.show(gameXO);
        }
    }

}
