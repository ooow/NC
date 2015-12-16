package servers;
import chat.TestRemote;
import clients.Client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by goga on 15.12.15.
 */
public class RemoteImpl extends UnicastRemoteObject implements TestRemote {

    private static final long serialVersionUID = 1L;

    protected RemoteImpl() throws RemoteException {
        super();
    }

    @Override
    public String sendMessage(String mes) throws RemoteException {
        return null;
    }
}
