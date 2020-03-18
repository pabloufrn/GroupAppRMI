package br.ufrn.imd.gerenciador_grupos.app;

import br.ufrn.imd.gerenciador_grupos.classes.GroupManagerImpl;
import br.ufrn.imd.gerenciador_grupos.interfaces.GroupManager;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject {

    protected Server() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        System.setProperty("java.rmi.server.hostname","192.168.11.6");
	    try {
            GroupManagerImpl gerenciadorGrupos = new GroupManagerImpl();
            GroupManager skeleton = (GroupManager) exportObject(gerenciadorGrupos, 0);
            LocateRegistry.createRegistry(1099);
            Naming.rebind("//192.168.11.6/GerenciadorGrupos", skeleton);
            System.err.println("Server ready");
	    } catch (Exception e){
	        System.err.println("Server exception: " + e.toString());
	        e.printStackTrace();
        }
    }
}
