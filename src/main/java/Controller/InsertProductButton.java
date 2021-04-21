package Controller;

import Business.BaseProduct;
import Business.MenuItem;
import Business.Restaurant;
import Presentation.AdministratorGUI;
import Presentation.InsertProduct;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Insert product button functionality
 */
public class InsertProductButton implements ActionListener {

    public InsertProductButton(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        InsertProduct insert = new InsertProduct();
    }

    public static void insert(String name, float price){
        boolean alreadyExists=Restaurant.containsProductName(name);
        if(alreadyExists){
            JOptionPane.showMessageDialog(null,"A product with the same name already exists in the menu! If you want to modify the price or name consider editing the product.");
        }
        else {
            MenuItem insertedProduct = new BaseProduct(++Restaurant.PRODUCT_COUNTER, name, price);
            Restaurant.addToMenu(insertedProduct);
            AdministratorGUI.tableModel.addRow(insertedProduct.prepareTableRecord());
            AdministratorGUI.availableProductsModel.addElement(name);
        }
    }
}
