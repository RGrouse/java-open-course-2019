package ru.mail.polis.open.taskFinal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class SceneInfo {
    static int WINDOW_WIDTH;
    static int WINDOW_HEIGHT;

    private ActionProcessor actionProcessor;
    private Paddle masterPaddle, slavePaddle;
    private Ball ball;

    public SceneInfo(ActionProcessor actionProcessor, Paddle masterPaddle, Paddle slavePaddle,
            Ball ball) {
        this.actionProcessor = actionProcessor;
        this.masterPaddle = masterPaddle;
        this.slavePaddle = slavePaddle;
        this.ball = ball;
    }

    // todo sync
    public void updateSceneInfo(UsersActionHolder usersActionHolder) {
        if (usersActionHolder.isThereAnyUsersActions()) {
            actionProcessor.updatePaddle(masterPaddle, usersActionHolder.getMasterActions());
            actionProcessor.updatePaddle(slavePaddle, usersActionHolder.getSlaveActions());
        }
        actionProcessor.updateBall(this, ball, masterPaddle, slavePaddle);
    }

    public void changeMasterPaddleLocation(Point newLocation) {
        masterPaddle.setLocation(newLocation);
    }

    public void changeSlavePaddleLocation(Point newLocation) {
        slavePaddle.setLocation(newLocation);
    }

    public void changeBallLocation(Point newLocation) {
        ball.setLocation(newLocation);
    }

    public PaddleSnapshot getMasterPaddleSnapshot() {
        return masterPaddle.getSnapshot();
    }

    public PaddleSnapshot getSlavePaddleSnapshot() {
        return slavePaddle.getSnapshot();
    }

    public BallSnapshot getBallSnapshot() {
        return ball.getSnapshot();
    }
}


class BallSnapshot {
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


class PaddleSnapshot {
    private int x, y, width, height, score;
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


class Ball extends MoveableRectangle {
    private int defXSpeed, defYSpeed;
    private int xSpeed, ySpeed;

    Ball(Point mainPoint, int diameter, int xSpeed, int ySpeed) {
        this(mainPoint, diameter, xSpeed, ySpeed, Color.WHITE);
    }

    Ball(Point mainPoint, int diameter, int xSpeed, int ySpeed, Color color) {
        super(mainPoint, diameter, diameter, color);
        defXSpeed = xSpeed;
        defYSpeed = ySpeed;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    void resetSpeed() {
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

    BallSnapshot getSnapshot() {
        return new BallSnapshot(x, y, width, getColor());
    }
}


class Paddle extends MoveableRectangle {
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

    PaddleSnapshot getSnapshot() {
        return new PaddleSnapshot(x, y, width, height, score, getColor());
    }
}


class MoveableRectangle extends Rectangle {
    private Point defaultPosition;
    private Color color;

    MoveableRectangle(Point mainPoint, int width, int height, Color color) {
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
