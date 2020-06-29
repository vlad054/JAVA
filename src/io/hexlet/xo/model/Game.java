package io.hexlet.xo.model;


public class Game<F>{

    private Player[] players = {new Player("First", Figure.X), new Player("Second",Figure.O)};

    private final Field<F> field;

    private final String name;

    public Game(final Field<F> field,
                final String name) {
        this.field = field;
        this.name = name;
    }

    public void setPlayers(final Player[] players){
        this.players = players;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Field<F> getField() {
        return field;
    }

    public String getName() {
        return name;
    }

}
