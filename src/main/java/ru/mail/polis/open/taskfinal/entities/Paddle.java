package ru.mail.polis.open.taskfinal.entities;

import java.awt.Color;
import java.awt.Point;

public class Paddle extends MoveableRectangle {
    private int score;

    public Paddle(Point mainPoint, int width, int height) {
        super(mainPoint, width, height, Color.WHITE);
    }

    public Paddle(Point mainPoint, int width, int height, Color color) {
        super(mainPoint, width, height, color);
    }

    public void incrementScore() {
        this.score++;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public PaddleSnapshot getSnapshot() {
        return new PaddleSnapshot(x, y, width, height, score, getColor());
    }
}
