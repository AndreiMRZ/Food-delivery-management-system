package BusinessLogic;

import java.util.HashSet;
import java.util.List;

public interface IDeliveryServiceProcessing {
    List<BaseProduct> listForSearchProduct(List<String> param1, List<String> param2 );
    void addProduct(MenuItem menuItem);
    void deleteProduct(MenuItem menuItem);
    void addOrder(int idClient, HashSet<MenuItem> products);
    void dataImport();
    void updateProduct(MenuItem prevItem, MenuItem nextItem);


}
