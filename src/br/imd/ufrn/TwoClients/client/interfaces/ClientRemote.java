package br.imd.ufrn.TwoClients.client.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientRemote extends Remote {
    String getName() throws RemoteException;
    void receiveMessage(String message) throws RemoteException;
}
