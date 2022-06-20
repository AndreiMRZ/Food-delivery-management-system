package Presentation;

import BusinessLogic.BaseProduct;
import BusinessLogic.CompositeProduct;
import BusinessLogic.MenuItem;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ClientView extends JFrame {
    private JFrame frame;
    private JPanel panel1;
    private JTextField titleTextField;
    private JTextField ratingTextField;
    private JTextField caloriesTextField;
    private JTextField proteinTextField;
    private JTextField fatTextField;
    private JTextField sodiumTextField;
    private JTextField priceTextField;
    private JButton searchButton;
    private JButton backButton;
    private JButton addProductButton;
    private JButton deleteProductButton;
    private JButton createOrderButton;
    private JScrollPane baseProductJscrollPane;
    private JList <BaseProduct> baseProductList;
    private JScrollPane composeProductJscrollPane;
    private JList <CompositeProduct> composeProductList;
    private JScrollPane orderJscrollPane;
    private JList<MenuItem> orderList;

    public ClientView(){
        frame = new JFrame("Client");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(false);
        baseProductJscrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        composeProductJscrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        orderJscrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public String getTitleTextField(){
        return titleTextField.getText();
    }
    public String getRatingTextField(){
        return ratingTextField.getText();
    }
    public String getCaloriesTextField(){
        return caloriesTextField.getText();
    }
    public String getProteinTextField(){
        return proteinTextField.getText();
    }
    public String getFatTextField(){
        return fatTextField.getText();
    }
    public String getSodiumTextField(){
        return sodiumTextField.getText();
    }
    public String getPriceTextField(){
        return priceTextField.getText();
    }

    public JList<BaseProduct> getBaseProductList() {
        return baseProductList;
    }

    public JList<CompositeProduct> getComposeProductList() {
        return composeProductList;
    }

    public JList<MenuItem> getOrderList() {
        return orderList;
    }
    public void addActionListenerSearchButton(ActionListener e){
        searchButton.addActionListener(e);
    }
    public void addActionListenerBackButton(ActionListener e){
        backButton.addActionListener(e);
    }
    public void addActionListenerAddProductButton(ActionListener e){
        addProductButton.addActionListener(e);
    }
    public void addActionListenerDeleteProductButton(ActionListener e){
        deleteProductButton.addActionListener(e);
    }
    public void addActionListenerCreateOrderButton(ActionListener e){
        createOrderButton.addActionListener(e);
    }
    void showError(String errMessage) {

        JOptionPane.showMessageDialog(this, errMessage);
    }
    public JFrame getFrame() {
        return frame;
    }
}
