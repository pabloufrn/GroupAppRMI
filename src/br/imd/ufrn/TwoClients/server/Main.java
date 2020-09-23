package br.imd.ufrn.TwoClients.server;

import br.imd.ufrn.TwoClients.server.controllers.Server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname","25.97.85.1");
            Registry registry = LocateRegistry.createRegistry(1010);
            registry.bind("MyServer", new Server());
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }

    }
}
