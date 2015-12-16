package Server;

import Client.Client;
import Commands.Send;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by goga on 16.12.15.
 */

// Класс Чат
public class Chat extends Send implements Runnable {
    private DatagramSocket ds;
    private Client[] users;

    public Chat(String host, int port) throws SocketException {
        super(host);
        this.ds = new DatagramSocket(port);
    }

    public void setUsers(Client... users) {
        this.users = users;
    }

    @Override
    public void run() {
        try {
            System.out.println("Chat Started");

            while (true) {
                DatagramPacket pack = new DatagramPacket(new byte[1024], 1024);
                ds.receive(pack);
                byte[] b = pack.getData();
                String mess = new String(b, "UTF-8");
                for (int i = 0; i < users.length; i++) {
                    send(mess, users[i].getPort());
                }
            }
        } catch (IOException e) {
        }
    }
}
