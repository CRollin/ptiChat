package main.java.fr.mrc.ptichat.ui;


import main.java.fr.mrc.ptichat.appmanagement.ChatManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChatUI extends GenericUI {

    private JTextField chatInput;
    private JTextArea chatArea;
    private String peerIp;
    private boolean isPeerConnected;
    private ChatManager chatManager;

    public ChatUI(ChatManager chatManager) {
       super("chatWindow");
        this.chatManager = chatManager;
    }

    public void open(String peerIp){
        this.peerIp = peerIp;
        this.isPeerConnected = this.peerIp != null;
        super.open();
    }


    @Override
    protected void createUI() {
        int borderWidth = this.uiStyleController.getIntValue("border.width");
        this.setLayout(new BorderLayout(borderWidth, borderWidth));
        JPanel titlePanel = new JPanel();
        JPanel chatPanel = new JPanel();
        JPanel messagingPanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth));
        chatPanel.setBorder(new EmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth));
        messagingPanel.setBorder(new EmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth));
        this.initChatPanel(chatPanel);
        this.initMessagingPanel(messagingPanel);
        this.getContentPane().add(chatPanel, BorderLayout.CENTER);
        this.getContentPane().add(messagingPanel, BorderLayout.SOUTH);
    }

    private void initChatPanel(Container container){
        FlowLayout flowLayout = new FlowLayout();
        container.setLayout(flowLayout);
        flowLayout.setAlignment(FlowLayout.CENTER);
        Dimension chatDim = this.createDimension("chatWindow.chatArea.width", "chatWindow.centerBar.height");
        this.chatArea = new JTextArea();

        String chatTitle = isPeerConnected ? this.languagesController.getWord("CHAT_TITLE") + " " + this.peerIp
                :  this.languagesController.getWord("CHAT_WANTING_TITLE");
        Dimension fileDim = this.createDimension("chatWindow.fileArea.width", "chatWindow.centerBar.height");
        JTextArea fileArea = new JTextArea();
        this.initPane(chatDim, this.chatArea, chatTitle, container);
        this.initPane(fileDim, fileArea, this.languagesController.getWord("FILE_TITLE"), container);
    }

    private void initPane(Dimension d, JTextArea ta, String s, Container c){
        ta.setEditable(false);
        ta.setBackground(Color.white);
        ta.setPreferredSize(d);
        ta.setLineWrap(true);
        ta.append(s + "\n");
        DefaultCaret dc = (DefaultCaret)ta.getCaret();
        dc.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        JScrollPane p = new JScrollPane();
        p.setViewportView(ta);
        c.add(p);
    }

    private void initMessagingPanel(Container container){
        FlowLayout flowLayout = new FlowLayout();
        container.setLayout(flowLayout);
        flowLayout.setAlignment(FlowLayout.CENTER);
        //Create input for sending messages
        this.chatInput = new JTextField();
        Dimension chatInputDim = this.createDimension("chatWindow.inputChat.width", "chatWindow.bottomBar.height");
        this.chatInput.setPreferredSize(chatInputDim);
        //Create buttons
        JButton sendMessageButton = new JButton(this.languagesController.getWord("SEND"));
        JButton sendFileButton = new JButton(this.languagesController.getWord("SEND_FILE"));
        Dimension buttonDim =   this.createDimension("chatWindow.button.width", "chatWindow.bottomBar.height");
        sendMessageButton.setPreferredSize(buttonDim);
        sendFileButton.setPreferredSize(buttonDim);
        //Build component
        container.add(chatInput);
        container.add(sendMessageButton);
        container.add(sendFileButton);
        sendMessageButton.addActionListener(e -> this.sendMessage(e));
    }

    private void sendMessage(ActionEvent e){
        String intro = "\n>>>>>>" + " Vous à " +"HH:hh:ss" + "\n";
        String s = intro + this.chatInput.getText() + "\n";
        this.chatInput.setText("");
        this.chatInput.grabFocus();
        this.chatArea.append(s);
        this.chatManager.setMessage(s);
    }

    public void addMessage(String m){
        this.chatArea.append(m);
    }
}
