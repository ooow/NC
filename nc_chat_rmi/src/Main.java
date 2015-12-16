import servers.RMIserver;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by goga on 16.12.15.
 */
public class Main {
    public static void main(String[] args) throws AlreadyBoundException, RemoteException, NotBoundException {
        new RMIserver();
        new Thread(new ClientGUI("Mike", 100, 150)).start();
        new Thread(new ClientGUI("Rob", 850, 150)).start();
    }
}
