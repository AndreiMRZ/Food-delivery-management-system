package Presentation;

import BusinessLogic.BaseProduct;
import BusinessLogic.CompositeProduct;
import BusinessLogic.DeliveryService;
import DataAccess.Serialization;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class AdministratorController {
    private LoginView loginView;
    private AdministratorView administratorView;
    private DeliveryService deliveryService;

    private DefaultListModel<BaseProduct> listBaseProduct;
    private DefaultListModel<CompositeProduct> listCompositeModel;

    public AdministratorController(){

    }
    public AdministratorController(LoginView loginView, AdministratorView administratorView, DeliveryService deliveryService){
        this.loginView = loginView;
        this.administratorView = administratorView;
        this.deliveryService = deliveryService;

        administratorView.addActionListenerAddProductButton(new ActionListenerAddProductButton());
        administratorView.addActionListenerBackButton(new ActionListenerBackButton());
        administratorView.addActionListenerAddComposedProductButton(new ActionListenerAddComposedProductButton());
        administratorView.addActionListenerDeleteComposedProductButton(new ActionListenerDeleteComposedProductButton());
        administratorView.addActionListenerGenerateClientsButton(new ActionListenerGenerateClientButton());
        administratorView.addActionListenerGenerateProductDayButton(new ActionListenerGenerateProductDayButton());
        administratorView.addActionListenerGenerateProductsOrderedButton(new ActionListenerGenerateProductsOrderedButton());
        administratorView.addActionListenerGenerateTimeIntervalButton(new ActionListenerGenerateTimeIntervalButton());
        administratorView.addActionListenerUpdateProductButton(new ActionListenerUpdateProductButton());
        administratorView.addActionListenerDeleteProductButton(new ActionListenerDeleteProductButton());

        listBaseProduct = new DefaultListModel<>();
        listCompositeModel = new DefaultListModel<>();
        setJListProducts();
    }



    public class ActionListenerDeleteProductButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            BaseProduct baseProduct = administratorView.getBaseProductList().getSelectedValue();
            if(baseProduct != null){
                deliveryService.deleteProduct(baseProduct);
                administratorView.showError("Product deleted");
                listBaseProduct.removeElement(baseProduct);
                administratorView.getBaseProductList().setModel(listBaseProduct);
                Serialization.serializationMemCsv("products.csv", deliveryService.getBaseProduct());
            }else
                administratorView.showError("Select a product");
        }
    }
    public class ActionListenerGenerateClientButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int price , nr;
            price =Integer.parseInt(administratorView.getTextFieldValueOfOrdersClient());
            nr = Integer.parseInt(administratorView.getTextFieldNumberOfOrdersClient());
            deliveryService.reportGenerated(deliveryService.reportNrClients(nr, price), "NumberOfClients");
        }
    }
    public class ActionListenerGenerateProductDayButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int day;
            day =Integer.parseInt(administratorView.getTextFieldDay());
            deliveryService.reportGenerated(deliveryService.reportOrdersTime(day), "OrdersTime");

        }
    }
    public class ActionListenerGenerateProductsOrderedButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int nr;
            nr = Integer.parseInt(administratorView.getTextFieldNumberOfOrders());
            //System.out.println(deliveryService.reportNrOrders(nr));
            deliveryService.reportGenerated(deliveryService.reportNrOrders(nr), "NumberOfOrders");

        }
    }

    public class ActionListenerUpdateProductButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String title;
            double rating;
            int calories, protein, fat, sodium, price;
            try{
                title = administratorView.getTextFieldTitle();
                rating = Double.parseDouble(administratorView.getTextFieldRating());
                calories = Integer.parseInt(administratorView.getTextFieldCalories());
                protein = Integer.parseInt(administratorView.getTextFieldProtein());
                fat = Integer.parseInt(administratorView.getTextFieldFat());
                sodium = Integer.parseInt(administratorView.getTextFieldSodium());
                price = Integer.parseInt(administratorView.getTextFieldPrice());
            }catch(Exception a){
                administratorView.showError(a.getMessage());
                return;
            }
            BaseProduct baseProduct = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
            BaseProduct prevBaseProduct = administratorView.getBaseProductList().getSelectedValue();

            if(prevBaseProduct != null){
                deliveryService.deleteProduct(prevBaseProduct);
                deliveryService.addProduct(baseProduct);
                administratorView.showError("Updated Product");
                listBaseProduct.removeElement(prevBaseProduct);
                listBaseProduct.addElement(baseProduct);
                administratorView.getBaseProductList().setModel(listBaseProduct);
                Serialization.serializationMemCsv("products.csv", deliveryService.getBaseProduct());
            }else{
                administratorView.showError("Select a product");
            }
        }
    }

    public class ActionListenerGenerateTimeIntervalButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int startHour, endHour;
            startHour = Integer.parseInt(administratorView.getTextFieldStartHour());
            endHour = Integer.parseInt(administratorView.getTextFieldEndHour());
            deliveryService.reportGenerated(deliveryService.reportTimeInterval(startHour, endHour), "IntervalTime");
        }

    }

    public class ActionListenerDeleteComposedProductButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            CompositeProduct compositeProduct = administratorView.getComposedProductList().getSelectedValue();
            if(compositeProduct != null){
                deliveryService.deleteProduct(compositeProduct);
                administratorView.showError("Product deleted");
                listCompositeModel.removeElement(compositeProduct);
                administratorView.getComposedProductList().setModel(listCompositeModel);
                Serialization.serializationMem("ComposedProducts.ser", deliveryService.getCompositeProducts());
            }else
                administratorView.showError("Select a product");
        }
    }
    public class ActionListenerAddProductButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String title;
            double rating;
            int calories, protein, fat, sodium, price;
            try{
                title = administratorView.getTextFieldTitle();
                rating = Double.parseDouble(administratorView.getTextFieldRating());
                calories = Integer.parseInt(administratorView.getTextFieldCalories());
                protein = Integer.parseInt(administratorView.getTextFieldProtein());
                fat = Integer.parseInt(administratorView.getTextFieldFat());
                sodium = Integer.parseInt(administratorView.getTextFieldSodium());
                price = Integer.parseInt(administratorView.getTextFieldPrice());
            }catch(Exception a){
                administratorView.showError(a.getMessage());
                return;
            }
            BaseProduct baseProduct = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
            if(deliveryService.existProduct(baseProduct)){
                administratorView.showError("Product exist");
            }else{
                deliveryService.addProduct(baseProduct);
                administratorView.showError("New product has ben added");
                listBaseProduct.addElement(baseProduct);
                administratorView.getBaseProductList().setModel(listBaseProduct);
                Serialization.serializationMemCsv("products.csv", deliveryService.getBaseProduct());
            }
        }
    }
    public class ActionListenerBackButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           administratorView.getFrame().setVisible(false);
           loginView.getFrame().setVisible(true);
        }
    }
    public class ActionListenerAddComposedProductButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String title = "";
            List<BaseProduct> baseProductList = administratorView.getBaseProductList().getSelectedValuesList();
            title = administratorView.getComposedProductName();

            if(title.equals("")){
                administratorView.showError("Name the new Product");
                return;
            }
            CompositeProduct compositeProduct = new CompositeProduct();
            if(baseProductList.size() >= 2){
                compositeProduct.setTitle(title);
                for(BaseProduct baseProduct : baseProductList){
                    compositeProduct.addCompositeProduct(baseProduct);
                }

            int ok = 0;
            for(CompositeProduct compositeProduct1 : deliveryService.getCompositeProducts()){
                if(compositeProduct1.equals(compositeProduct)){
                    ok = 1;
                    break;
                }
            }
            if(ok == 0){
                listCompositeModel.addElement(compositeProduct);
                deliveryService.addProduct(compositeProduct);
                administratorView.showError("New Composite Product has been created");
                Serialization.serializationMem("ComposedProducts.ser", deliveryService.getCompositeProducts());

            }else{
                administratorView.showError("Product already exists");
            }

        }
            else
                administratorView.showError("Select 2 base products");
    }




}

private void setJListProducts(){
    Iterator<BaseProduct> iterator1 = deliveryService.getBaseProduct().listIterator();
    Iterator<CompositeProduct> iterator2 = deliveryService.getCompositeProducts().listIterator();

    while(iterator1.hasNext()){
        listBaseProduct.addElement(iterator1.next());
    }
    while(iterator2.hasNext()){
       listCompositeModel.addElement(iterator2.next());
    }
    administratorView.getBaseProductList().setModel(listBaseProduct);
    administratorView.getComposedProductList().setModel(listCompositeModel);
}
}