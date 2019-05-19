package ru.mail.polis.open.taskfinal.entities;

import java.awt.Color;
import java.awt.Point;

public class Ball extends MoveableRectangle {
    private int defSpeedX;
    private int defSpeedY;
    private int speedX;
    private int speedY;

    public Ball(Point mainPoint, int diameter, int speedX, int speedY) {
        this(mainPoint, diameter, speedX, speedY, Color.WHITE);
    }

    public Ball(Point mainPoint, int diameter, int speedX, int speedY, Color color) {
        super(mainPoint, diameter, diameter, color);
        defSpeedX = speedX;
        defSpeedY = speedY;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public void resetSpeed() {
        speedX = defSpeedX;
        speedY = defSpeedY;
    }

    public int getxSpeed() {
        return speedX;
    }

    public void setxSpeed(int xSpeed) {
        this.speedX = xSpeed;
    }

    public int getySpeed() {
        return speedY;
    }

    public void setySpeed(int ySpeed) {
        this.speedY = ySpeed;
    }

    public BallSnapshot getSnapshot() {
        return new BallSnapshot(x, y, width, getColor());
    }
}
