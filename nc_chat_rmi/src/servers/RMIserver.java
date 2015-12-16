package servers;

import chat.Const;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by goga on 16.12.15.
 */
public class RMIserver {

    public RMIserver() throws RemoteException, AlreadyBoundException {
        RemoteImpl imp1 = new RemoteImpl();
        Registry registry = LocateRegistry.createRegistry(Const.RMI_PORT);
        registry.bind(Const.RMI_ID, imp1);
        System.out.println("server started");
    }
}
