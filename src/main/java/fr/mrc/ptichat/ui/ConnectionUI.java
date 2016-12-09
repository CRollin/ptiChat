package main.java.fr.mrc.ptichat.ui;

import main.java.fr.mrc.ptichat.appmanagement.ConnectionManager;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.*;
import java.util.Set;


public class ConnectionUI extends GenericUI {

    private Hashtable<String, JTextField> formFields;
    private JTextArea errorTextArea;
    private ConnectionManager cm;
    private DirectoryChooserUI chooser;

    public ConnectionUI(ConnectionManager cm) {
        super("connectionWindow");
        this.cm = cm;
        this.chooser.cm = cm;
    }

    /**
     * Creates the global architecture of the window.
     * Divides the window into three rows, containing respectively the title, the form and the validation button.
     */
    @Override()
    protected void createUI() {
        this.formFields = new Hashtable<>();
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
        String[] inputList = this.uiStyleController.getArrayValue("connectionWindow.grid.elements");
        GridLayout gl = new GridLayout(this.uiStyleController.getIntValue("connectionWindow.grid.height"),
                this.uiStyleController.getIntValue("connectionWindow.grid.width"),
                this.uiStyleController.getIntValue("connectionWindow.grid.hgap"),
                this.uiStyleController.getIntValue("connectionWindow.grid.vgap"));
        container.setLayout(gl);
        for(String s:inputList) {
            JLabel label = this.createLabel(s);
            JPanel panel = this.createInput(s);
            container.add(label);
            container.add(panel);
        }
    }

    /**
     * Initializes the validation Panel.
     * @param container the <code>Container</code> to fulfill
     */
    private void initValidationPanel(Container container) {
        FlowLayout flowLayout = new FlowLayout();
        container.setLayout(flowLayout);
        flowLayout.setAlignment(FlowLayout.RIGHT);
        JButton startButton = new JButton();
        JButton dirPathButton = new JButton();
        startButton.setText(this.languagesController.getWord("START"));
        dirPathButton.setText(this.languagesController.getWord("SET_DIR_PATH"));
        startButton.addActionListener(e -> this.start(e));
        this.chooser = new DirectoryChooserUI(dirPathButton);
        this.errorTextArea = new JTextArea();
        this.errorTextArea.setBackground(container.getBackground());
        this.errorTextArea.setEditable(false);
        this.errorTextArea.setForeground(Color.red);
        container.add(this.errorTextArea);
        container.add(dirPathButton);
        container.add(startButton);
    }

    /**
     * Creates a panel containing an input with fixed size.
     * @return the created <code>JPanel</code>
     */
    private JPanel createInput(String id){
        JTextField input = new JTextField();
        Dimension inputDim =  new Dimension(this.uiStyleController.getIntValue("input.width"),
                this.uiStyleController.getIntValue("input.height"));
        input.setPreferredSize(inputDim);
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(input);
        this.formFields.put(id, input);
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

    private void start(ActionEvent e) {
        Hashtable<String, String> data = new Hashtable<>();
        Set<Entry<String, JTextField>> setFormFields = this.formFields.entrySet();
        Iterator<Entry<String, JTextField>> it = setFormFields.iterator();
        while(it.hasNext()){
            Entry<String, JTextField> entry = it.next();
            data.put(entry.getKey(), entry.getValue().getText());
        }
        this.cm.initConnection(data);
    }

    public void addErrorMessage(String m){
        this.errorTextArea.setText(m);
    }
}
