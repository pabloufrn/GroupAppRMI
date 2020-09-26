package br.imd.ufrn.TwoClients.client.interfaces;

import br.imd.ufrn.TwoClients.server.interfaces.GroupRemote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface GroupsListenerRemote extends Remote {

    void notifyGroupsChange(List<GroupRemote> groups) throws RemoteException;
}
