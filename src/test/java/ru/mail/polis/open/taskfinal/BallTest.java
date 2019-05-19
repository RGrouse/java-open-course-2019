package ru.mail.polis.open.taskfinal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.Point;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mail.polis.open.taskfinal.entities.Ball;
import ru.mail.polis.open.taskfinal.entities.BallSnapshot;

class BallTest {
    static final int DIAMETER = 10;
    static Point defaultLocation;

    static Ball ball;

    @BeforeAll
    static void init() {
        defaultLocation = new Point(0, 0);
        ball = new Ball(defaultLocation, DIAMETER, 5, 5);
    }

    @Test
    void testSpeed() {
        int speedX = ball.getxSpeed();
        int speedY = ball.getySpeed();

        ball.setxSpeed(100);
        ball.setySpeed(55);

        assertEquals(100, ball.getxSpeed());
        assertEquals(55, ball.getySpeed());

        ball.resetSpeed();

        assertEquals(speedX, ball.getxSpeed());
        assertEquals(speedY, ball.getySpeed());
    }

    @Test
    void testSnapshot() {
        BallSnapshot ballSnapshot = ball.getSnapshot();
        assertEquals(ballSnapshot.getX(), ball.x);
        assertEquals(ballSnapshot.getY(), ball.y);
        assertEquals(ballSnapshot.getDiameter(), DIAMETER);
    }
}
