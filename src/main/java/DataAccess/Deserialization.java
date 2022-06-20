package DataAccess;

import BusinessLogic.BaseProduct;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Deserialization {

    /**
     *
     * @param file fisier de intrare
     * @return hashMap, mapul pentru return

     */
    public static<O,C> Map<O, C> mapForDeserialization(String file){
        HashMap<O, C> hashMap = new HashMap<>();
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            hashMap = (HashMap<O, C>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return hashMap;
    }

    /**
     *
     * @param file
     * @return un array list
     */
    public static <O>ArrayList<O> deserializationMem(String file){
        ArrayList<O> arrayList = new ArrayList<>();
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            arrayList.addAll((List<O>)objectInputStream.readObject());

            fileInputStream.close();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
             e.printStackTrace();
        }
        return arrayList;
    }

    /**
     *
     * @param file
     * @return arrayList cu prod deserializate
     */
    public static ArrayList<BaseProduct> deserializationMemCsv(String file){
        ArrayList<BaseProduct> arrayList = new ArrayList<>();

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String product = bufferedReader.readLine();

            while((product = bufferedReader.readLine())!= null){
                String[] stringProduct = product.split(",");
                try{
                    arrayList.add(new BaseProduct(stringProduct[0], Double.parseDouble(stringProduct[1]),
                            Integer.parseInt(stringProduct[2]), Integer.parseInt(stringProduct[3]),
                            Integer.parseInt(stringProduct[4]), Integer.parseInt(stringProduct[5]),
                            Integer.parseInt(stringProduct[6])));
                }catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            bufferedReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return arrayList;
    }
}
