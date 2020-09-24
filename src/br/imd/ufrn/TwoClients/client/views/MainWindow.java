package br.imd.ufrn.TwoClients.client.views;

import br.imd.ufrn.TwoClients.client.controllers.Client;
import br.imd.ufrn.TwoClients.client.interfaces.ClientRemote;
import br.imd.ufrn.TwoClients.server.interfaces.ClientGroupRemote;
import br.imd.ufrn.TwoClients.server.interfaces.GroupRemote;
import br.imd.ufrn.TwoClients.server.interfaces.ServerRemote;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MainWindow extends JFrame implements ListSelectionListener {
    private JList gList;
    private JPanel mainPane;
    private JButton joinButton;
    private JButton novoGrupoButton;

    private List<GroupRemote> groups;
    private Integer selectedGroup;
    Client client;

    ServerRemote  stub;

    public MainWindow(String title, ServerRemote stub) {
        super(title);
        this.stub = stub;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPane);
        this.pack();
        selectedGroup = -1;

        DefaultListModel listModel = new DefaultListModel();
        gList.setModel(listModel);

        gList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gList.setSelectedIndex(0);
        gList.addListSelectionListener(this);
        gList.setVisibleRowCount(5);

        ClientRemote client =  new Client("Pablo");

        try {
            stub.createGroup("dwe", client);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        // -------------------------------------------
        // --------------- CARREGAR ------------------
        // -------------------------------------------
        try {
            groups = stub.listGroups();
            System.out.println(groups);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        listModel.addAll(
                groups.stream().map(group -> {
                    try {
                        return group.getName();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return "";
                    }
                }).collect(Collectors.toList())
        );
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("join - " + e.getActionCommand());
                joinSelectedGroup();
            }
        });
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            Integer newSelectedGroup = null;
            try {
                newSelectedGroup = groups.get(gList.getSelectedIndex()).getId();
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
            if(newSelectedGroup == selectedGroup) {
                joinSelectedGroup();
            } else {
                selectedGroup = newSelectedGroup;
            }
        }
    }
    public void joinSelectedGroup() {
        try {
            stub.enterGroup(selectedGroup, client);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
