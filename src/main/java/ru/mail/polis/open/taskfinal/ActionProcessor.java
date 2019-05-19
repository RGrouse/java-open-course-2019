package ru.mail.polis.open.taskfinal;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.List;
import ru.mail.polis.open.taskfinal.entities.Ball;
import ru.mail.polis.open.taskfinal.entities.Paddle;

public class ActionProcessor {
    public void updatePaddle(Paddle paddle, List<Integer> userActions) {
        int paddleSlaveX = paddle.x;;

        for (Integer e : userActions) {
            if (e == KeyEvent.VK_RIGHT) {
                paddleSlaveX += 15;

                if (paddleSlaveX + paddle.getWidth() > SceneInfo.WINDOW_WIDTH) {
                    paddleSlaveX = SceneInfo.WINDOW_WIDTH - paddle.width;
                }
            } else if (e == KeyEvent.VK_LEFT) {
                paddleSlaveX -= 15;

                if (paddleSlaveX < 0) {
                    paddleSlaveX = 0;
                }
            }
        }

        paddle.setLocation(paddleSlaveX, paddle.y);
    }

    public void updateBall(SceneInfo sceneInfo, Ball ball, Paddle masterPaddle,
            Paddle slavePaddle) {

        int ballX = ball.x + ball.getxSpeed();
        int ballY = ball.y + ball.getySpeed();

        ball.setLocation(new Point(ballX, ballY));

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
