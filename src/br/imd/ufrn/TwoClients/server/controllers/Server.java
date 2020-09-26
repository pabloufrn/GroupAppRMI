package br.imd.ufrn.TwoClients.server.controllers;

import br.imd.ufrn.TwoClients.client.interfaces.ClientRemote;
import br.imd.ufrn.TwoClients.client.interfaces.GroupsListenerRemote;
import br.imd.ufrn.TwoClients.server.interfaces.ClientGroupRemote;
import br.imd.ufrn.TwoClients.server.interfaces.GroupRemote;
import br.imd.ufrn.TwoClients.server.interfaces.ServerRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Server implements ServerRemote {

    List<GroupsListenerRemote> groupsListenerList;
    List<Group> groups;
    Integer nextId;

    public Server() throws RemoteException {
        this.nextId = 0;
        this.groups = new ArrayList<>();
        this.groupsListenerList = new ArrayList<>();
        UnicastRemoteObject.exportObject(this, 1010);
    }

    @Override
    public void conectListener(GroupsListenerRemote listener) throws RemoteException {
        this.groupsListenerList.add(listener);
    }

    @Override
    public void disconectListener(GroupsListenerRemote listener) throws RemoteException {
        this.groupsListenerList.remove(listener);
    }

    @Override
    public ClientGroupRemote enterGroup(Integer id, ClientRemote client) throws RemoteException{
        Optional<Group> groupOpt = groups.stream().filter(group -> group.getId().equals(id)).findAny();
        if(groupOpt.isEmpty()){
            return null;
        }
        Group group = groupOpt.get();
        group.addClient(client);
        return new ClientGroup(client, group);
    }

    @Override
    public ClientGroupRemote createGroup(String name, ClientRemote client) throws RemoteException{
        Group group = new Group(nextId++, name, this);
        this.groups.add(group);
        this.notifyGroupsChanges();
        return enterGroup(group.getId(), client);
    }

    public void removeGroup(Group group) {
        this.groups.remove(group);
        this.notifyGroupsChanges();
    }

    private void notifyGroupsChanges(){
        this.groupsListenerList.forEach(g -> {
            try {
                g.notifyGroupsChange(listGroups());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    private List<GroupRemote> listGroups() {
        return groups.stream().map(group -> (GroupRemote) group).collect(Collectors.toList());
    }
}
