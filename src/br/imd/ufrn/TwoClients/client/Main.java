package br.imd.ufrn.TwoClients.client;

import br.imd.ufrn.TwoClients.client.controllers.Client;
import br.imd.ufrn.TwoClients.server.interfaces.ServerRemote;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        try{
            Registry registry = LocateRegistry.getRegistry(1010);
            ServerRemote stub = (ServerRemote) registry.lookup("MyServer");
            stub.registerClient(new Client());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
