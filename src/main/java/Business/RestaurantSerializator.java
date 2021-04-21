package Business;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Seralize and Deserialize the restaurant data
 */
public class RestaurantSerializator {
    /**
     * @param fileName name of the file where the restaurant data will be savedto/loaded from
     */
    private ArrayList<MenuItem> restaurantMenu;
    private ArrayList<Order> orders;
    private HashMap<Order, ArrayList<MenuItem>> ordersContent;
    private int orderCounter;
    private int productCounter;
    String fileName;

    /**
     * Constructor, gets filename as parameter
     * @param fileName
     */
    public RestaurantSerializator(String fileName){
        this.fileName=fileName;
    }

    public void saveRestaurantState(ArrayList<MenuItem> restaurantMenu, ArrayList<Order> orders, Map<Order, ArrayList<MenuItem>> ordersContent, int orderCounter, int productCounter){
        try{
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(restaurantMenu);
            out.writeObject(orders);
            out.writeObject(ordersContent);
            out.writeObject(orderCounter);
            out.writeObject(productCounter);
            out.close();
            file.close();
        }catch(FileNotFoundException e){
            System.out.println("File could not be created");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadRestaurantState(){
        try{
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);
            this.restaurantMenu=(ArrayList<MenuItem>)in.readObject();
            this.orders = (ArrayList<Order>)in.readObject();
            this.ordersContent = (HashMap<Order, ArrayList<MenuItem>>)in.readObject();
            this.orderCounter = (int)in.readObject();
            this.productCounter = (int)in.readObject();
            in.close();
            file.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found!");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public ArrayList<MenuItem> getRestaurantMenu() {
        return restaurantMenu;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public Map<Order, ArrayList<MenuItem>> getOrdersContent() {
        return ordersContent;
    }

    public int getOrderCounter() {
        return orderCounter;
    }

    public int getProductCounter() {
        return productCounter;
    }
}
