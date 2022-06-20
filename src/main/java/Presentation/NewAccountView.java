package Presentation;

import javax.swing.*;
import java.awt.event.ActionListener;

public class NewAccountView extends JFrame {
    private JPanel panel1;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;
    private JButton createButton;
    private JButton backButton;
    private JFrame frame;

    public NewAccountView(){
        frame = new JFrame("New Account");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(false);
        frame.setSize(320, 300);
    }

    public String getTextFieldUsername(){
        return textFieldUsername.getText();
    }
    public char[] getPasswordField(){
        return passwordField.getPassword();
    }
    public void addActionListenerCreateButton(ActionListener e){
        createButton.addActionListener(e);
    }
    public void addActionListenerBackButton(ActionListener e){
        backButton.addActionListener(e);
    }
    void showError(String errMessage) {

        JOptionPane.showMessageDialog(this, errMessage);
    }

    public JFrame getFrame() {
        return frame;
    }
}
