package br.imd.ufrn.TwoClients.server.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GroupRemote extends Remote {
    Integer getId() throws RemoteException;
    String getName() throws RemoteException;
}
