package br.imd.ufrn.TwoClients.server.controllers;

import br.imd.ufrn.TwoClients.client.interfaces.ClientRemote;
import br.imd.ufrn.TwoClients.server.interfaces.ClientGroupRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientGroup implements ClientGroupRemote {
    ClientRemote client;
    Group group;

    public ClientGroup(ClientRemote client, Group group) throws RemoteException{
        this.client = client;
        this.group = group;
        UnicastRemoteObject.exportObject(this, 1010);
    }

    @Override
    public void sendMessage(String msg) throws RemoteException {
        group.sendMessage(client.getName() + ": " + msg);
    }

    @Override
    public void leaveGroup() throws RemoteException {
        this.group.removeClient(client);
    }
}
