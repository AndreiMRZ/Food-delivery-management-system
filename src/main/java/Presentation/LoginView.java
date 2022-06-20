package Presentation;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame{
    private JPanel panel1;
    private JTextField usernameTextField;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JButton newAccountButton;
    private JFrame frame;

public LoginView(){
    frame = new JFrame("Login");
    frame.setContentPane(panel1);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
    frame.setSize(320, 300);
}
     public String getUsernameTextField(){
    return usernameTextField.getText();
     }
     public char[] getPasswordField(){
    return passwordField1.getPassword();
     }
     public void addActionListenerLoginButton(ActionListener e){
    loginButton.addActionListener(e);
     }
     public void addActionListenerNewAccountButton(ActionListener e){
    newAccountButton.addActionListener(e);

     }
    void showError(String errMessage) {

    JOptionPane.showMessageDialog(this, errMessage);
    }

    public JFrame getFrame() {
        return frame;
    }
}
