package servers;

import chat.Chats;
import clients.Client;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by goga on 15.12.15.
 */
public class GreenLine implements Chats {
    public Client friend;
    public String str;


    @Override
    public void sendMessage(String mes) throws RemoteException {
        System.out.println(mes);
        str = mes;
    }

    @Override
    public void setFriend(Client friend) {
        this.friend = friend;
    }

    @Override
    public void send() {
        friend.receive(str);
        str = null;
    }

    @Override
    public boolean haveMes() {
        return this.str != null;
    }


}
