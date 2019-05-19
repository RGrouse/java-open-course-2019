package ru.mail.polis.open.taskfinal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.Point;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mail.polis.open.taskfinal.entities.Paddle;
import ru.mail.polis.open.taskfinal.entities.PaddleSnapshot;

class PaddleTest {
    static final int WIDTH = 10;
    static final int HEIGHT = 13;
    static Point defaultLocation;

    static Paddle paddle;

    @BeforeAll
    static void init() {
        defaultLocation = new Point(0, 0);
        paddle = new Paddle(defaultLocation, WIDTH, HEIGHT);
    }

    @Test
    void testSnapshot() {
        PaddleSnapshot ballSnapshot = paddle.getSnapshot();
        assertEquals(ballSnapshot.getX(), paddle.x);
        assertEquals(ballSnapshot.getY(), paddle.y);
        assertEquals(ballSnapshot.getWidth(), WIDTH);
        assertEquals(ballSnapshot.getHeight(), HEIGHT);
    }

    @Test
    void testIncrementScore() {
        PaddleSnapshot paddleSnapshot = paddle.getSnapshot();
        paddle.incrementScore();
        assertEquals(paddleSnapshot.getScore() + 1, paddle.getSnapshot().getScore());
        paddle.incrementScore();
        assertEquals(paddleSnapshot.getScore() + 2, paddle.getSnapshot().getScore());
    }
}
