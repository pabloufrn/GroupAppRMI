package br.ufrn.imd.gerenciador_grupos.app;

import br.ufrn.imd.gerenciador_grupos.classes.GroupManagerImpl;

import java.rmi.RemoteException;

public class MessageSenderThread extends Thread {
    GroupManagerImpl groupManager;

    MessageSenderThread(GroupManagerImpl groupManager){
        super();
        this.groupManager = groupManager;
    }

    @Override
    public void run() {
        for(;;) {
            try {
                groupManager.update();
                Thread.sleep(3000);
            } catch (RemoteException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
