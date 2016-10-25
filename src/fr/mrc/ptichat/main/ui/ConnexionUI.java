package fr.mrc.ptichat.main.ui;

import fr.mrc.ptichat.main.utils.LanguagesController;
import fr.mrc.ptichat.main.utils.UIStyleController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConnexionUI extends JFrame {

    private LanguagesController languagesController = new LanguagesController("French");
    private UIStyleController uiStyleController = new UIStyleController();

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
        Dimension d = new Dimension(this.uiStyleController.getIntValue("connexionWindow.width"),
                this.uiStyleController.getIntValue("connexionWindow.height"));
        this.setMinimumSize(d);
        this.setResizable(this.uiStyleController.getBooleanValue("connexionWindow.resizable"));
        this.setLocationRelativeTo(null);
    }

    /**
     * Creates the global architecture of the window.
     * Divides the window into three rows, containing respectively the title, the form and the validation button.
     */
    private void createUI() {
        int borderWidth = this.uiStyleController.getIntValue("border.width");
        this.setLayout(new BorderLayout(borderWidth, borderWidth));
        JPanel titlePanel = new JPanel();
        JPanel formPanel = new JPanel();
        JPanel validationPanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth));
        formPanel.setBorder(new EmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth));
        validationPanel.setBorder(new EmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth));
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
        String[] inputList = this.uiStyleController.getArrayValue("connexionWindow.grid.elements");
        GridLayout gl = new GridLayout(this.uiStyleController.getIntValue("connexionWindow.grid.height"),
                this.uiStyleController.getIntValue("connexionWindow.grid.width"),
                this.uiStyleController.getIntValue("connexionWindow.grid.hgap"),
                this.uiStyleController.getIntValue("connexionWindow.grid.vgap"));
        container.setLayout(gl);
        for(String s:inputList) {
            JLabel label = this.createLabel(s);
            JPanel panel = this.createInput();
            container.add(label);
            container.add(panel);
        }
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
        Dimension inputDim =  new Dimension(this.uiStyleController.getIntValue("input.width"),
                this.uiStyleController.getIntValue("input.height"));
        input.setPreferredSize(inputDim);
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(input);
        return inputPanel;
    }

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
