package br.imd.ufrn.TwoClients.client.controllers;

import br.imd.ufrn.TwoClients.client.interfaces.ClientRemote;
import br.imd.ufrn.TwoClients.client.views.ChatWindow;

import javax.swing.*;
import java.rmi.server.UnicastRemoteObject;

public class Client implements ClientRemote {

    private ChatWindow window;

    public Client(ChatWindow window) {
        this.window = window;
        this.exportService();
    }
    @Override
    public void doSomething() {
        window.writeChatMsg("Alguém entrou no servidor");
    }

    private void exportService() {
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
}
