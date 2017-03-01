import clients.Client;
import servers.GreenLine;
import servers.RMIserver;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by goga on 16.12.15.
 */
public class Main {
    public static void main(String[] args) throws AlreadyBoundException, RemoteException, NotBoundException {
        Client Mike = new Client("Mike");
        Client Rob = new Client("Rob");
        System.out.println(Mike);
        System.out.println(Rob);
        new RMIserver(Mike, Rob);                             // Создаю сервер (чат для 2-х пользователей)
        new Thread(new ClientGUI(Mike, 100, 150)).start();    // В качестве ID использую имя
        new Thread(new ClientGUI(Rob, 850, 150)).start();
    }
}
