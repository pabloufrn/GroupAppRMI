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
        user.test();
    }

    @Override
    public boolean cadastrarGrupo(String nomeGrupo, String nomeCliente) throws RemoteException {
        if(nomeGrupo.equals("") || groups.containsKey(nomeGrupo)) {
            return false;
        }

        groups.put(nomeGrupo, new Group(nomeGrupo));
        this.entrarGrupo(nomeGrupo, nomeCliente);
        this.mandarMensagem(nomeGrupo, "Grupo " + nomeGrupo + " criado com sucesso.");

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

        return false;
    }

    @Override
    public boolean sairGrupo(String nomeGrupo) throws RemoteException {
        return false;
    }

    @Override
    public boolean mandarMensagem(String nomeGrupo, String mensagem) throws RemoteException {
        /** todo
         * Obter nome do cliente pelo endereço
         * Mandar rmi para todos os clientes conectados
         */
        return false;
    }
}
