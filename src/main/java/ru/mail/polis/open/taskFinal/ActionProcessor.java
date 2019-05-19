package ru.mail.polis.open.taskFinal;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.List;

public class ActionProcessor {
    public void updatePaddle(Paddle paddle, List<Integer> userActions) {
        int x_paddle_slave = paddle.x;;

        for (Integer e : userActions) {
            if (e == KeyEvent.VK_RIGHT) {
                x_paddle_slave += 15;

                if (x_paddle_slave + paddle.getWidth() > SceneInfo.WINDOW_WIDTH) {
                    x_paddle_slave = SceneInfo.WINDOW_WIDTH - paddle.width;
                }
            } else if (e == KeyEvent.VK_LEFT) {
                x_paddle_slave -= 15;

                if (x_paddle_slave < 0) {
                    x_paddle_slave = 0;
                }
            }
        }

        paddle.setLocation(x_paddle_slave, paddle.y);
    }

    public void updateBall(SceneInfo sceneInfo, Ball ball, Paddle masterPaddle,
            Paddle slavePaddle) {

        int xBall = ball.x + ball.getxSpeed();
        int yBall = ball.y + ball.getySpeed();

        ball.setLocation(new Point(xBall, yBall));

        if (masterPaddle.intersects(ball) || slavePaddle.intersects(ball)) {
            ball.setySpeed(-ball.getySpeed());
        }

        if (ball.x <= 0 || ball.x >= SceneInfo.WINDOW_WIDTH) {
            ball.setxSpeed(-ball.getxSpeed());
        }

        if (ball.y <= 0) {
            slavePaddle.incrementScore();
            ball.resetLocation();
            ball.resetSpeed();
        } else if (ball.y >= SceneInfo.WINDOW_HEIGHT) {
            masterPaddle.incrementScore();
            ball.resetLocation();
            ball.resetSpeed();
        }
    }

}
