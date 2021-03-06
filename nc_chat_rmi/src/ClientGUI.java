import clients.Client;

import javax.swing.*;
import java.awt.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by goga on 16.12.15.
 */
public class ClientGUI extends JFrame implements Runnable {
    private Client user;
    private int x, y;
    private JButton b;
    private JTextArea t1;
    private JTextField t2;

    public ClientGUI(Client name, int x, int y) throws RemoteException, NotBoundException {
        super(name.toString());
        this.x = x;
        this.y = y;
        this.user = name;
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
        b = new JButton();
        Font normal = new Font("Georgia", Font.ITALIC, 25);
        b.setFont(normal);
        b.setText("Send Message");
        add(t1);
        add(t2);
        add(b);
        user.addSlot(t1);
        try {
            user.connect();
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        b.addActionListener(e -> {
            if (e.getSource() == b) {
                try {
                    user.send(t2.getText());
                    t2.setText("");
                } catch (RemoteException e1) {
                    System.out.println("Что то не так");
                }
            }
        });
    }
}
