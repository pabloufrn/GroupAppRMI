package br.imd.ufrn.TwoClients.server.controllers;

import br.imd.ufrn.TwoClients.client.interfaces.ClientRemote;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Group {

    private final Integer id;
    private final String name;
    private final Server server;
    private final List<ClientRemote> clients;

    public Group(Integer id, String name, Server server) {
        this.name = name;
        this.id = id;
        this.server = server;
        this.clients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void addClient(ClientRemote client) {
        try {
            sendMessage(client.getName() + " entrou no grupo!");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        clients.add(client);
    }

    public void removeClient(ClientRemote client) {
        this.clients.remove(client);
        if(clients.size() == 0){
            this.server.removeGroup(this);
        }
        else {
            try {
                sendMessage(client.getName() + " saiu do grupo!");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        clients.forEach(cliente -> {
            try {
                cliente.receiveMessage(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }
}
