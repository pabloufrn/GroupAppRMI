package br.ufrn.imd.gerenciador_grupos.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GerenciadorGrupos extends Remote {
    boolean cadastrarGrupo(String nome) throws RemoteException;
    boolean entrarGrupo(String nomeGrupo, String nomeCliente);
    boolean sairGrupo(String nomeGrupo, String nomeCliente);
    boolean mandarMensagem(String nomeGrupo, String nomeCliente, String mensagem);
    ArrayList<String> receberMensagens(String nomeGrupo, String nomeCliente);
}
