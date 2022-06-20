package Presentation;

import BusinessLogic.DeliveryService;
import Model.Administrator;
import Model.Client;
import Model.Employee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {

private DeliveryService deliveryService;
private LoginView loginView;
private NewAccountView newAccountView;
private ClientView clientView;
private EmployeeView employeeView;
private AdministratorView administratorView;

public LoginController(){};

public LoginController(DeliveryService deliveryService, LoginView loginView, NewAccountView newAccountView, ClientView clientView, EmployeeView employeeView, AdministratorView administratorView){
    this.deliveryService = deliveryService;
    this.loginView = loginView;
    this.newAccountView = newAccountView;
    this.clientView = clientView;
    this.employeeView = employeeView;
    this.administratorView = administratorView;

    loginView.addActionListenerLoginButton(new ActionListenerLoginButton());
    loginView.addActionListenerNewAccountButton(new ActionListenerNewAccountButton());
}
 public class ActionListenerLoginButton implements ActionListener{

     @Override
     public void actionPerformed(ActionEvent e) {
         String uname, password;

         uname = loginView.getUsernameTextField();
         password = String.valueOf(loginView.getPasswordField());

         int ok = 1;
         for(Administrator administrator:deliveryService.getAdministratorList()){
             if(administrator.getUsername().equals(uname)){
                 if(!administrator.getPass().equals(password)){
                     loginView.showError("Incorrect password");
                     ok = 0;
                     break;
                 }else{
                     loginView.showError("Connected as Administrator");
                     loginView.getFrame().setVisible(false);
                     administratorView.getFrame().setVisible(true);
                     ok = 0;
                     break;
                 }
             }
         }

         for(Client client : deliveryService.getClientList()){
             if(client.getUsername().equals(uname)){
                 if(!client.getPass().equals(password)){
                     loginView.showError("Incorrect password");
                     ok = 0;
                     break;
                 }else{
                     loginView.showError("Connected as Client");
                     loginView.getFrame().setVisible(false);
                     clientView.getFrame().setVisible(true);
                     ok = 0;
                     deliveryService.setClient(client);
                     break;
                 }
             }
         }
         for(Employee employee : deliveryService.getEmployeeList()){
             if(employee.getUsername().equals(uname)){
                 if(!employee.getPass().equals(password)){
                     loginView.showError("Incorrect password");
                     ok = 0;
                     break;
                 }else{
                     loginView.showError("Connected as Employee");
                     loginView.getFrame().setVisible(false);
                     employeeView.getFrame().setVisible(true);
                     ok = 0;
                     break;
                 }
             }
         }
         if(ok == 1){
             loginView.showError("Incorrect username");
         }
     }
 }

 public class ActionListenerNewAccountButton implements ActionListener{

     @Override
     public void actionPerformed(ActionEvent e) {
         loginView.getFrame().setVisible(false);
         newAccountView.getFrame().setVisible(true);
     }
 }
}
