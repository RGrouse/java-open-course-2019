package ru.mail.polis.open.taskFinal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

public class UsersActionHolder {
    private LinkedBlockingDeque<Integer> master_actions;
    private LinkedBlockingDeque<Integer> slave_actions;

    public UsersActionHolder() {
        master_actions = new LinkedBlockingDeque<>();
        slave_actions = new LinkedBlockingDeque<>();
    }

    public List<Integer> getMasterActions() {
        List<Integer> listOfActions = new ArrayList<Integer>();
        if (!master_actions.isEmpty()) {
            int size = master_actions.size();
            for (int i = 0; i < size; i++) {
                listOfActions.add(master_actions.pollFirst());
            }
        }
        return listOfActions;
    }

    public List<Integer> getSlaveActions() {
        List<Integer> listOfActions = new ArrayList<Integer>();
        if (!slave_actions.isEmpty()) {
            int size = slave_actions.size();
            for (int i = 0; i < size; i++) {
                listOfActions.add(slave_actions.pollFirst());
            }
        }
        return listOfActions;
    }

    public void addMasterActions(List<Integer> actions) {
        master_actions.addAll(actions);
    }

    public void addSlaveActions(List<Integer> actions) {
        slave_actions.addAll(actions);
    }

    public boolean isThereAnyUsersActions() {
        return !master_actions.isEmpty() || !slave_actions.isEmpty();
    }
}
