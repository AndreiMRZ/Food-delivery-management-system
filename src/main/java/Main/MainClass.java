package Main;

import BusinessLogic.DeliveryService;
import DataAccess.Serialization;
import Model.Administrator;
import Model.Client;
import Model.Employee;
import Presentation.*;

import java.util.ArrayList;
import java.util.List;

public class MainClass {

    public static void main(String [] args){

        List<Client> clientList = new ArrayList<>();
        List<Employee> employeeList = new ArrayList<>();
        List<Administrator> administratorList = new ArrayList<>();

        employeeList.add(new Employee(1,"Angajat", "12345"));
        clientList.add(new Client("Client", "111"));
        administratorList.add(new Administrator(1, "Administrator", "1233"));


        LoginView loginView = new LoginView();
        ClientView clientView = new ClientView();
        AdministratorView administratorView = new AdministratorView();
        EmployeeView employeeView = new EmployeeView();
        NewAccountView newAccountView = new NewAccountView();
        DeliveryService deliveryService = new DeliveryService();

        Serialization.serializationMem("Client.ser", clientList);
        Serialization.serializationMem("Employee.ser", employeeList);
        Serialization.serializationMem("Administrator.ser", administratorList);


        LoginController loginController = new LoginController(deliveryService, loginView,newAccountView, clientView, employeeView, administratorView );
        NewAccountController newAccountController = new NewAccountController(loginView, newAccountView, deliveryService);
        ClientController clientController = new ClientController(loginView, clientView, deliveryService);
        EmployeeController employeeController = new EmployeeController(loginView, employeeView, deliveryService);
        AdministratorController administratorController = new AdministratorController(loginView, administratorView, deliveryService);
        deliveryService.addObserver(employeeController);
        deliveryService.addObserver(clientController);
    }
}
