package br.imd.ufrn.TwoClients.client.controllers;

import br.imd.ufrn.TwoClients.client.interfaces.GroupsListenerRemote;
import br.imd.ufrn.TwoClients.client.views.MainWindow;
import br.imd.ufrn.TwoClients.server.interfaces.GroupRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class GroupsListener implements GroupsListenerRemote {
    private final MainWindow mainWindow;

    public GroupsListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        this.exportRemoteObject();
    }

    private void exportRemoteObject() {
        int port = 1021;
        boolean stop = false;
        while (!stop) {
            try {
                UnicastRemoteObject.exportObject(this, port);
                stop = true;
            } catch (Exception e) {
                if (port == 1030) {
                    stop = true;
                    e.printStackTrace();
                }
            }
            port +=1;
        }
    }

    @Override
    public void notifyGroupsChange(List<GroupRemote> groups) throws RemoteException {
        this.mainWindow.refreshGroups(groups);
    }
}
