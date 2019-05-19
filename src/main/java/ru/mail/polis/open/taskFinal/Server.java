package ru.mail.polis.open.taskFinal;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private SceneInfo sceneInfo;
    private UsersActionHolder userActionHolder;
    private DatagramSocket serverSocket;

    Server(SceneInfo sceneInfo, UsersActionHolder userActionHolder) throws IOException {
        this.sceneInfo = sceneInfo;
        this.userActionHolder = userActionHolder;
        serverSocket = new DatagramSocket(6666);
    }

    public void recieveSlaveActions() throws IOException {
        byte[] receiveData = new byte[256];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);

        ByteBuffer wrapped = ByteBuffer.wrap(receivePacket.getData());
        int actionsNumber = wrapped.getInt();

        if (actionsNumber > 0) {
            List<Integer> actions = new ArrayList<Integer>(actionsNumber);
            for (int i = 0; i < actionsNumber; i++) {
                actions.add(wrapped.getInt());
            }
            userActionHolder.addSlaveActions(actions);
        }

        InetAddress IPAddress = receivePacket.getAddress();
        int port = receivePacket.getPort();

        ByteBuffer buffer = ByteBuffer.allocate(24);

        PaddleSnapshot mps = sceneInfo.getMasterPaddleSnapshot();
        PaddleSnapshot sps = sceneInfo.getSlavePaddleSnapshot();
        BallSnapshot bs = sceneInfo.getBallSnapshot();

        buffer.putInt(mps.getX());
        buffer.putInt(mps.getY());

        buffer.putInt(sps.getX());
        buffer.putInt(sps.getY());

        buffer.putInt(bs.getX());
        buffer.putInt(bs.getY());

        DatagramPacket sendPacket =
                new DatagramPacket(buffer.array(), buffer.limit(), IPAddress, port);
        serverSocket.send(sendPacket);
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

}
