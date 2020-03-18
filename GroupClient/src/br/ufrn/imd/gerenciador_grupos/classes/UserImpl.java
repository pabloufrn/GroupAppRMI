package br.ufrn.imd.gerenciador_grupos.classes;

import br.ufrn.imd.gerenciador_grupos.interfaces.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserImpl extends UnicastRemoteObject implements User {
    private String name;

    public UserImpl(String name) throws RemoteException {
        super();
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void sendMessage(String msg) throws RemoteException {
        System.out.println(msg);
    }
}
