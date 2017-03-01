package Gamer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by goga on 23.12.15.
 */
public class Client {
    private Socket connect;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int port;
    private JButton[][] b;
    private boolean newGames = false;

    public Client(int port, JButton[][] buttons) {
        this.port = port;
        this.b = buttons;
        try {
            connect = new Socket(InetAddress.getByName("localhost"), this.port);
            System.out.println("Соединение установленно");
            out = new ObjectOutputStream(connect.getOutputStream());
            in = new ObjectInputStream(connect.getInputStream());
        } catch (IOException e) {
            b[0][0].setFont(new Font("Georgia", Font.PLAIN, 12));
            b[0][0].setText("Связь");
            b[0][1].setFont(new Font("Georgia", Font.PLAIN, 12));
            b[0][1].setText("потеряна");
            for (JButton[] aB : b) {
                for (JButton anAB : aB) {
                    anAB.setEnabled(false);
                }
            }
            System.out.println("Не удалось подключиться к серверу");
        }
    }

    public Point makeStep(Point p) {
        Point np = null;
        try {
            out.writeObject(p);
            np = (Point) in.readObject();
        } catch (IOException e) {
            System.out.println("Не выполнился ход");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return np;
        }
    }

    public void newGame(Point p) {
        try {
            out.writeObject(p);
            System.out.println("Отправил ход ");
        } catch (IOException e) {
            System.out.println("не выполнился ход");
        } finally {
            newGames = true;
        }
    }

    public boolean getStatus() {
        return newGames;
    }

    public void setStatus(boolean arg) {
        this.newGames = arg;
    }

    //  Вывод победителя
    public void win(String XO) {
        for (int k = 0; k < b.length; k++) {
            for (int l = 0; l < b[k].length; l++) {
                b[k][l].setEnabled(true);
                b[k][l].setForeground(Color.cyan);
            }
        }
        b[0][0].setText("W");
        b[0][1].setText("I");
        b[0][2].setText("N");
        b[1][0].setText("");
        b[1][1].setText(XO);
        b[1][2].setText("");
        b[2][0].setText("");
        b[2][1].setText("");
        b[2][2].setText("");
    }

    //  Проверка на выйграш
    public boolean isWin(String XO) {
        int k = 0;
        for (int i = 0; i < b.length; i++) {
            if (XO.equals(b[i][0].getText()))
                k++;
            if (XO.equals(b[i][1].getText()))
                k++;
            if (XO.equals(b[i][2].getText()))
                k++;
            if (k == 3)
                return true;
            else
                k = 0;
        }

        for (int i = 0; i < b.length; i++) {
            if (XO.equals(b[0][i].getText()))
                k++;
            if (XO.equals(b[1][i].getText()))
                k++;
            if (XO.equals(b[2][i].getText()))
                k++;
            if (k == 3)
                return true;
            else
                k = 0;
        }

        for (int i = 0; i < b.length; i++) {
            if (XO.equals(b[i][i].getText()))
                k++;
        }
        if (k == 3)
            return true;
        else
            k = 0;

        if (XO.equals(b[0][2].getText()))
            k++;
        if (XO.equals(b[1][1].getText()))
            k++;
        if (XO.equals(b[2][0].getText()))
            k++;
        return k == 3;
    }

    public void close() {
        try {
            if (connect != null) {
                this.connect.close();
                System.out.println("Клиент отключился");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при разрыве связи");
        }
    }
}
