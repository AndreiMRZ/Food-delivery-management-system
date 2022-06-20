package Presentation;

import BusinessLogic.DeliveryService;
import DataAccess.Serialization;
import Model.Administrator;
import Model.Client;
import Model.Employee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewAccountController {
    private LoginView loginView;
    private NewAccountView newAccountView;
    private DeliveryService deliveryService;

    public NewAccountController(){

    }
    public NewAccountController(LoginView loginView, NewAccountView newAccountView, DeliveryService deliveryService){
        this.loginView = loginView;
        this.newAccountView = newAccountView;
        this.deliveryService = deliveryService;

        newAccountView.addActionListenerCreateButton(new ActionListenerCreateButton());
        newAccountView.addActionListenerBackButton(new ActionListenerBackButton());
    }
    public class ActionListenerBackButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           newAccountView.getFrame().setVisible(false);
           loginView.getFrame().setVisible(true);
        }
    }

    public DeliveryService getDeliveryService() {
        return deliveryService;
    }

    public void setDeliveryService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    public class ActionListenerCreateButton extends NewAccountController implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String uname, password;
            uname = newAccountView.getTextFieldUsername();
            password = String.valueOf(newAccountView.getPasswordField());

            setDeliveryService(super.getDeliveryService());

            int ok = 0;
            for(Administrator administrator : deliveryService.getAdministratorList()){
                if(administrator.getUsername().equals(uname)){
                    newAccountView.showError("Username already exists");
                    ok = 1;
                    break;
                }
            }
            for(Client client : deliveryService.getClientList()){
                if(client.getUsername().equals(uname)){
                    newAccountView.showError("Username already exists");
                    ok  = 1;
                    break;
                }
            }
            for(Employee employee : deliveryService.getEmployeeList()){
                if(employee.getUsername().equals(uname)){
                    newAccountView.showError("Username already exists");
                    ok = 1;
                    break;
                }
            }
            if(ok == 0){
                int id;
                id = deliveryService.getClientList().get(deliveryService.getClientList().size() - 1).getId();
                deliveryService.getClientList().add(new Client(id + 1, uname, password));
                Serialization.serializationMem("Client.ser", deliveryService.getClientList());
                newAccountView.showError("New Account has been created");
            }
        }
    }
}
