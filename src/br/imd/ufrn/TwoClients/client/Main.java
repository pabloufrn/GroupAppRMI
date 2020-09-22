package br.imd.ufrn.TwoClients.client;

import br.imd.ufrn.TwoClients.client.controllers.Client;
import br.imd.ufrn.TwoClients.client.views.ChatWindow;
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


        try{
            Registry registry = LocateRegistry.getRegistry(1010);
            ServerRemote stub = (ServerRemote) registry.lookup("MyServer");
            ChatWindow chatWindow = new ChatWindow("Multichat");
            chatWindow.setVisible(true);
            Client client = new Client(chatWindow);
            stub.registerClient(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
