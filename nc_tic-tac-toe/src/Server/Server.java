package Server;

import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by goga on 23.12.15.
 */
public class Server {
    private ServerSocket server;
    private Socket connect;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Server(int port) {
        try {
            server = new ServerSocket(port, 10);
            System.out.println("Server запущен");
            connect = server.accept();
            Logic l = new Logic();
            System.out.println("Соединение установленно");
            out = new ObjectOutputStream(connect.getOutputStream());
            in = new ObjectInputStream(connect.getInputStream());
            while (connect.isConnected()) {
                Point p = (Point) in.readObject();
                if (p.x == -1)
                    // начало новой игры
                    l = new Logic();
                else {
                    // ответ клиенту
                    out.writeObject(l.makeMove(p));
                }
            }
        } catch (IOException e) {
            System.out.println("Не установленно соединение");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connect.close();
            } catch (IOException e) {
                System.out.println("Ошибка при разрыве связи");
            }
            try {
                server.close();
            } catch (IOException e) {
                System.out.println("Ошибка при закрытии сервера");
            }
        }
    }

    public static void main(String[] args) {
        new Server(7777);
    }
}

