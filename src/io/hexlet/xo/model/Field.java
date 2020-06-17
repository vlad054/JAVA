package io.hexlet.xo.model;


import io.hexlet.xo.model.exceptions.AlreadyOccupiedException;
import io.hexlet.xo.model.exceptions.InvalidFieldSize;
import io.hexlet.xo.model.exceptions.InvalidPointException;

import java.awt.*;

public class Field {

    private static final int MIN_COORDINATE = 0;

    private final Figure[][] field;

    private int filedSize = 3;

    public Field() {
        field = new Figure[filedSize][filedSize];
    }

    public Field(int size) {
        this.filedSize = size;
        field = new Figure[filedSize][filedSize];
    }

    public void setFiledSize(int size) throws InvalidFieldSize{
        if ((size < 1) || (size > 10))
            filedSize = size;
        else throw new InvalidFieldSize();
    }

    public int getSize() {
        return filedSize;
    }

    public Figure getFigure(final Point point) throws InvalidPointException {
        if (!checkPoint(point)) {
            throw new InvalidPointException();
        }
        return field[point.x][point.y];
    }

    public void setFigure(final Point point, final Figure figure) throws InvalidPointException , AlreadyOccupiedException {
        if (!checkPoint(point)) {
            throw new InvalidPointException();
        }
        if (field[point.x][point.y] != null){
            throw  new AlreadyOccupiedException();
        }
        field[point.x][point.y] = figure;
    }

    private boolean checkPoint(final Point point) {
        return checkCoordinate(point.x, field.length) && checkCoordinate(point.y, field[point.x].length);
    }

    private boolean checkCoordinate(final int coordinate, final int maxCoordinate) {
        return coordinate >= MIN_COORDINATE && coordinate < maxCoordinate;
    }

}
