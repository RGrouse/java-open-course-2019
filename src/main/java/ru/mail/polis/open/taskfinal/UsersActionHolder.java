package ru.mail.polis.open.taskfinal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

public class UsersActionHolder {
    private LinkedBlockingDeque<Integer> masterActions;
    private LinkedBlockingDeque<Integer> slaveActions;

    public UsersActionHolder() {
        masterActions = new LinkedBlockingDeque<>();
        slaveActions = new LinkedBlockingDeque<>();
    }

    public List<Integer> getMasterActions() {
        List<Integer> listOfActions = new ArrayList<Integer>();
        if (!masterActions.isEmpty()) {
            int size = masterActions.size();
            for (int i = 0; i < size; i++) {
                listOfActions.add(masterActions.pollFirst());
            }
        }
        return listOfActions;
    }

    public List<Integer> getSlaveActions() {
        List<Integer> listOfActions = new ArrayList<Integer>();
        if (!slaveActions.isEmpty()) {
            int size = slaveActions.size();
            for (int i = 0; i < size; i++) {
                listOfActions.add(slaveActions.pollFirst());
            }
        }
        return listOfActions;
    }

    public void addMasterActions(List<Integer> actions) {
        masterActions.addAll(actions);
    }

    public void addSlaveActions(List<Integer> actions) {
        slaveActions.addAll(actions);
    }

    public boolean isThereAnyUsersActions() {
        return !masterActions.isEmpty() || !slaveActions.isEmpty();
    }
}
