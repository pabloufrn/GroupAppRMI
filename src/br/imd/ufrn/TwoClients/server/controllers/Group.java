package br.imd.ufrn.TwoClients.server.controllers;

import br.imd.ufrn.TwoClients.client.interfaces.ClientRemote;

import java.util.List;

public class Group {
    // pega a lista de cliente e manda uma mensagem para cada um

    private String name;
    private List<ClientRemote> clients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    void addClient(ClientRemote client) {
        clients.add(client);
    }

    void sendMessage(String message) {
        // TODO:
    }

}
