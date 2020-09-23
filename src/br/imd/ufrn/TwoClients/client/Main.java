package br.imd.ufrn.TwoClients.client;

import br.imd.ufrn.TwoClients.client.controllers.Client;
import br.imd.ufrn.TwoClients.client.views.ChatWindow;
import br.imd.ufrn.TwoClients.server.interfaces.ClientGroupRemote;
import br.imd.ufrn.TwoClients.server.interfaces.ServerRemote;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/* TODO:
*  ação do botão de enviar texto
* */

public class Main {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        System.setProperty("java.rmi.server.hostname","25.6.51.213");

        try{
            Registry registry = LocateRegistry.getRegistry("25.97.85.1", 1010);
            ServerRemote stub = (ServerRemote) registry.lookup("MyServer");
            ChatWindow chatWindow = new ChatWindow("Multichat");
            chatWindow.setVisible(true);
            Client client = new Client(chatWindow);
            ClientGroupRemote clientGroup = stub.registerClient(client, "Pablo");
            chatWindow.setClientGroup(clientGroup);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
