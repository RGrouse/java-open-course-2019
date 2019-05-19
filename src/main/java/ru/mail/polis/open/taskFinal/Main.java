package ru.mail.polis.open.taskFinal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import ru.mail.polis.open.taskFinal.HeartBeat.Role;

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

        int window_x_centre = SceneInfo.WINDOW_WIDTH / 2;
        int window_y_centre = SceneInfo.WINDOW_HEIGHT / 2;

        int x_paddle_master = window_x_centre - PADDLE_WIDTH / 2;
        int y_paddle_master = PADDLE_VERTICAL_INDENT;

        int x_paddle_slave = window_x_centre - PADDLE_WIDTH / 2;
        int y_paddle_slave = SceneInfo.WINDOW_HEIGHT - PADDLE_HEIGHT - PADDLE_VERTICAL_INDENT;

        int x_ball = window_x_centre - BALL_DIAMETER / 2;
        int y_ball = window_y_centre - BALL_DIAMETER / 2;

        Paddle master_paddle = new Paddle(new Point(x_paddle_master, y_paddle_master), PADDLE_WIDTH,
                PADDLE_HEIGHT);
        Paddle slave_paddle =
                new Paddle(new Point(x_paddle_slave, y_paddle_slave), PADDLE_WIDTH, PADDLE_HEIGHT);
        Ball ball = new Ball(new Point(x_ball, y_ball), BALL_DIAMETER, BALL_X_SPEED, BALL_Y_SPEED);

        return new SceneInfo(actionProcessor, master_paddle, slave_paddle, ball);
    }
}


class MainPanel extends JPanel {
    private PaddleSnapshot mpCachedState, spCachedState;
    private BallSnapshot ballCachedState;

    private Color filler;
    private Font textFont;

    private SceneInfo sceneInfo;

    public MainPanel(SceneInfo sceneInfo) {
        this.setPreferredSize(new Dimension(SceneInfo.WINDOW_WIDTH, SceneInfo.WINDOW_HEIGHT));
        this.setBackground(Color.BLACK);
        this.sceneInfo = sceneInfo;

        mpCachedState = sceneInfo.getMasterPaddleSnapshot();
        spCachedState = sceneInfo.getSlavePaddleSnapshot();
        ballCachedState = sceneInfo.getBallSnapshot();

        filler = Color.red;
        textFont = new Font("Monaco", Font.PLAIN, 20);

        final Timer timer = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaintIfNeeded();
            }
        });
        timer.start();
    }

    private void repaintIfNeeded() {
        PaddleSnapshot mpCurrentState = sceneInfo.getMasterPaddleSnapshot();
        PaddleSnapshot spCurrentState = sceneInfo.getSlavePaddleSnapshot();
        BallSnapshot ballCurrentState = sceneInfo.getBallSnapshot();

        if (!mpCachedState.equals(mpCurrentState) || !spCachedState.equals(spCurrentState)
                || !ballCachedState.equals(ballCurrentState)) {
            this.mpCachedState = mpCurrentState;
            this.spCachedState = spCurrentState;
            this.ballCachedState = ballCurrentState;

            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(filler);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        paintPaddles(g2);
        paintBall(g2);
        paintScore(g2);
    }

    private void paintPaddles(Graphics2D g2) {
        g2.setColor(mpCachedState.getColor());
        g2.fillRect(mpCachedState.getX(), mpCachedState.getY(), mpCachedState.getWidth(),
                mpCachedState.getHeight());
        g2.setColor(spCachedState.getColor());
        g2.fillRect(spCachedState.getX(), spCachedState.getY(), spCachedState.getWidth(),
                spCachedState.getHeight());
    }

    private void paintBall(Graphics2D g2) {
        g2.setColor(ballCachedState.getColor());
        g2.fillRoundRect(ballCachedState.getX(), ballCachedState.getY(),
                ballCachedState.getDiameter(), ballCachedState.getDiameter(),
                ballCachedState.getDiameter(), ballCachedState.getDiameter());
    }

    private void paintScore(Graphics2D g2) {
        g2.setFont(textFont);
        g2.setColor(mpCachedState.getColor());
        g2.drawString(String.valueOf(mpCachedState.getScore()), 0, textFont.getSize());
        g2.setColor(spCachedState.getColor());
        g2.drawString(String.valueOf(spCachedState.getScore()), 0, SceneInfo.WINDOW_HEIGHT);
    }
}

