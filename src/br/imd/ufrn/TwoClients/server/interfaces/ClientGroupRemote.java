package br.imd.ufrn.TwoClients.server.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientGroupRemote extends Remote {
    // group

    // TODO: class
    // client (com nome)

    // group.sendMsg("nome:" + "msg")
    void sendMessage(String msg) throws RemoteException;
}
