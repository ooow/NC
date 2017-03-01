package chat;

import clients.Client;

import javax.swing.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by goga on 15.12.15.
 */
public interface Chats extends Remote{
    void sendMessage(String mes) throws RemoteException;
    void setFriend(Client friend);
    void send();
    boolean haveMes();
}
