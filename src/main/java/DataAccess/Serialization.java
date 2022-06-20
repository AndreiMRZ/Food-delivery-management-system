package DataAccess;

import BusinessLogic.BaseProduct;

import java.io.*;
import java.util.List;
import java.util.Map;

public class Serialization {
    /**
     *
     * @param file
     * @param map un map dat ca parametru de intrare

     */
    public static <O, C> void mapForSerialization(String file, Map<O, C> map){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(map);

            objectOutputStream.close();
            fileOutputStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param file
     * @param baseProductList o lista de produse
     */
    public static void serializationMemCsv(String file, List<BaseProduct> baseProductList){
        try{
            FileWriter writer = new FileWriter(file);
            writer.append("Title").append(",").append("Rating").append(",").append("Calories").append(",").append("Protein").append(",").append("Fat").append(",").append("Sodium").append("Price").append("\n");

            for(BaseProduct baseProduct : baseProductList){
                writer.append(baseProduct.getTitle()).append(",").append(String.valueOf(baseProduct.getRating())).append(",").append(String.valueOf(baseProduct.getCalories())).append(",").append(String.valueOf(baseProduct.getProtein())).append(",").append(String.valueOf(baseProduct.getFat())).append(",").append(String.valueOf(baseProduct.getSodium())).append(",").append(String.valueOf(baseProduct.getPrice())).append("\n");

            }
            writer.flush();
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param file
     * @param list
     */
    public static <O> void serializationMem(String file, List<O> list){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(list);

            objectOutputStream.close();
            fileOutputStream.close();

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
