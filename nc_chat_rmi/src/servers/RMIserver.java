package servers;

import clients.Client;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

/**
 * Created by goga on 16.12.15.
 */
public class RMIserver {
    private Client[] clients;

    public RMIserver(Client... names) throws RemoteException, AlreadyBoundException, NotBoundException {
        Registry registry = LocateRegistry.createRegistry(7777);
        this.clients = names;
        for (int i = 0; i < names.length; i++) {
            registry.bind(names[i].toString(), new GreenLine());
        }
        System.out.println(Arrays.toString(registry.list()));
        System.out.println("chat started");

        for (int i = 0; i < names.length; i++) {
            GreenLine c = (GreenLine) registry.lookup(names[i].toString());
            if (names[i].toString().equals("Mike")) {
                c.setFriend(this.clients[1]);
            } else {
                c.setFriend(this.clients[0]);
            }
            if (c.haveMes()) {
                c.send();
            }
        }
    }
}
