package br.ufrn.imd.gerenciador_grupos.classes;

import br.ufrn.imd.gerenciador_grupos.interfaces.GerenciadorGrupos;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;

public class GerenciadorGruposImpl implements GerenciadorGrupos {

    private HashSet<String> pessoas;
    private HashSet<String> grupos;

    public GerenciadorGruposImpl() {
        grupos = new HashSet<String>();
    }

    @Override
    public boolean cadastrarGrupo(String nome) throws RemoteException {
        if(nome == "" || grupos.contains(nome))
            return false;
        grupos.add(nome);
        return true;
    }

    @Override
    public boolean entrarGrupo(String nomeGrupo, String nomeCliente) {
        return false;
    }

    @Override
    public boolean sairGrupo(String nomeGrupo, String nomeCliente) {
        return false;
    }

    @Override
    public boolean mandarMensagem(String nomeGrupo, String nomeCliente, String mensagem) {
        return false;
    }

    @Override
    public ArrayList<String> receberMensagens(String nomeGrupo, String nomeCliente) {
        return null;
    }
}
