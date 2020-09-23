package br.imd.ufrn.TwoClients.client.views;

import br.imd.ufrn.TwoClients.server.interfaces.ClientGroupRemote;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;

public class ChatWindow extends JFrame {
    private JPanel chatPanel;
    private JTextField msgInput;
    private JButton sendButton;
    private JTextPane chatPane;

    private ClientGroupRemote clientGroup;

    public ChatWindow(String title) {
        super(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(chatPanel);
        this.pack();

        // ----------------------------------------
        // ------------ SETUP ---------------------
        // ----------------------------------------

        writeChatMsg("Olá, seja bem vindo ao chat global.\n");

        // ----------------------------------------
        // ------------ PROPS ---------------------
        // ----------------------------------------
        sendButton.setEnabled(false);

        // ----------------------------------------
        // ----------- EVENTOS --------------------
        // ----------------------------------------
        msgInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
               if(msgInput.getText().trim().length() == 1) {
                    sendButton.setEnabled(true);
               }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(msgInput.getText().trim().isBlank()){
                    sendButton.setEnabled(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        sendButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                String text = msgInput.getText().trim();
                if(text.isBlank()) {
                    sendButton.setEnabled(false
                    );
                } else if(text.length() == 1) {
                    sendButton.setEnabled(true);
                }
            }
        });
        sendButton.addActionListener(e -> {
            // botão clicado
            try {
                clientGroup.sendMessage(msgInput.getText());
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
        });
    }

    public void setClientGroup(ClientGroupRemote clientGroup) {
        this.clientGroup = clientGroup;
    }

    public void writeChatMsg(String msg) {
        SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
        StyledDocument styledDocument = chatPane.getStyledDocument();
        try {
            styledDocument.insertString(styledDocument.getLength(), msg + "\n", simpleAttributeSet);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

    }
}
