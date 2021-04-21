package Controller;

import Business.CompositeProduct;
import Business.MenuItem;
import Business.Restaurant;
import Presentation.AdministratorGUI;
import Presentation.CreateComposite;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Create composite button functionality
 */
public class CreateCompositeButton implements ActionListener {
    JTextField name;

    public CreateCompositeButton(){
        this.name = name;
    }

    /**
     * Creates a new composite with specified items
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        CreateComposite composite = new CreateComposite();
    }

    public static void create(JTextField name){
        boolean alreadyExists = Restaurant.containsProductName(name.getText());
        if(alreadyExists){
            JOptionPane.showMessageDialog(null,"A product with the same name already exists in the menu! If you want to modify components, consider editing the existing product.");
        }
        else {
            CreateComposite.updateTempProduct(name.getText(),++Restaurant.PRODUCT_COUNTER);
            MenuItem prod = CreateComposite.getTempProduct();
            AdministratorGUI.tableModel.addRow(prod.prepareTableRecord());
            Restaurant.addToMenu(prod);
            AdministratorGUI.availableProductsModel.addElement(prod.getName());
            CreateComposite.clearTempProduct(); // clear temp product for next usage
            CreateComposite.currentItemsModel.clear();
            name.setText("");
        }
    }
}
