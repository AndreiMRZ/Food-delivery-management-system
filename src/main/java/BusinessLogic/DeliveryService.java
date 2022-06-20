package BusinessLogic;

import DataAccess.Deserialization;
import Model.Administrator;
import Model.Client;
import Model.Employee;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing {
    private HashSet<MenuItem> menuItemHashSet;
    private Map<Order, HashSet<MenuItem>>  orderHashSetMap;
    private List<Administrator> administratorList;
    private List<Client> clientList;
    private List<Employee> employeeList;
    private Client client;
    public static int counterForReports = 0;

    public DeliveryService(){
        dataImport();
    }

    public List<BaseProduct> getBaseProduct(){
        return menuItemHashSet.stream().filter(t -> t instanceof BaseProduct).map(t->(BaseProduct) t).map(BaseProduct.class::cast).collect(Collectors.toList());
    }

    @Override
    public List<BaseProduct> listForSearchProduct(List<String> param1, List<String> param2) {
        List<BaseProduct> list = getBaseProduct();
        Predicate<BaseProduct> predicate;
        List<Predicate<BaseProduct>> predicateList = new ArrayList<>();
        int i = 0;

        for(String string : param1){
            switch (string){
                case "title":
                    int a = i;
                    predicate = t-> t.getTitle().equals(param2.get(a));
                    predicateList.add(predicate);
                    break;

                case "rating":
                    int b = i;
                    predicate = t->t.getRating() >= Double.parseDouble(param2.get(b));
                    predicateList.add(predicate);
                    break;
                case "calorie":
                    int c = i;
                    predicate = t->t.getCalories() >= Integer.parseInt(param2.get(c));
                    predicateList.add(predicate);
                    break;

                case "protein":
                        int d = i;
                    predicate = t->t.getProtein() >= Integer.parseInt(param2.get(d));
                    predicateList.add(predicate);
                    break;

                case "fat":
                        int e = i;
                    predicate = t->t.getFat() >= Integer.parseInt(param2.get(e));
                    predicateList.add(predicate);
                    break;

                case "sodium":
                    int f = i;
                    predicate = t->t.getSodium() >= Integer.parseInt(param2.get(f));
                    predicateList.add(predicate);
                    break;

                case "price":
                    int g = i;
                    predicate = t->t.getPrice() >= Integer.parseInt(param2.get(g));
                    predicateList.add(predicate);
                    break;
            }
            i++;
        }
        for(Predicate<BaseProduct> predicate1 : predicateList){
            list = list.stream().filter(predicate1).collect(Collectors.toList());
        }
        return list;
    }

    @Override
    public void addProduct(MenuItem menuItem) {
            menuItemHashSet.add(menuItem);
            setChanged();
            notifyObservers(1);
    }

    @Override
    public void deleteProduct(MenuItem menuItem) {
            menuItemHashSet.remove(menuItem);
            setChanged();
            notifyObservers(1);
    }

    @Override
    public void addOrder(int idClient, HashSet<MenuItem> products) {
           orderHashSetMap.put(new Order(orderHashSetMap.size()+1, idClient, Calendar.getInstance()), products);
    }

    @Override
    public void dataImport() {
        importClients();
        importMeniu();
        importAdmins();
        importEmployees();
        importOrders();

    }

    @Override
    public void updateProduct(MenuItem prevItem, MenuItem nextItem) {
        if(menuItemHashSet.contains(prevItem)){
            menuItemHashSet.remove(prevItem);
            menuItemHashSet.add(nextItem);
            setChanged();
            notifyObservers(1);
        }

    }
    public void importClients(){
        clientList = Deserialization.deserializationMem("Client.ser");
    }
    public void importMeniu(){
        menuItemHashSet = new HashSet<>();
        menuItemHashSet.addAll(Deserialization.deserializationMemCsv("products.csv"));
        menuItemHashSet.addAll(Deserialization.deserializationMem("ComposedProducts.ser"));

    }
    public void importAdmins(){
       administratorList = Deserialization.deserializationMem("Administrator.ser");
    }
    public void importEmployees(){
        employeeList = Deserialization.deserializationMem("Employee.ser");

    }
    public void importOrders(){
        orderHashSetMap = Deserialization.mapForDeserialization("Orders.ser");
        if(orderHashSetMap == null)
            orderHashSetMap = new HashMap<>();

    }
    public void bill(DefaultListModel<MenuItem> listModel){
        PrintWriter printWriter = null;

        try{
            printWriter = new PrintWriter("Bill" + orderHashSetMap.size() + ".txt");

        }catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }

        StringBuilder ord = new StringBuilder("Order " + orderHashSetMap.size() + 1);
        ord.append("\n Products : " + "\n");
        int k =0;
        int totalPrice = 0;
        while(listModel.size() > k){
            ord.append(listModel.get(k));
            totalPrice += listModel.get(k).countPrice();
            k++;
        }
        ord.append("Total Price = ");
        ord.append(totalPrice);

        printWriter.print(ord);
        printWriter.close();
    }

    public void reportGenerated(String report, String title){
        PrintWriter printWriter = null;
        try{
            printWriter = new PrintWriter(title + counterForReports + ".txt");
            counterForReports++;
        }catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }
        printWriter.print(report);
        printWriter.close();
    }

    public String reportTimeInterval(int stH, int fnH){
        List<Order> orderList = orderHashSetMap.keySet().stream().filter(t-> t.getOrderDate().get(Calendar.HOUR_OF_DAY)>stH && t.getOrderDate().get(Calendar.HOUR_OF_DAY)<fnH).collect(Collectors.toList());
        return orderList.size() + "\n" + orderList;
    }
    public String reportNrOrders(int n){
        Map<MenuItem, Integer> cnt = new HashMap<>();
        for(Map.Entry<Order, HashSet<MenuItem>> entry : orderHashSetMap.entrySet()){
            for(MenuItem product : entry.getValue()){
                cnt.put(product, cnt.getOrDefault(product, 0) + 1);
            }
        }
        return cnt.entrySet().stream().filter(t->t.getValue() > n).collect(Collectors.toList()).toString();
    }
    public String reportNrClients(int n, int price){
        List<Client> clientList1 = new ArrayList<>();
        for(Client clt : clientList){
            if(verifyClient(clt, n, price)){
                clientList1.add(clt);
            }
        }
        System.out.println(clientList1.toString());
        return clientList1.toString();
    }
    public String reportOrdersTime(int day){
        List<Order> orderList = orderHashSetMap.keySet().stream().
                filter(t -> t.getOrderDate().get(Calendar.DATE) == day).collect(Collectors.toList());
        return orderList.size() + "\n" + orderList;
    }
    public boolean verifyClient(Client client, int n, int price){
        int sum = 0;
        for(Map.Entry<Order, HashSet<MenuItem>> entry : orderHashSetMap.entrySet()){
           if(entry.getKey().getClientId() == client.getId()){
               for(MenuItem menuItem : entry.getValue()){
                   sum += menuItem.hashCode();
               }
           }
        }
        return orderHashSetMap.keySet().stream().filter(t->t.getClientId() == client.getId()).count() > n && sum > price;
    }

    public Map<Order, HashSet<MenuItem>> getOrderHashSetMap() {
        return orderHashSetMap;
    }

    public List<Administrator> getAdministratorList() {
        return administratorList;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<CompositeProduct> getCompositeProducts() {
        return menuItemHashSet.stream().filter(t-> t instanceof CompositeProduct).map(t->(CompositeProduct) t).map(CompositeProduct.class::cast).collect(Collectors.toList());

    }
    public boolean existProduct(MenuItem menuItem){
        return menuItemHashSet.contains(menuItem);
    }
    public HashSet<MenuItem> getMenuItemHashSet() {
        return menuItemHashSet;
    }

    public void setMenuItemHashSet(HashSet<MenuItem> menuItemHashSet) {
        this.menuItemHashSet = menuItemHashSet;
    }
}
