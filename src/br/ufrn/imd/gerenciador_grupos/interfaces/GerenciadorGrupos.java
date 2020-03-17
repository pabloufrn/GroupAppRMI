package br.ufrn.imd.gerenciador_grupos.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GerenciadorGrupos extends Remote {
    boolean cadastrarGrupo(String nomeGrupo, String nomeCliente) throws RemoteException;
    boolean entrarGrupo(String nomeGrupo, String nomeCliente) throws RemoteException;
    boolean sairGrupo(String nomeGrupo) throws RemoteException;
    boolean mandarMensagem(String nomeGrupo, String mensagem) throws RemoteException;
    ArrayList<String> receberMensagens(String nomeGrupo) throws RemoteException;
}
