package fr.mrc.ptichat.main.ui;

import fr.mrc.ptichat.main.utils.LanguagesController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConnexionUI extends JFrame {

    private LanguagesController languagesController = new LanguagesController("English");

    public ConnexionUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 100));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.createUI();
        this.pack();
    }

    public void open(){
        this.setVisible(true);
    }

    private void createUI() {
        this.setLayout(new BorderLayout(10, 10));
        JPanel titlePanel = new JPanel();
        JPanel formPanel = new JPanel();
        JPanel validationPanel = new JPanel();

        titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        validationPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.createTitlePanel(titlePanel);
        this.createConnexionForm(formPanel);
        this.createValidationPanel(validationPanel);
        this.getContentPane().add(titlePanel, BorderLayout.NORTH);
        this.getContentPane().add(formPanel, BorderLayout.CENTER);
        this.getContentPane().add(validationPanel, BorderLayout.SOUTH);
    }

    private void createTitlePanel(Container container) {
        JLabel title = new JLabel();
        title.setText(this.languagesController.getWord("WELCOME"));
        container.add(title);
    }

    private void createConnexionForm(Container container) {
        JLabel userIPLabel = this.createLabel("USER_IP");
        JLabel userPortLabel = this.createLabel("USER_PORT");
        JLabel peerIPLabel = this.createLabel("PEER_IP");
        JLabel peerPortLabel = this.createLabel("PEER_PORT");
        JPanel userIPPanel = this.createInput();
        JPanel userPortPanel = this.createInput();
        JPanel peerIPPanel = this.createInput();
        JPanel peerPortPanel = this.createInput();
        GridLayout gl = new GridLayout(2, 4, 50, 10);
        container.setLayout(gl);
        container.add(userIPLabel);
        container.add(userIPPanel);
        container.add(userPortLabel);
        container.add(userPortPanel);
        container.add(peerIPLabel);
        container.add(peerIPPanel);
        container.add(peerPortLabel);
        container.add(peerPortPanel);
    }

    private JPanel createInput(){
        JTextField input = new JTextField();
        input.setPreferredSize(new Dimension(150, 20));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(input);
        return inputPanel;
    };

    private JLabel createLabel(String key) {
        JLabel label = new JLabel();
        label.setText(this.languagesController.getWord(key));
        return label;
    }

    private void createValidationPanel(Container container) {
        JButton startButton = new JButton();
        startButton.setText(this.languagesController.getWord("START"));
        container.add(startButton);
    }
}
