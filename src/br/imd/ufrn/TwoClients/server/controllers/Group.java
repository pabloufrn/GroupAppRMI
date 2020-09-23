package br.imd.ufrn.TwoClients.server.controllers;

import br.imd.ufrn.TwoClients.client.interfaces.ClientRemote;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Group {
    // pega a lista de cliente e manda uma mensagem para cada um

    private String name;
    private final List<ClientRemote> clients;

    public Group() {
        this.clients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    void addClient(ClientRemote client) {
        //Fulano entrou no grupo
        try {
            sendMessage(client.getName() + " entrou no grupo!");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        clients.add(client);
    }

    void sendMessage(String message) {
        clients.forEach(cliente -> {
            try {
                cliente.receiveMessage(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

}
