package fr.mrc.ptichat.main.ui;

import fr.mrc.ptichat.main.utils.LanguagesController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConnexionUI extends JFrame {

    private LanguagesController languagesController = new LanguagesController("English");

    public ConnexionUI() {
        this.setGlobalParameters();
        this.createUI();
        this.pack();
    }

    public void open(){
        this.setVisible(true);
    }

    /**
     * Formats the connexion window.
     */
    private void setGlobalParameters(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 100));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    /**
     * Creates the global architecture of the window.
     * Divides the window into three rows, containing respectively the title, the form and the validation button.
     */
    private void createUI() {
        this.setLayout(new BorderLayout(10, 10));
        JPanel titlePanel = new JPanel();
        JPanel formPanel = new JPanel();
        JPanel validationPanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        validationPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.initTitlePanel(titlePanel);
        this.initConnexionForm(formPanel);
        this.initValidationPanel(validationPanel);
        this.getContentPane().add(titlePanel, BorderLayout.NORTH);
        this.getContentPane().add(formPanel, BorderLayout.CENTER);
        this.getContentPane().add(validationPanel, BorderLayout.SOUTH);
    }

    /**
     * Initializes the title panel.
     * @param container the <code>Container</code> to fulfill
     */
    private void initTitlePanel(Container container) {
        JLabel title = new JLabel();
        title.setText(this.languagesController.getWord("WELCOME"));
        container.add(title);
    }

    /**
     * Initializes the form panel.
     * @param container the <code>Container</code> to fulfill
     */
    private void initConnexionForm(Container container) {
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

    /**
     * Initializes the validation Panel.
     * @param container the <code>Container</code> to fulfill
     */
    private void initValidationPanel(Container container) {
        JButton startButton = new JButton();
        startButton.setText(this.languagesController.getWord("START"));
        container.add(startButton);
    }

    /**
     * Creates a panel containing an input with fixed size.
     * @return the created <code>JPanel</code>
     */
    private JPanel createInput(){
        JTextField input = new JTextField();
        input.setPreferredSize(new Dimension(150, 20));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(input);
        return inputPanel;
    };

    /**
     * Creates a label with a given text.
     * @param key the <code>String</code> representing the text
     * @return the created <code>JLabel</code>
     */
    private JLabel createLabel(String key) {
        JLabel label = new JLabel();
        label.setText(this.languagesController.getWord(key));
        return label;
    }
}
