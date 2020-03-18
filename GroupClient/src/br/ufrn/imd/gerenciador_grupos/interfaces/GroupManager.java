package br.ufrn.imd.gerenciador_grupos.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GroupManager extends Remote {
    void register(User user) throws RemoteException;
    boolean cadastrarGrupo(String nomeGrupo, String nomeCliente) throws RemoteException;
    boolean entrarGrupo(String nomeGrupo, String nomeCliente) throws RemoteException;
    boolean sairGrupo(String nomeGrupo) throws RemoteException;
    boolean mandarMensagem(String nomeGrupo, String mensagem) throws RemoteException;
}
