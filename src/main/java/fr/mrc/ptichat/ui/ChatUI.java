package main.java.fr.mrc.ptichat.ui;

import main.java.fr.mrc.ptichat.appmanagement.ChatManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChatUI extends GenericUI {

    private JTextField chatInput;
    private JTextArea chatArea;
    private JTextArea fileArea;
    private String peerIp;
    private boolean isPeerConnected;
    private ChatManager chatManager;
    private FileChooserUI chooserUI;

    public ChatUI(ChatManager chatManager, String peerIp) {
        super("chatWindow");
        this.chatManager = chatManager;
        this.chooserUI.chatManager = chatManager;
        this.peerIp = peerIp;
        this.isPeerConnected = this.peerIp != null;
        this.updateChatTitle();
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
        Dimension fileDim = this.createDimension("chatWindow.fileArea.width", "chatWindow.centerBar.height");
        this.fileArea = new JTextArea();
        this.initPane(chatDim, this.chatArea, null, container);
        this.initPane(fileDim, this.fileArea, this.languagesController.getWord("FILE_TITLE"), container);
    }

    private void initPane(Dimension d, JTextArea ta, String s, Container c){
        ta.setEditable(false);
        ta.setBackground(Color.white);
        ta.setLineWrap(true);
        if (s != null){
            ta.append(s + "\n");
        }
        DefaultCaret dc = (DefaultCaret)ta.getCaret();
        dc.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        JScrollPane p = new JScrollPane();
        p.setPreferredSize(d);
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
        //Create button
        JButton sendMessageButton = new JButton(this.languagesController.getWord("SEND"));
        JButton sendFileButton = new JButton(this.languagesController.getWord("SEND_FILE"));
        Dimension buttonDim =   this.createDimension("chatWindow.button.width", "chatWindow.bottomBar.height");
        sendMessageButton.setPreferredSize(buttonDim);
        sendFileButton.setPreferredSize(buttonDim);
        //Build component
        container.add(chatInput);
        container.add(sendMessageButton);
        container.add(sendFileButton);
        sendMessageButton.addActionListener(e -> this.sendMessage());
        chatInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    sendMessage();
            }
        });
        //Bind FileChooserUI to sendFileButton
        this.chooserUI = new FileChooserUI(sendFileButton, true);
    }

    private void sendMessage(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String intro = "\n>>>>>> " + this.languagesController.getWord("SEND_INTRO") + " " + sdf.format(cal.getTime()) + "\n";
        String message = this.chatInput.getText();
        this.chatManager.setMessage(message);
        if (this.chatManager.mh.isTerminationMessage(message)) {
            this.chatManager.leaveChat();
        } else {
            String s = intro + message + "\n";
            this.chatInput.setText("");
            this.chatInput.grabFocus();
            this.chatArea.append(s);
        }

    }

    public void addMessage(String m){
        if(this.isPeerConnected == false) {
            this.isPeerConnected = true;
            this.updateChatTitle();
        }
        this.chatArea.append(m);
    }

    public void handlePeerDisconnection(){
        this.isPeerConnected = false;
        this.chatArea.append(this.formatChatInfo(this.languagesController.getWord("PEER_DECO")));
    }

    private void updateChatTitle(){
        String t = this.isPeerConnected ? this.languagesController.getWord("CHAT_TITLE")
                :  this.languagesController.getWord("CHAT_WANTING_TITLE");
        this.chatArea.append(this.formatChatInfo(t));
    }

    private String formatChatInfo(String info) {
        return "\n" + "----------------------------------" + "\n" + info
                + "\n" + "----------------------------------" + "\n";
    }

    /**
     * Appends a new file to the list of received files.
     * @param file the new received file
     */
    public void addFile(String file) {
        this.fileArea.append(file + "\n");
    }
}
