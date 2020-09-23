package br.imd.ufrn.TwoClients.server.controllers;

import br.imd.ufrn.TwoClients.client.interfaces.ClientRemote;
import br.imd.ufrn.TwoClients.server.interfaces.ClientGroupRemote;
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
        this.groups = new ArrayList<>();
    }

    @Override
    public ClientGroupRemote registerClient(ClientRemote client, String name) throws RemoteException {
        Group group;
        if(groups.isEmpty()){
            group = new Group();
            this.groups.add(group);
        } else {
            group = this.groups.get(0);
        }
        group.addClient(client);
        this.clients.add(client);
        return new ClientGroup(client, group);
    }

    @Override
    public List<Map.Entry<String, String>> listGroups() throws RemoteException {
        return groups.stream().map(group ->
                Map.entry("fwef", "fwefw")).collect(Collectors.toList());
    }


}
