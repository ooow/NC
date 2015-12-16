package Commands;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by goga on 16.12.15.
 */

// Команда (объект) для отправки сообщения
public class Send {
    protected String host;  //порт для отпраки сообщений

    public Send(String host) {
        this.host = host;
    }

    public synchronized void send(String message, int port) throws IOException {
        byte[] data = message.getBytes();
        InetAddress addr = InetAddress.getByName(host);
        DatagramPacket pack = new DatagramPacket(data, data.length, addr, port);
        DatagramSocket ds = new DatagramSocket();
        ds.send(pack);
        ds.close();
    }
}
