package ru.mail.polis.open.taskfinal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import ru.mail.polis.open.taskfinal.entities.BallSnapshot;
import ru.mail.polis.open.taskfinal.entities.PaddleSnapshot;

class MainPanel extends JPanel {
    private PaddleSnapshot mpCachedState;
    private PaddleSnapshot spCachedState;
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
        textFont = new Font("Monaco", Font.PLAIN, 15);

        final Timer timer = new Timer(20, new ActionListener() {
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
