package ru.mail.polis.open.taskFinal;

import java.io.IOException;
import java.util.List;

public class HeartBeat {
    enum Role {
        Master, Slave
    }

    private SceneInfo sceneInfo;
    private HostActionHolder hostActionHolder;
    private UsersActionHolder usersActionHolder;

    boolean goOn;

    public HeartBeat(MainPanel paddle, SceneInfo sceneInfo, HostActionHolder hostActionHolder) {
        this.sceneInfo = sceneInfo;
        this.hostActionHolder = hostActionHolder;

        usersActionHolder = new UsersActionHolder();
    }

    public void start(Role role) throws IOException {
        goOn = true;

        Runnable hr = role == Role.Master ? getServerSetup() : getClientSetup();
        new Thread(hr).start();

        if (role == Role.Master) {
            new Thread(() -> {
                while (goOn) {
                    List<Integer> actions = hostActionHolder.getUserActions();
                    usersActionHolder.addMasterActions(actions);
                    sceneInfo.updateSceneInfo(usersActionHolder);

                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void stop() {
        goOn = false;
    }

    Runnable getClientSetup() throws IOException {
        Runnable r = () -> {
            Client client = null;
            try {
                client = new Client(sceneInfo, hostActionHolder);

                while (goOn) {
                    client.sendActions();
                    Thread.sleep(15);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (client != null) {
                        client.stop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        return r;
    }

    Runnable getServerSetup() {
        Runnable r = () -> {
            Server server = null;
            try {
                server = new Server(sceneInfo, usersActionHolder);
                while (goOn) {
                    server.recieveSlaveActions();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (server != null) {
                        server.stop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        return r;
    }
}
