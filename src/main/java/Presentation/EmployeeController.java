package Presentation;

import BusinessLogic.DeliveryService;
import BusinessLogic.MenuItem;
import BusinessLogic.Order;
import DataAccess.Serialization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class EmployeeController implements Observer {
    private LoginView loginView;
    private EmployeeView employeeView;
    private DeliveryService deliveryService;

    DefaultListModel<Map.Entry<Order, HashSet<MenuItem>>> defaultListModel;

    public EmployeeController(){

    }
    public EmployeeController(LoginView loginView, EmployeeView employeeView, DeliveryService deliveryService){
        defaultListModel = new DefaultListModel<>();

        this.loginView = loginView;
        this.employeeView = employeeView;
        this.deliveryService = deliveryService;

        employeeView.addActionListenerBackButton(new ActionListenerBackButton());
        employeeView.addActionListenerDeleteButton(new ActionListenerDeleteButton(deliveryService, defaultListModel));
        setJList();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg == null){
            defaultListModel.removeAllElements();
            setJList();
        }
    }

    public class ActionListenerBackButton  implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            employeeView.getFrame().setVisible(false);
            loginView.getFrame().setVisible(true);
        }
    }

    public class ActionListenerDeleteButton extends EmployeeController implements ActionListener{
        public ActionListenerDeleteButton(DeliveryService deliveryService, DefaultListModel<Map.Entry<Order, HashSet<MenuItem>>> defaultListModel){
            this.setDefaultListModel(defaultListModel);
            this.setDeliveryService(deliveryService);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            List<Map.Entry<Order, HashSet<MenuItem>>> list = employeeView.getEmployeeOrdersList().getSelectedValuesList();
            for(Map.Entry<Order, HashSet<MenuItem>> map : list){
                deliveryService.getOrderHashSetMap().remove(map.getKey());
                defaultListModel.removeElement(map);
            }
            employeeView.getEmployeeOrdersList().setModel(defaultListModel);
            Serialization.mapForSerialization("Orders.ser", deliveryService.getOrderHashSetMap());
        }
    }

    public void setJList(){
        for (Map.Entry<Order, HashSet<MenuItem>> tmp : deliveryService.getOrderHashSetMap().entrySet()) {
            defaultListModel.addElement(tmp);
        }
        employeeView.getEmployeeOrdersList().setModel(defaultListModel);
    }

    public void setDefaultListModel(DefaultListModel<Map.Entry<Order, HashSet<MenuItem>>> defaultListModel) {
        this.defaultListModel = defaultListModel;
    }

    public void setDeliveryService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }
}
