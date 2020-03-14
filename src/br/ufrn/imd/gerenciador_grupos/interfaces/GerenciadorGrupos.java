package br.ufrn.imd.gerenciador_grupos.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GerenciadorGrupos extends Remote {
    String cadastrarGrupo() throws RemoteException;
}
