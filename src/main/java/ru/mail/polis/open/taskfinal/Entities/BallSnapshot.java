package ru.mail.polis.open.taskfinal.Entities;

import java.awt.Color;

public class BallSnapshot {
    private int x, y, diameter;
    private Color color;

    public BallSnapshot(int x, int y, int diameter, Color color) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }

    public Color getColor() {
        return color;
    }

    public boolean equals(BallSnapshot ballSnapshot) {
        if (ballSnapshot == null) {
            return false;
        }

        if (ballSnapshot == this) {
            return true;
        } else {
            if (this.x == ballSnapshot.getX() && this.y == ballSnapshot.getY()
                    && this.diameter == ballSnapshot.getDiameter()) {
                return true;
            }
        }

        return false;
    }
}
