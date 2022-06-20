package Presentation;

import BusinessLogic.BaseProduct;
import BusinessLogic.CompositeProduct;
import BusinessLogic.DeliveryService;
import BusinessLogic.MenuItem;
import DataAccess.Serialization;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ClientController implements Observer {
    private DefaultListModel<BaseProduct> listBaseProducts;
    private DefaultListModel<CompositeProduct> listCompositeProduct;
    private DefaultListModel<MenuItem> listMenuItem;
    private LoginView loginView;
    private ClientView clientView;
    private DeliveryService deliveryService;

    public ClientController(){

    }
    public ClientController(LoginView loginView, ClientView clientView, DeliveryService deliveryService){
        this.loginView = loginView;
        this.clientView = clientView;
        this.deliveryService = deliveryService;

        listBaseProducts = new DefaultListModel<>();
        listCompositeProduct = new DefaultListModel<>();
        listMenuItem = new DefaultListModel<>();

        clientView.addActionListenerAddProductButton(new ActionListenerAddProductButton());
        clientView.addActionListenerBackButton(new ActionListenerBackButton());
        clientView.addActionListenerCreateOrderButton(new ActionListenerCreateOrderButton());
        clientView.addActionListenerDeleteProductButton(new ActionListenerDeleteProductButton());
        clientView.addActionListenerSearchButton(new ActionListenerSearchButton());

        setJlistProduct();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg != null){
            listBaseProducts.removeAllElements();
            listCompositeProduct.removeAllElements();
            setJlistProduct();
        }
    }
    class ActionListenerBackButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clientView.getFrame().setVisible(false);
            loginView.getFrame().setVisible(true);
        }
    }

    public class ActionListenerAddProductButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            List<CompositeProduct> selectedCompositeProduct = clientView.getComposeProductList().getSelectedValuesList();
            List<BaseProduct> selectedBaseProduct = clientView.getBaseProductList().getSelectedValuesList();

            if(selectedBaseProduct.size() == 0 && selectedCompositeProduct.size() == 0){
                clientView.showError("Select a product");
            }
            else{
                for(CompositeProduct compositeProduct : selectedCompositeProduct){
                    listMenuItem.addElement(compositeProduct);
                }
                for(BaseProduct baseProduct :selectedBaseProduct){
                    listMenuItem.addElement(baseProduct);
                }
                clientView.getOrderList().setModel(listMenuItem);
            }
        }
    }

    public class ActionListenerCreateOrderButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(listMenuItem.size() == 0){
                clientView.showError("Select Product");
                return;
            }
            HashSet<MenuItem> menuItems = new HashSet<>();
            int i = 0;
            while(listMenuItem.size() > i)
            {
                menuItems.add(listMenuItem.get(i));
                i++;
            }
            deliveryService.bill(listMenuItem);
            deliveryService.addOrder(deliveryService.getClient().getId(), menuItems);
            Serialization.mapForSerialization("Orders.ser", deliveryService.getOrderHashSetMap());
        }
    }
    public void setJlistProduct(){
        Iterator<BaseProduct> iterator1 = deliveryService.getBaseProduct().listIterator();
        Iterator<CompositeProduct> iterator2 = deliveryService.getCompositeProducts().listIterator();

        while(iterator1.hasNext()){
            listBaseProducts.addElement(iterator1.next());
        }
        while(iterator2.hasNext()){
            listCompositeProduct.addElement(iterator2.next());
        }

        clientView.getBaseProductList().setModel(listBaseProducts);
        clientView.getComposeProductList().setModel(listCompositeProduct);
    }
    public class ActionListenerDeleteProductButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            List<MenuItem> selectedProducts = clientView.getOrderList().getSelectedValuesList();

            if(selectedProducts.size() > 0){
                for(MenuItem menuItem : selectedProducts){
                    listMenuItem.removeElement(menuItem);
                }
                clientView.getOrderList().setModel(listMenuItem);
            }else{
                clientView.showError("Select a product");
            }
        }
    }
    public class  ActionListenerSearchButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String title = "";
            double rating = 0;
            int calories = 0, protein = 0,fat = 0,  sodium = 0, price = 0;
            title = clientView.getTitleTextField();
            if(!clientView.getRatingTextField().equals(""))
                rating = Double.parseDouble(clientView.getRatingTextField());
            if(!clientView.getCaloriesTextField().equals(""))
                calories = Integer.parseInt(clientView.getCaloriesTextField());
            if(!clientView.getProteinTextField().equals(""))
                protein = Integer.parseInt(clientView.getProteinTextField());
            if(!clientView.getFatTextField().equals(""))
                fat = Integer.parseInt(clientView.getFatTextField());
            if(!clientView.getSodiumTextField().equals(""))
                sodium = Integer.parseInt((clientView.getSodiumTextField()));
            if(!clientView.getPriceTextField().equals(""))
                price = Integer.parseInt(clientView.getPriceTextField());

            List<String> stringList = new ArrayList<>();
            List<String> verify = new ArrayList<>();

            if(!title.equals("")){
                stringList.add("title");
                verify.add(title);
            }
            if(rating > 0){
                stringList.add("rating");
                verify.add(String.valueOf(rating));
            }

            if(calories > 0){
                stringList.add("calories");
                verify.add(String.valueOf(calories));
            }

            if(protein > 0)
            {
                stringList.add("protein");
                verify.add(String.valueOf(protein));
            }

            if(fat > 0){
                stringList.add("fat");
                verify.add(String.valueOf(fat));
            }

            if(sodium > 0){
                stringList.add("sodium");
                verify.add(String.valueOf(sodium));
            }

            if(price > 0){
                stringList.add("price");
                verify.add(String.valueOf(price));
            }

            listBaseProducts.removeAllElements();
            List<BaseProduct> searchResult = deliveryService.listForSearchProduct(stringList, verify);
            for(BaseProduct baseProduct : searchResult){
                listBaseProducts.addElement(baseProduct);
            }
            clientView.getBaseProductList().setModel(listBaseProducts);

        }
    }
}
