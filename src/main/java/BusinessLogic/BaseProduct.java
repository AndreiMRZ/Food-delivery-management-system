package BusinessLogic;

import java.io.Serializable;
import java.util.Objects;

public class BaseProduct extends MenuItem implements Serializable {

    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;
    BaseProduct baseProduct;
    @Override
    public double countPrice() {
        return price;
    }

    public BaseProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price){
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getSodium() {
        return sodium;
    }

    public int getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, rating, calories, protein, fat, sodium, price);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof BaseProduct))
            return false;
        baseProduct = (BaseProduct) obj;
        return this.title.equals(baseProduct.getTitle());
    }

    @Override
    public String toString() {
        return "BaseProduct{" +
                "title='" + title + '\'' +
                ", rating=" + rating +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", price=" + price +
                '}';
    }
}
