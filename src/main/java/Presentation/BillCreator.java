package Presentation;

import Business.MenuItem;
import Business.Order;
import Business.Restaurant;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Creates bill for specific orders;
 */
public class BillCreator {
    Order o;

    public BillCreator(Order o){
        this.o=o;
    }

    public void createBill(){
        File dir = new File("Bills/");
        dir.mkdir();
        File exportedBill = null;
        try {
            exportedBill = new File("Bills/Order"+o.getId()+"_bill.txt");
            FileWriter export = new FileWriter("Bills/Order"+o.getId()+"_bill.txt");
            export.write("BILL\nOrder ID: "+o.getId()+"\nTable: "+o.getTable()+"\n\n");
            export.write("PRODUCTS:\n");
            for(MenuItem m : Restaurant.getOrdersContent().get(o)){
                export.write(m.getName()+"\t\t "+m.computePrice()+" lei\n");
            }
            export.write("TOTAL: " + o.computePrice());
            export.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
