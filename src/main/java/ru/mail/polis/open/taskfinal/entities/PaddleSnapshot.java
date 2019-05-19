package ru.mail.polis.open.taskfinal.entities;

import java.awt.Color;

public class PaddleSnapshot {
    private int x;
    private int y;
    private int width;
    private int height;
    private int score;
    private Color color;

    public PaddleSnapshot(int x, int y, int width, int height, int score, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.score = score;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getScore() {
        return score;
    }

    public Color getColor() {
        return color;
    }

    public boolean equals(PaddleSnapshot paddleSnapshot) {
        if (paddleSnapshot == null) {
            return false;
        }

        if (paddleSnapshot == this) {
            return true;
        } else {
            if (this.x == paddleSnapshot.getX() && this.y == paddleSnapshot.getY()
                    && this.width == paddleSnapshot.getWidth()
                    && this.height == paddleSnapshot.getHeight()) {
                return true;
            }
        }

        return false;
    }
}
