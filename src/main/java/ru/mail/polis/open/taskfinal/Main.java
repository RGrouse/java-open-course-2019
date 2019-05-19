package ru.mail.polis.open.taskfinal;

import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import ru.mail.polis.open.taskfinal.HeartBeat.Role;
import ru.mail.polis.open.taskfinal.entities.Ball;
import ru.mail.polis.open.taskfinal.entities.Paddle;

class Main {
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 25;
    private static final int BALL_DIAMETER = 15;
    private static final int BALL_X_SPEED = 7;
    private static final int BALL_Y_SPEED = -5;
    private static final int WINDOW_WIDTH = 700;
    private static final int WINDOW_HEIGHT = 800;
    private static final int PADDLE_VERTICAL_INDENT = 15;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(Role.Master);
            }
        });
    }

    private static void createAndShowGUI(Role role) {
        JFrame mainFrame = new JFrame("Pong " + role);

        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SceneInfo sceneInfo = getSceneInfo(new ActionProcessor());

        MainPanel mainScenePanel = new MainPanel(sceneInfo);

        HostActionHolder hosstActionHolder = new HostActionHolder();

        mainScenePanel.addKeyListener(hosstActionHolder);
        mainScenePanel.setFocusable(true);
        mainScenePanel.grabFocus();

        mainFrame.getContentPane().add(mainScenePanel);
        mainFrame.pack();
        mainFrame.setVisible(true);

        HeartBeat heartBeat = new HeartBeat(mainScenePanel, sceneInfo, hosstActionHolder);

        try {
            heartBeat.start(role);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                heartBeat.stop();
                we.getWindow().dispose();
            }
        });
    }

    static SceneInfo getSceneInfo(ActionProcessor actionProcessor) {
        SceneInfo.WINDOW_WIDTH = WINDOW_WIDTH;
        SceneInfo.WINDOW_HEIGHT = WINDOW_HEIGHT;

        int windowHorizontalCentre = SceneInfo.WINDOW_WIDTH / 2;
        int windowVerticalCentre = SceneInfo.WINDOW_HEIGHT / 2;

        int paddleMasterX = windowHorizontalCentre - PADDLE_WIDTH / 2;
        int paddleMasterY = PADDLE_VERTICAL_INDENT;

        int paddleSlaveX = windowHorizontalCentre - PADDLE_WIDTH / 2;
        int paddleSlaveY = SceneInfo.WINDOW_HEIGHT - PADDLE_HEIGHT - PADDLE_VERTICAL_INDENT;

        int ballX = windowHorizontalCentre - BALL_DIAMETER / 2;
        int ballY = windowVerticalCentre - BALL_DIAMETER / 2;

        Paddle masterPaddle =
                new Paddle(new Point(paddleMasterX, paddleMasterY), PADDLE_WIDTH, PADDLE_HEIGHT);
        Paddle slavePaddle =
                new Paddle(new Point(paddleSlaveX, paddleSlaveY), PADDLE_WIDTH, PADDLE_HEIGHT);
        Ball ball = new Ball(new Point(ballX, ballY), BALL_DIAMETER, BALL_X_SPEED, BALL_Y_SPEED);

        return new SceneInfo(actionProcessor, masterPaddle, slavePaddle, ball);
    }
}

