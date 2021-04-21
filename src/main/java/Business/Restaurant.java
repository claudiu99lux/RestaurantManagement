package Business;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Defines the restaurant functionality
 */
public class Restaurant implements IRestaurantProcessing{
    /**
     * @params restaurantMenu Represents the menu of the restaurant
     * @params ordersContent HashMap that contains the products in an order
     * @params orders List of orders
     * @params observerList List of observers (Observer pattern)
     * @params ORDER_COUNTER static variable, keeps track of the current order count
     * @params PRODUCT_COUNTER static variable, keeps track of the current product count
     */
    private static ArrayList<MenuItem> restaurantMenu;
    private static Map<Order, ArrayList<MenuItem>> ordersContent;
    private static ArrayList<Order> orders;
    private static ArrayList<Observer> observerList;
    public static int ORDER_COUNTER=0;
    public static int PRODUCT_COUNTER=0;

    public Restaurant(){
        ordersContent = new HashMap<Order, ArrayList<MenuItem>>();
        orders = new ArrayList<Order>();
        restaurantMenu = new ArrayList<MenuItem>();
        observerList = new ArrayList<Observer>();
    }

    /**
     * Checks if the object is well formed, part of Design By Contract pattern
     * @param o
     * @return
     */
    public static boolean wellFormed(Object o){
        if(o instanceof String){
            String s = (String)o;
            if(s!=null && s.length()>0)
                return true;
            else
                return false;
        }
        else
            if(o instanceof JList){
                if(((JList)o).getSelectedIndex()==-1)
                    return false;
                return true;
            }
            else
                if(o instanceof JTable){
                    if(((JTable)o).getSelectedRow()==-1)
                        return false;
                    return true;
                }
            return true;
    }

    /**
     * Creates order
     * @param tableNumber specifies the number of the table
     * @return
     */
    public static Order createOrder(int tableNumber){
        Order o = new Order(++ORDER_COUNTER, tableNumber);
        ArrayList<MenuItem> productList = new ArrayList<MenuItem>();
        ordersContent.put(o,productList);
        orders.add(o);
        sendToNotifier(o);
        return o;
    }

    /**
     * Sends the order to the notifier
     * @param o
     */
    public static void sendToNotifier(Order o){
        Notifier n = new Notifier(observerList);
        n.notifyObservers(o);
    }

    /**
     * Registers new observer
     * @param O
     */
    public static void registerObserver(Observer O){
        Notifier n = new Notifier(observerList);
        n.registerObserver(O);
    }

    /**
     * Loads restaurant data from the serialized file
     * @param r Serializator object
     */
    public void loadData(RestaurantSerializator r){
        r.loadRestaurantState();
        restaurantMenu=r.getRestaurantMenu();
        orders=r.getOrders();
        ordersContent=r.getOrdersContent();
        ORDER_COUNTER=r.getOrderCounter();
        PRODUCT_COUNTER=r.getProductCounter();
    }

    /**
     * Adds item to restaurant menu
     * @param m
     */
    public static void addToMenu(MenuItem m){
        restaurantMenu.add(m);
    }

    /**
     * Removes item from restaurant menu
     * @param m
     */
    public static void removeFromMenu(MenuItem m){
        restaurantMenu.remove(m);
    }

    /**
     * Finds MenuItem with specified id
     * @param id
     * @return MenuItem with specified id
     */
    public static MenuItem getProductById(int id){
        MenuItem selectedProduct=null;
        for (MenuItem m : restaurantMenu) {
            if (m.getId()==id) {
                selectedProduct = m;
                break;
            }
        }
        return selectedProduct;
    }

    /**
     * Finds MenuItem with specified name
     * @param name
     * @return MenuItem with specified name
     */
    public static MenuItem getProductByName(String name){
        MenuItem selectedProduct=null;
        for (Business.MenuItem m : restaurantMenu) {
            if (m.getName().toLowerCase().equals(name.toLowerCase())) {
                selectedProduct = m;
                break;
            }
        }
        return selectedProduct;
    }

    /**
     * Finds order by ID
     * @param id
     * @return Order with specified ID
     */
    public static Order getOrderById(int id){
        Order selectedOrder=null;
        for (Order o : Restaurant.getOrders()) {
            if (o.getId()==id) {
                selectedOrder = o;
                break;
            }
        }
        return selectedOrder;
    }

    /**
     * Checks if an object with specified name already exists
     * @param name
     * @return true if already exists, false otherwise
     */
    public static boolean containsProductName(String name){
        boolean exists=false;
        for(Business.MenuItem m : restaurantMenu){
            if(m.getName().toLowerCase().equals(name.toLowerCase())){
                exists=true;
                break;
            }
        }
        return exists;
    }

    /**
     * Getter for orderContent
     * @return orderContent HashMap
     */
    public static Map<Order, ArrayList<MenuItem>> getOrdersContent() {
        return ordersContent;
    }

    /**
     * Getter for orders
     * @return orders ArrayList
     */
    public static ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Getter for restaurant menu
     * @return menu ArrayList
     */
    public static ArrayList<MenuItem> getMenu(){
        return restaurantMenu;
    }

    /**
     * Saves current state of the restaurant, calls RestaurantSerializator to Serialize the data
     */
    public static void saveState(){
        RestaurantSerializator r = new RestaurantSerializator(MainClass.saveFileName);
        r.saveRestaurantState(restaurantMenu, orders, ordersContent, ORDER_COUNTER, PRODUCT_COUNTER);
    }

    /**
     * Adds a MenuItem to an Order
     * @param o Order
     * @param m The item that needs to be added
     */
    public static void addToOrder(Order o, MenuItem m){
        ArrayList<MenuItem> list = Restaurant.getOrdersContent().get(o);
        list.add(m);
        Restaurant.getOrdersContent().put(o,list);
        sendToNotifier(o);
    }

    /**
     * Removes a MenuItem from an Order
     * @param o Order
     * @param m The item that needs to be removed from the order
     */
    public static void removeFromOrder(Order o, MenuItem m){
        ArrayList<MenuItem> list = Restaurant.getOrdersContent().get(o);
        list.remove(m);
        Restaurant.getOrdersContent().put(o,list);
        sendToNotifier(o);
    }
}
