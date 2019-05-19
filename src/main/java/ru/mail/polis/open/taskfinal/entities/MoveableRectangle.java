package ru.mail.polis.open.taskfinal.entities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class MoveableRectangle extends Rectangle {
    private Point defaultPosition;
    private Color color;

    public MoveableRectangle(Point mainPoint, int width, int height, Color color) {
        super(mainPoint, new Dimension(width, height));
        this.defaultPosition = mainPoint;
        this.color = color;
    }

    public void resetLocation() {
        this.setLocation(defaultPosition);
    }

    public Color getColor() {
        return color;
    }
}
