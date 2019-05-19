package ru.mail.polis.open.taskfinal;

import java.awt.Point;
import ru.mail.polis.open.taskfinal.entities.Ball;
import ru.mail.polis.open.taskfinal.entities.BallSnapshot;
import ru.mail.polis.open.taskfinal.entities.Paddle;
import ru.mail.polis.open.taskfinal.entities.PaddleSnapshot;

public class SceneInfo {
    static int WINDOW_WIDTH;
    static int WINDOW_HEIGHT;

    private ActionProcessor actionProcessor;
    private Paddle masterPaddle;
    private Paddle slavePaddle;
    private Ball ball;

    public SceneInfo(ActionProcessor actionProcessor, Paddle masterPaddle, Paddle slavePaddle,
            Ball ball) {
        this.actionProcessor = actionProcessor;
        this.masterPaddle = masterPaddle;
        this.slavePaddle = slavePaddle;
        this.ball = ball;
    }

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

    public void changeMasterPaddleScore(int score) {
        masterPaddle.setScore(score);
    }

    public void changeSlavePaddleScore(int score) {
        slavePaddle.setScore(score);
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
