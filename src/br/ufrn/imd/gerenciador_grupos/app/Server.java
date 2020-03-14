package br.ufrn.imd.gerenciador_grupos.app;

import br.ufrn.imd.gerenciador_grupos.classes.GerenciadorGruposImpl;
import br.ufrn.imd.gerenciador_grupos.interfaces.GerenciadorGrupos;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String[] args) {
        System.setProperty("java.rmi.server.hostname","192.168.0.27");
	    try {
            GerenciadorGruposImpl gerenciadorGrupos = new GerenciadorGruposImpl();
            GerenciadorGrupos stub = (GerenciadorGrupos) UnicastRemoteObject.exportObject(gerenciadorGrupos, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("GerenciadorGrupos", stub);
            System.err.println("Server ready");
	    } catch (Exception e){
	        System.err.println("Server exception: " + e.toString());
	        e.printStackTrace();
        }
    }
}
