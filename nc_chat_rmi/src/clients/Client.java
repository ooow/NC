package clients;

import chat.Const;
import chat.TestRemote;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by goga on 15.12.15.
 */
public class Client {
    private String name;
    private Registry registry;
    private TestRemote remote;

    public Client(String name) throws RemoteException, NotBoundException {
        this.name = name;
        registry = LocateRegistry.getRegistry("localhost", Const.RMI_PORT);
        remote = (TestRemote) registry.lookup(Const.RMI_ID);
    }

    public void send(String message) throws RemoteException {
        remote.sendMessage(message);
    }
}
