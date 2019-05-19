package ru.mail.polis.open.taskfinal.Entities;

import java.awt.Color;
import java.awt.Point;

public class Ball extends MoveableRectangle {
    private int defXSpeed, defYSpeed;
    private int xSpeed, ySpeed;

    public Ball(Point mainPoint, int diameter, int xSpeed, int ySpeed) {
        this(mainPoint, diameter, xSpeed, ySpeed, Color.WHITE);
    }

    public Ball(Point mainPoint, int diameter, int xSpeed, int ySpeed, Color color) {
        super(mainPoint, diameter, diameter, color);
        defXSpeed = xSpeed;
        defYSpeed = ySpeed;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void resetSpeed() {
        xSpeed = defXSpeed;
        ySpeed = defYSpeed;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public BallSnapshot getSnapshot() {
        return new BallSnapshot(x, y, width, getColor());
    }
}
