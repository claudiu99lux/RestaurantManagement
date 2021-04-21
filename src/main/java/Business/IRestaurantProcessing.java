package Business;

public interface IRestaurantProcessing {

    public static void addToMenu(MenuItem m){};
    public static void removeFromMenu(MenuItem m){};
    public static void addToOrder(Order o, MenuItem m){};
    public static void removeFromOrder(Order o, MenuItem m){};
}
