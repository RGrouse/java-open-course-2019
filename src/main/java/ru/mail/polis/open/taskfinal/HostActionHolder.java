package ru.mail.polis.open.taskfinal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

public class HostActionHolder implements KeyListener {
    private LinkedBlockingDeque<Integer> userActions;

    public HostActionHolder() {
        userActions = new LinkedBlockingDeque<>();
    }

    public List<Integer> getUserActions() {
        List<Integer> listOfActions = new ArrayList<Integer>();
        if (!userActions.isEmpty()) {
            int size = userActions.size();
            for (int i = 0; i < size; i++) {
                listOfActions.add(userActions.pollFirst());
            }
        }
        return listOfActions;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        userActions.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        return;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }
}
