package ru.mail.polis.open.taskfinal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.awt.Color;
import java.awt.Point;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mail.polis.open.taskfinal.entities.MoveableRectangle;

class MoveableRectangleTest {
    static final int WIDTH = 10;
    static final int HEIGHT = 13;
    static Point defaultLocation;
    static Color color;
    static MoveableRectangle rect;

    @BeforeAll
    static void init() {
        defaultLocation = new Point(0, 0);
        color = Color.BLACK;
        rect = new MoveableRectangle(defaultLocation, WIDTH, HEIGHT, color);
    }

    @Test
    void testResetLocation() {
        rect.setLocation(new Point(10, 10));
        assertNotEquals(rect.getLocation(), defaultLocation);
        rect.resetLocation();
        assertEquals(rect.getLocation(), defaultLocation);
    }

    @Test
    void testColor() {
        assertEquals(color, rect.getColor());
    }
}
