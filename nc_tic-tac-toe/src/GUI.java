import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;

/**
 * Created by goga on 13.12.15.
 */
public class GUI extends JFrame {
    private JButton[][] b = new JButton[3][3];
    private Server server = new Server();
    private Thread t1 = new Thread(server);
    private Active click = new Active();
    private boolean newGame = true;

    public GUI() {
        super("X  and  O");
        setLayout(new GridLayout(3, 3, 0, 0));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        Font BigFont = new Font("Georgia", Font.BOLD, 55);
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                b[i][j] = new JButton();
                b[i][j].setFont(BigFont);
                add(b[i][j]);
                b[i][j].addActionListener(click);
            }
        }
        t1.start();
    }

    class Active implements ActionListener {
        private char[] mas = new char[9];

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < b.length; i++) {
                for (int j = 0; j < b[i].length; j++) {
                    // Когда игрок нажимает на кнопку, на ней пояляется Х и ее координаты отпраялются на сервер
                    if (e.getSource() == b[i][j]) {
                        if (newGame) {
                            b[i][j].setText("X");
                            if (isWin("X")) {
                                Win("X");
                                break;
                            }
                            b[i][j].setEnabled(false);
                            Gamer g = new Gamer(i, j);
                            g.run();
                            Point t = g.getStep();
                            b[t.x][t.y].setText("O");
                            b[t.x][t.y].setEnabled(false);
                            if (isWin("O")) {
                                Win("O");
                                break;
                            }
                        } else {
                            for (int k = 0; k < b.length; k++) {
                                for (int l = 0; l < b.length; l++) {
                                    b[k][l].setText("");
                                }
                            }
                            server.close();
                            server = new Server();
                            Thread t1 = new Thread(server);
                            t1.start();
                            newGame = true;
                        }
                    }
                }
            }
        }

        //  Вывод победителя

        public void Win(String XO) {
            newGame = false;
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
    }
}
