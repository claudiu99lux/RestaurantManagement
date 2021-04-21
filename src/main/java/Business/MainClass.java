package Business;

import Presentation.MainMenuGUI;

/**
 * This is the main class
 * @params args -> takes serialized file to initialize products and orders
 */

public class MainClass {
    public static String saveFileName;

    public static void main(String[] args){
        int loadFile=0;
        if(args.length<1){
            System.out.println("You didn't specify backup name so nothing will be loaded!");
            saveFileName="backup.ser";
        }
        else {
            saveFileName = args[0];
            loadFile=1;
        }

        RestaurantSerializator r = new RestaurantSerializator(saveFileName);
        Restaurant restaurant = new Restaurant();
        if(loadFile==1)
            restaurant.loadData(r);
        MainMenuGUI gui = new MainMenuGUI();
    }
}
