package br.ufrn.imd.gerenciador_grupos.app;

import br.ufrn.imd.gerenciador_grupos.interfaces.GerenciadorGrupos;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public Client() {}

    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            GerenciadorGrupos stub = (GerenciadorGrupos) registry.lookup("GerenciadorGrupos");
            boolean response = stub.cadastrarGrupo("Teste");
            System.out.println("response: " + (response ? "yes":"no"));
            response = stub.cadastrarGrupo("Teste");
            System.out.println("response: " + (response ? "yes":"no"));
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
