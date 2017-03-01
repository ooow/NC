package clients;

import chat.Chats;

import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by goga on 15.12.15.
 */
public class Client {
    private String name;
    private JTextArea texts;
    private Registry registry;
    private Chats remote;

    public Client(String name){
        this.name = name;
    }
    public void connect() throws RemoteException, NotBoundException {
        registry = LocateRegistry.getRegistry("localhost", 7777);
        remote = (Chats) registry.lookup(this.name);
    }

    public void send(String message) throws RemoteException {
        remote.sendMessage(message);
    }

    public void receive(String mes) {
        texts.setText(mes);
    }

    public void addSlot(JTextArea texts) {
        this.texts = texts;
    }
    @Override
    public String toString(){
        return this.name;
    }
}
