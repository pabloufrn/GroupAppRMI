package br.imd.ufrn.TwoClients.server.controllers;

import br.imd.ufrn.TwoClients.client.interfaces.ClientRemote;
import br.imd.ufrn.TwoClients.server.interfaces.ServerRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Server implements ServerRemote {

//    ClientRemote client;
    List<ClientRemote> clients;
    List<Group> groups;

    public Server() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 1010);
        this.clients = new ArrayList<>();
    }

    @Override
    public void registerClient(ClientRemote client) throws RemoteException {
        this.clients.add(client);
        this.clients.forEach(clientRemote -> {
            try {
                clientRemote.doSomething();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public List<Map.Entry<String, String>> listGroups() throws RemoteException {
        return groups.stream().map(group ->
                Map.entry("fwef", "fwefw")).collect(Collectors.toList());
    }


}
