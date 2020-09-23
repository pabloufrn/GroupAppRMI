package br.imd.ufrn.TwoClients.client.controllers;

import br.imd.ufrn.TwoClients.client.interfaces.ClientRemote;
import br.imd.ufrn.TwoClients.client.views.ChatWindow;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client implements ClientRemote {

    private ChatWindow window;
    private String name;

    public Client(ChatWindow window, String name) {
        this.window = window;
        this.name = name;
        this.exportService();
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

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        window.writeChatMsg(message);
    }
}
