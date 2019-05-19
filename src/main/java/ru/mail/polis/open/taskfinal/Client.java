package ru.mail.polis.open.taskfinal;

import java.awt.Point;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.List;

class Client {
    byte[] receiveData;

    DatagramSocket clientSocket;
    InetAddress address;
    SceneInfo sceneInfo;

    private HostActionHolder hostActionHolder;

    Client(SceneInfo sceneInfo, HostActionHolder hostActionHolder, InetAddress address)
            throws SocketException, UnknownHostException {
        this.sceneInfo = sceneInfo;
        this.hostActionHolder = hostActionHolder;
        this.address = address;
        clientSocket = new DatagramSocket();
        receiveData = new byte[1024];
    }

    public void sendActions() throws IOException {
        List<Integer> actions = hostActionHolder.getUserActions();

        ByteBuffer bb = ByteBuffer.allocate((actions.size() + 1) * 4);
        bb.putInt(actions.size());
        for (int i : actions) {
            bb.putInt(i);
        }

        DatagramPacket sendPacket = new DatagramPacket(bb.array(), bb.limit(), address, 6666);
        clientSocket.send(sendPacket);

        ByteBuffer bbResponce = ByteBuffer.allocate(24);
        IntBuffer wrappedBb = bbResponce.asIntBuffer();

        DatagramPacket receivePacket = new DatagramPacket(bbResponce.array(), bbResponce.limit());
        clientSocket.receive(receivePacket);

        Point masterPaddlePoint = new Point(wrappedBb.get(), wrappedBb.get());
        Point slavePaddlePoint = new Point(wrappedBb.get(), wrappedBb.get());
        Point ballPoint = new Point(wrappedBb.get(), wrappedBb.get());

        sceneInfo.changeMasterPaddleLocation(masterPaddlePoint);
        sceneInfo.changeSlavePaddleLocation(slavePaddlePoint);
        sceneInfo.changeBallLocation(ballPoint);
    }

    public void stop() throws IOException {
        clientSocket.close();
    }
}


