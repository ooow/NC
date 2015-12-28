package Gamer;

/**
 * Created by goga on 23.12.15.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI extends JFrame {
    private JButton[][] b = new JButton[3][3];
    private Active click = new Active();
    private Client c;
    private int isDraw = 0;

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
        c = new Client(7777, b);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                c.close();
                e.getWindow().dispose();
            }
        });
    }

    public static void main(String[] args) {
        new GUI();
    }

    class Active implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < b.length; i++) {
                for (int j = 0; j < b[i].length; j++) {
                    if (e.getSource() == b[i][j]) {
                        visualization(i, j);
                        break;
                    }
                }
            }
        }

        public void visualization(int i, int j) {
            if (!c.getStatus()) {
                b[i][j].setText("X");
                if (c.isWin("X")) {
                    c.win("X");
                    c.newGame(new Point(-1, -1));
                    isDraw = 0;
                    return;
                }
                b[i][j].setEnabled(false);
                isDraw++;
                if (isDraw == 5) {
                    c.newGame(new Point(-1, -1));
                    c.setStatus(false);
                    for (JButton[] aB : b) {
                        for (int l = 0; l < b[i].length; l++) {
                            aB[l].setEnabled(true);
                            aB[l].setText("");
                        }
                    }
                    return;
                }
                Point t = c.makeStep(new Point(i, j));
                b[t.x][t.y].setText("O");
                b[t.x][t.y].setEnabled(false);
                if (c.isWin("O")) {
                    c.win("O");
                    c.newGame(new Point(-1, -1));
                    isDraw = 0;
                    return;
                }
            } else {
                c.setStatus(false);
                for (JButton[] aB : b) {
                    for (int l = 0; l < b[i].length; l++) {
                        aB[l].setEnabled(true);
                        aB[l].setText("");
                    }
                }
            }
        }
    }
}
