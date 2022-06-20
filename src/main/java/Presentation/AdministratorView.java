package Presentation;

import BusinessLogic.BaseProduct;
import BusinessLogic.CompositeProduct;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AdministratorView extends JFrame {
    private JPanel panel1;
    private JTextField textFieldTitle;
    private JTextField textFieldRating;
    private JTextField textFieldCalories;
    private JTextField textFieldProtein;
    private JTextField textFieldFat;
    private JTextField textFieldSodium;
    private JTextField textFieldPrice;
    private JButton addProductButton;
    private JButton updateProductButton;
    private JList<BaseProduct>  baseProductList;
    private JList<CompositeProduct> composedProductList;
    private JTextField composedProductName;
    private JButton addComposedProductButton;
    private JButton deleteComposedProductButton;
    private JTextField textFieldStartHour;
    private JTextField textFieldEndHour;
    private JButton generateTimeIntervalButton;
    private JTextField textFieldNumberOfOrders;
    private JButton generateProductsOrderedButton;
    private JTextField textFieldNumberOfOrdersClient;
    private JTextField textFieldValueOfOrdersClient;
    private JButton generateClientsButton;
    private JTextField textFieldDay;
    private JButton generateProductDayButton;
    private JScrollPane baseProductJscrollPane;
    private JScrollPane composedProductJscrollPane;
    private JButton backButton;
    private JButton deleteProductButton;
    private JFrame frame;

    public AdministratorView(){
        frame = new JFrame("Administrator");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(false);
        baseProductJscrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        composedProductJscrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public String getTextFieldTitle() {
        return textFieldTitle.getText();
    }

    public String getTextFieldRating() {
        return textFieldRating.getText();
    }

    public String getTextFieldCalories() {
        return textFieldCalories.getText();
    }

    public String getTextFieldProtein() {
        return textFieldProtein.getText();
    }

    public String getTextFieldFat() {
        return textFieldFat.getText();
    }

    public String getTextFieldSodium() {
        return textFieldSodium.getText();
    }

    public String getTextFieldPrice() {
        return textFieldPrice.getText();
    }

    public String getComposedProductName() {
        return composedProductName.getText();
    }

    public String getTextFieldStartHour() {
        return textFieldStartHour.getText();
    }

    public String getTextFieldEndHour() {
        return textFieldEndHour.getText();
    }

    public String getTextFieldNumberOfOrders() {
        return textFieldNumberOfOrders.getText();
    }

    public String getTextFieldNumberOfOrdersClient() {
        return textFieldNumberOfOrdersClient.getText();
    }

    public String getTextFieldValueOfOrdersClient() {
        return textFieldValueOfOrdersClient.getText();
    }

    public String getTextFieldDay() {
        return textFieldDay.getText();
    }

    public JList<BaseProduct> getBaseProductList() {
        return baseProductList;
    }

    public JList<CompositeProduct> getComposedProductList() {
        return composedProductList;
    }
    public void addActionListenerAddProductButton(ActionListener e){
        addProductButton.addActionListener(e);
    }
    public void addActionListenerUpdateProductButton(ActionListener e){
        updateProductButton.addActionListener(e);
    }
    public void addActionListenerAddComposedProductButton(ActionListener e){
        addComposedProductButton.addActionListener(e);
    }
    public void addActionListenerDeleteComposedProductButton(ActionListener e){
        deleteComposedProductButton.addActionListener(e);
    }
    public void addActionListenerGenerateTimeIntervalButton(ActionListener e){
        generateTimeIntervalButton.addActionListener(e);
    }
    public void addActionListenerGenerateProductsOrderedButton(ActionListener e){
        generateProductsOrderedButton.addActionListener(e);
    }
    public void addActionListenerGenerateClientsButton(ActionListener e){
        generateClientsButton.addActionListener(e);
    }
    public void addActionListenerGenerateProductDayButton(ActionListener e){
        generateProductDayButton.addActionListener(e);
    }
    public void addActionListenerBackButton(ActionListener e){
        backButton.addActionListener(e);
    }
    public void addActionListenerDeleteProductButton(ActionListener e){
        deleteProductButton.addActionListener(e);
    }

    public JFrame getFrame() {
        return frame;
    }
    void showError(String errMessage) {

        JOptionPane.showMessageDialog(this, errMessage);
    }
}
