package br.imd.ufrn.TwoClients.server.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import br.imd.ufrn.TwoClients.client.interfaces.ClientRemote;

public interface ServerRemote extends Remote {
    public void registerClient(ClientRemote client) throws RemoteException;
}