package br.imd.ufrn.TwoClients.client.views;

import br.imd.ufrn.TwoClients.server.interfaces.ClientGroup;
import br.imd.ufrn.TwoClients.server.interfaces.ServerRemote;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChatWindow extends JFrame {
    private JPanel chatPanel;
    private JTextField msgInput;
    private JButton sendButton;
    private JTextPane chatPane;

    private ClientGroup clientGroup;

    public ChatWindow(String title, ServerRemote stub) {
        super(title);
        this.stub = stub;
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
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // botão clicado
                clientGroup.sendMessage(msgInput.getText());
            }
        });
    }

    public void setClientGroup(ClientGroup clientGroup) {
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
