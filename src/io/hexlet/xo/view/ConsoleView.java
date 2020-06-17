package io.hexlet.xo.view;


import io.hexlet.xo.controllers.CurrentMoveController;
import io.hexlet.xo.controllers.MoveController;
import io.hexlet.xo.controllers.WinnerController;
import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.Game;
import io.hexlet.xo.model.Player;
import io.hexlet.xo.model.exceptions.AlreadyOccupiedException;
import io.hexlet.xo.model.exceptions.InvalidFieldSize;
import io.hexlet.xo.model.exceptions.InvalidPointException;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {

    private final CurrentMoveController currentMoveController = new CurrentMoveController();

    private final WinnerController winnerController = new WinnerController();

    private final MoveController moveController = new MoveController();

    public void getPlayersNames(Game game){
        final Scanner in = new Scanner(System.in);
        final Player[] players = new Player[2];

        System.out.println("First name player (X figure): ");
        players[0] = new Player(in.next(), Figure.X);
        System.out.println("Second name player (O figure) : ");
        players[1] = new Player(in.next(), Figure.O);

        game.setPlayers(players);


    }

    public void setGameRules(Game game){
        final Scanner in = new Scanner(System.in);

        System.out.println("Set game size (0 for classic 3 size): ");
        boolean t = true;
        while (t) {

            try {
                int size = in.nextInt();
                if (size != 0) game.getField().setFiledSize(size);
                t = false;
            } catch (InvalidFieldSize e) {
                System.out.println("Please enter size more 1 and less 10");
                continue;

            }
        }

    }

    public void show(final Game game) {
        System.out.format("Game name: %s\n", game.getName());
        final Field field = game.getField();
        for (int x = 0; x < field.getSize(); x++) {
            if (x != 0)
                printSeparator(game);
            printLine(field, x);
        }
    }

    public boolean move(final Game game) {
        final Field field = game.getField();
        final Figure winnerFig = winnerController.getWinner(field);
        final Player currentPlayer = currentMoveController.currentPlayer(field, game);

        if (winnerFig != null) {
            Player player =  game.getPlayers()[0];
            if (game.getPlayers()[0].equals(currentPlayer))
                player = game.getPlayers()[1];
            System.out.format("Winner is : %s with figure %s\n", player, winnerFig);
            return false;
        }
        final Figure currentFigure = currentMoveController.currentMove(field);

        if (currentFigure == null) {
            System.out.println("No winner and no moves left!");
            return false;
        }
        System.out.format("Please %s enter move point for %s\n", currentPlayer, currentFigure);
        final Point point = askPoint();
        try {
            moveController.applyFigure(field, point, currentFigure);
        } catch (final InvalidPointException | AlreadyOccupiedException e) {
            System.out.println("Point is invalid!");
        }
        return true;
    }

    private Point askPoint() {
        return new Point(askCoordinate("X") - 1, askCoordinate("Y") - 1);
    }

    private int askCoordinate(final String coordinateName) {
        System.out.format("Please input %s:", coordinateName);
        final Scanner in = new Scanner(System.in);
        try {
            return in.nextInt();
        } catch (final InputMismatchException e) {
            System.out.println("Mistake in coordinate!");
            return askCoordinate(coordinateName);
        }
    }

    private void printLine(final Field field,
                           final int x) {
        for (int y = 0; y < field.getSize(); y++) {
            if (y != 0)
                System.out.print("|");
            System.out.print(" ");
            final Figure figure;
            try {
                figure = field.getFigure(new Point(y, x));
            } catch (final InvalidPointException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            System.out.print(figure != null ? figure : " ");
            System.out.print(" ");
        }
        System.out.println();
    }

    private void printSeparator(final Game game) {
        int size = game.getField().getSize();

        for(int i = 0; i < size; i++){
            System.out.print("~~~");
        }
        System.out.println("~~~");
    }

}
