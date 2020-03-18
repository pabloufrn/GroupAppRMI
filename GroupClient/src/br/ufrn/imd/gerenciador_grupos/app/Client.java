package br.ufrn.imd.gerenciador_grupos.app;

import br.ufrn.imd.gerenciador_grupos.classes.UserImpl;
import br.ufrn.imd.gerenciador_grupos.interfaces.GroupManager;
import br.ufrn.imd.gerenciador_grupos.interfaces.User;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject {

    protected Client() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        try {
            GroupManager stub = (GroupManager) Naming.lookup("//localhost/GerenciadorGrupos");
            User user = new UserImpl("Fulano");
            //User user = new User
            stub.register(user);
            boolean response = stub.cadastrarGrupo("Teste","");
            System.out.println("response: " + (response ? "yes":"no"));
            response = stub.cadastrarGrupo("Teste","");
            System.out.println("response: " + (response ? "yes":"no"));
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
