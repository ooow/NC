import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.WildcardType;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;

/**
 * Created by goga on 13.12.15.
 */
public class Server {
    private ServerSocket server;
    private Socket connect;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int[][] map = new int[3][3];
    private boolean firstStep = true;

    public Server(int port) {
        try {
            server = new ServerSocket(port, 10);
            System.out.println("Server запущен");
            connect = server.accept();
            for (int[] aMap : map) {
                Arrays.fill(aMap, -1);
            }
            System.out.println("Соединение установленно");
            out = new ObjectOutputStream(connect.getOutputStream());
            in = new ObjectInputStream(connect.getInputStream());
            while (connect.isConnected()) {
                Point p = (Point) in.readObject();
                if (p.x == -1)
                    // начало новой игры
                    for (int[] aMap : map) {
                        Arrays.fill(aMap, -1);
                        firstStep = true;
                    }
                else {
                    // ответ клиенту
                    out.writeObject(makeMove(p));
                }
            }
        } catch (IOException e) {
            System.out.println("Не установленно соединение");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server s = new Server(7777);
    }

    // Метод закрыающий сервер

    public void close() {
        try {
            out.close();
            in.close();
            connect.close();
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ниже указана "Стратегия" сервера (коментировать ее не вижу смысла, она не подобие AI)
    private Point makeMove(Point p) {
        map[p.x][p.y] = 1;
        int i = 0;
        int j = 0;
        if (firstStep) {
            firstStep = false;
            if (isAngle(p)) {
                i = Math.abs(p.x - 2);
                j = Math.abs(p.y - 2);
            }
            if (isEdge(p)) {
                i = 2;
                j = 2;
            }
            if (isCentre(p)) {
                i = 0;
                j = 2;
            }
        } else {
            for (int k = 0; k < map.length; k++) {
                for (int l = 0; l < map.length; l++) {
                    if (map[k][l] == 0) {
                        Point pr = newPlace(k, l);
                        i = pr.x;
                        j = pr.y;
                    }
                }
            }
        }
        map[i][j] = 0;
        return new Point(i, j);
    }

    private boolean isAngle(Point r) {
        return (r.x == 0 && r.y == 0 || r.x == 0 && r.y == 2 || r.x == 2 && r.y == 0 || r.x == 2 && r.y == 2);
    }

    private boolean isEdge(Point r) {
        return (r.x == 0 && r.y == 1 || r.x == 1 && r.y == 0 || r.x == 1 && r.y == 2 || r.x == 2 && r.y == 1);
    }

    private boolean isCentre(Point r) {
        return (r.x == 1 && r.y == 1);
    }

    private Point newPlace(int a, int b) {
        Point pr = new Point(a, b);
        if (isAngle(pr)) {
            pr.x = Math.abs(pr.x - 1);
            pr.y = Math.abs(pr.y - 1);
            if (map[pr.x][pr.y] == -1)
                return pr;
            else
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map.length; j++) {
                        if (map[i][j] == -1) {
                            pr.x = i;
                            pr.y = j;
                            return pr;
                        }
                    }
                }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == -1) {
                    pr.x = i;
                    pr.y = j;
                    return pr;
                }
            }
        }
        return pr;
    }
}
