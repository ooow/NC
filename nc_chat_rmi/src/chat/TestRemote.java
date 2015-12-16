package chat;

import clients.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by goga on 15.12.15.
 */
public interface TestRemote extends Remote{
    public String sendMessage(String mes) throws RemoteException;
}
