package br.imd.ufrn.TwoClients.client.controllers;

import br.imd.ufrn.TwoClients.client.interfaces.ClientRemote;

import java.rmi.server.UnicastRemoteObject;

public class Client implements ClientRemote {
    public Client() {
        int port = 1011;
        boolean stop = false;
        while (!stop) {
            try {
                UnicastRemoteObject.exportObject(this, port);
                stop = true;
            } catch (Exception e) {
                if (port == 1020) {
                    stop = true;
                    e.printStackTrace();
                }
            }
            port +=1;
        }
    }
    @Override
    public void doSomething() {
        System.out.println("Alguém entrou no servidor");
    }
}
