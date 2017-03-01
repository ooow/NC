package Commands;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by goga on 16.12.15.
 */

// Команда (объект) для получения сообщений
public class Receive implements Runnable {
    private int port;       //порт для получения сообщений
    private JTextArea text; //поле для вывода полученного сообщения

    public Receive(JTextArea t, int port) {
        this.text = t;
        this.port = port;
    }

    @Override
    public synchronized void run() {
        try {
            DatagramSocket ds = new DatagramSocket(this.port);
            DatagramPacket pack = new DatagramPacket(new byte[1024], 1024);
            while (true) {
                ds.receive(pack);
                byte[] b = pack.getData();
                String mess = new String(b, "UTF-8");
                text.setText(text.getText() + "\n" + mess);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
