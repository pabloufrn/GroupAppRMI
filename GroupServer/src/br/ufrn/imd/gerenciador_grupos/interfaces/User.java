package br.ufrn.imd.gerenciador_grupos.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface User extends Remote, Serializable {

    String getName() throws RemoteException;

    void test() throws RemoteException;
}
