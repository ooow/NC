import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by goga on 13.12.15.
 */
public class Gamer implements Runnable {
    private Socket connect;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int port;

    public Gamer(int port) {
        this.port = port;
    }


    @Override
    public void run() {
        try {
            connect = new Socket(InetAddress.getByName("localhost"), port);
            out = new ObjectOutputStream(connect.getOutputStream());
            in = new ObjectInputStream(connect.getInputStream());
        } catch (IOException e) {
            System.out.println("Оибка в конструктооре Геймера");
        }
    }

    public Point makeStep(Point p) {
        Point np = new Point(0, 0);
        try {
            out.writeObject(p);
            System.out.println("Отправил ход ");
            np = (Point) in.readObject();
            System.out.println("Принял ответный ход");
        } catch (IOException e) {
            System.out.println("не выполнился ход");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return np;
        }
    }

    private void close() {
        try {
            out.close();
            in.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
