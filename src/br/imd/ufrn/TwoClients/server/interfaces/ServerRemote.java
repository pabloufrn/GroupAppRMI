package br.imd.ufrn.TwoClients.server.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import br.imd.ufrn.TwoClients.client.interfaces.ClientRemote;

public interface ServerRemote extends Remote {

    ClientGroupRemote registerClient(ClientRemote client, String name) throws RemoteException;
    List<Map.Entry<String, String>> listGroups() throws RemoteException;


    // stub.connect(client) | cliente
    //  stub.listarGrupos()                 | client
    // stub.entrarGrupo(client, idGrupo)    | client
    // clientGroup.enviarMensagem           ()
    // group.enviarMensagem()               | client
    //
}