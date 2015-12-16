package Client;

import Commands.Receive;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by goga on 16.12.15.
 */
public class ClientGUI extends JFrame implements Runnable {
    private Client me;
    private int x, y;
    private JButton b;
    private JTextArea t1;
    private JTextField t2;

    public ClientGUI(Client name, int x, int y) {
        super(name.toString());
        this.x = x;
        this.y = y;
        me = name;
    }

    @Override
    public void run() {
        setLayout(new GridLayout(3, 1, 5, 5));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 350);
        setLocation(x, y);
        t1 = new JTextArea();
        t1.setEnabled(false);
        t2 = new JTextField();
        t2.setText(me.toString() + ": ");
        b = new JButton();
        Font normal = new Font("Georgia", Font.ITALIC, 25);
        b.setFont(normal);
        b.setText("Send Message");
        add(t1);
        add(t2);
        add(b);
        new Thread(new Receive(t1, me.getPort())).start();          // запускаю службу для приема сообщений
        b.addActionListener(e -> {
            if (e.getSource() == b) {
                try {
                    me.send(t2.getText(), me.getServerPort());     // после нажания на кнопку, отпарвляем сообщение на сервер
                                                                   // на котором зарегестрирован клиент
                    t2.setText(me.toString() + ": ");
                } catch (IOException e1) {
                }
            }
        });
    }
}
