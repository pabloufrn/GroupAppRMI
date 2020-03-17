package br.ufrn.imd.gerenciador_grupos.classes;

import br.ufrn.imd.gerenciador_grupos.interfaces.GerenciadorGrupos;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class GerenciadorGruposImpl implements GerenciadorGrupos {

    /*
    * Modificar pessoas para uma lista ordenada
    * */

    private HashSet<String> grupos;
    private HashMap<String, ArrayList<String>> pessoas;
    private HashMap<String, ArrayList<String>> mensagens;

    public GerenciadorGruposImpl() {
        grupos = new HashSet<String>();
        pessoas = new HashMap<String, ArrayList<String>>();
        mensagens = new HashMap<String, ArrayList<String>>();
    }

    @Override
    public boolean cadastrarGrupo(String nomeGrupo, String nomeCliente) throws RemoteException {
        if(nomeGrupo == "" || grupos.contains(nomeGrupo))
            return false;
        grupos.add(nomeGrupo);
        mensagens.put(nomeGrupo, new ArrayList<String>());
        pessoas.put(nomeCliente, new ArrayList<String>());
        this.mandarMensagem(nomeGrupo, "Grupo " + nomeGrupo + " criado com sucesso.");
        this.entrarGrupo(nomeGrupo, nomeCliente);
        return true;
    }

    @Override
    public boolean entrarGrupo(String nomeGrupo, String nomeCliente) throws RemoteException {
        if(grupos.contains(nomeGrupo) || pessoas.get(nomeGrupo).contains(nomeCliente))
            return  false;
        pessoas.put(nomeGrupo, new ArrayList<String>(Arrays.asList(nomeCliente)));
        mensagens.get(nomeGrupo).add(nomeCliente + " entrou no grupo");
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
        mensagens.get(nomeGrupo).add(mensagem);
        return false;
    }

    @Override
    public ArrayList<String> receberMensagens(String nomeGrupo) throws RemoteException {
        // todo: Verificar se cliente pertence ao grupo
        // O get retorna null, caso a chave não exista
        return mensagens.get(nomeGrupo);
    }
}
