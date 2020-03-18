package br.ufrn.imd.gerenciador_grupos.classes;

import br.ufrn.imd.gerenciador_grupos.interfaces.User;

import java.rmi.RemoteException;
import java.util.*;

public class Group {
    private String name;
    private Map<String, User> users;
    private List<String> messages;
    private Integer firsNotSentMsg;

    public Group(String name){
        this.name = name;
        this.users = new HashMap<>();
        this.messages = new LinkedList<>();
        this.firsNotSentMsg = 0;
    }

    public void addUser(User user) throws RemoteException {
        users.put(user.getName(), user);
    }

    public boolean isInGroup(String name){
        return users.containsKey(name);
    }

    public void addMessage(String message){
        messages.add(message);
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public List<String> getMessages() {
        return messages;
    }

    public String getName() {
        return name;
    }

    public Integer getFirsNotSentMsg() {
        return firsNotSentMsg;
    }

    public void setFirsNotSentMsg(Integer firsNotSentMsg) {
        this.firsNotSentMsg = firsNotSentMsg;
    }
}
