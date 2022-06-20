package Presentation;

import BusinessLogic.MenuItem;
import BusinessLogic.Order;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class EmployeeView extends JFrame {
    private JPanel panel1;
    private JList<Map.Entry<Order, HashSet<MenuItem>>> employeeOrdersList;
    private JButton deleteButton;
    private JButton backButton;
    private JScrollPane employeeOrdersJscrollPane;
    private JFrame frame;

    public EmployeeView(){
        frame = new JFrame("Employee");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(false);
        employeeOrdersJscrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }
    public void addActionListenerDeleteButton(ActionListener e){
        deleteButton.addActionListener(e);
    }
    public void addActionListenerBackButton(ActionListener e){
        backButton.addActionListener(e);
    }
    public JList<Map.Entry<Order, HashSet<MenuItem>>> getEmployeeOrdersList(){
        return employeeOrdersList;
    }
    void showError(String errMessage) {

        JOptionPane.showMessageDialog(this, errMessage);
    }

    public JFrame getFrame() {
        return frame;
    }
}
