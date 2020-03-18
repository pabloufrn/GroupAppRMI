package br.ufrn.imd.gerenciador_grupos.classes;

import br.ufrn.imd.gerenciador_grupos.interfaces.GroupManager;
import br.ufrn.imd.gerenciador_grupos.interfaces.User;

import java.rmi.RemoteException;
import java.util.*;

public class GroupManagerImpl implements GroupManager {

    /*
    * Modificar pessoas para uma lista ordenada
    * */

    private Map<String, Group> groups;
    private Map<String, User> users;

    public GroupManagerImpl() {
        groups = new HashMap<>();
        users = new HashMap<>();
    }

    @Override
    public void register(User user) throws RemoteException {
        users.put(user.getName(), user);
    }

    @Override
    public boolean cadastrarGrupo(String nomeGrupo, String nomeCliente) throws RemoteException {
        if(nomeGrupo.equals("") || groups.containsKey(nomeGrupo)) {
            return false;
        }

        Group group = new Group(nomeGrupo);
        groups.put(nomeGrupo, group);
        System.out.println("Grupo " + nomeGrupo + " criado com sucesso.");
        this.entrarGrupo(nomeGrupo, nomeCliente);
        group.addMessage("Grupo " + nomeGrupo + " criado com sucesso.");

        return true;
    }

    @Override
    public boolean entrarGrupo(String nomeGrupo, String nomeCliente) throws RemoteException {
        Group group = groups.get(nomeGrupo);
        User user = users.get(nomeCliente);

        if(group == null || user == null || group.isInGroup(nomeCliente)) {
            return false;
        }

        group.addUser(users.get(nomeCliente));
        group.addMessage(nomeCliente + " entrou no grupo");
        System.out.println(nomeCliente + " entrou no grupo");

        return true;
    }

    @Override
    public boolean sairGrupo(String nomeGrupo) throws RemoteException {
        return false;
    }

    public void update() throws RemoteException {
        for(Group group: groups.values()){
            int size = group.getMessages().size();
            Integer firsNotSentMsg = group.getFirsNotSentMsg();
            while(firsNotSentMsg < size){
                for(User user: group.getUsers().values()){
                    System.out.println("Sending to:" + user.getName());
                    user.sendMessage(group.getMessages().get(firsNotSentMsg));
                }
                firsNotSentMsg ++;
            }
            group.setFirsNotSentMsg(firsNotSentMsg);
        }
    }
}
