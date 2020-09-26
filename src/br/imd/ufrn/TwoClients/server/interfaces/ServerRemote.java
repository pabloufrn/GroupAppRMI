package br.imd.ufrn.TwoClients.server.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import br.imd.ufrn.TwoClients.client.controllers.GroupsListener;
import br.imd.ufrn.TwoClients.client.interfaces.ClientRemote;
import br.imd.ufrn.TwoClients.client.interfaces.GroupsListenerRemote;

public interface ServerRemote extends Remote {

    void conectListener(GroupsListenerRemote listener) throws RemoteException;
    void disconectListener(GroupsListenerRemote listener) throws RemoteException;
    ClientGroupRemote enterGroup(Integer id, ClientRemote client) throws RemoteException;
    ClientGroupRemote createGroup(String name, ClientRemote client) throws RemoteException;


    // stub.connect(client) | cliente
    //  stub.listarGrupos()                 | client
    // stub.entrarGrupo(client, idGrupo)    | client
    // clientGroup.enviarMensagem           ()
    // group.enviarMensagem()               | client
    //
}