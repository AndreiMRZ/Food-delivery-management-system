package BusinessLogic;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

public class CompositeProduct extends MenuItem implements Serializable {
    private HashSet<BaseProduct> baseProductHashSet;
    private String title;

    public CompositeProduct(String title, HashSet<BaseProduct> baseProductHashSet){
        this.title = title;
        this.baseProductHashSet = baseProductHashSet;
    }
    public CompositeProduct(){
        baseProductHashSet = new HashSet<>();
    }

    @Override
    public double countPrice() {
        return baseProductHashSet.stream().map(BaseProduct :: getPrice).reduce(0, Integer :: sum);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public HashSet<BaseProduct> getBaseProductHashSet() {
        return baseProductHashSet;
    }

    public void setBaseProductHashSet(HashSet<BaseProduct> baseProductHashSet) {
        this.baseProductHashSet = baseProductHashSet;
    }
    public void addCompositeProduct(BaseProduct baseProduct){
        baseProductHashSet.add(baseProduct);
    }

    @Override
    public String toString() {
        return "CompositeProduct" +
                "baseProduct " + baseProductHashSet +
                ", title= '" + title + '\'';
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof CompositeProduct)) return false;
        CompositeProduct compositeProduct = (CompositeProduct) obj;
        return this.title.equals(compositeProduct.getTitle());
    }
}
