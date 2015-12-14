import java.awt.*;
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
    private boolean Run = true;
    private Point p;

    public Gamer(int i, int j) {
        this.p = new Point(i, j);
    }

    @Override
    public void run() {
        try {
            while (Run) {
                connect = new Socket(InetAddress.getByName("127.0.0.1"), 7777);
                out = new ObjectOutputStream(connect.getOutputStream());
                out.flush();
                out.writeObject(this.p);
                out.flush();
                in = new ObjectInputStream(connect.getInputStream());
                this.p = (Point) in.readObject();
                close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public Point getStep() {
        return this.p;
    }

    private void close() {
        try {
            Run = false;
            out.close();
            in.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
