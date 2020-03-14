package br.ufrn.imd.gerenciador_grupos.classes;

import br.ufrn.imd.gerenciador_grupos.interfaces.GerenciadorGrupos;

import java.rmi.RemoteException;

public class GerenciadorGruposImpl implements GerenciadorGrupos {

    public GerenciadorGruposImpl() {}

    @Override
    public String cadastrarGrupo() throws RemoteException {
        return "Ok";
    }
}
