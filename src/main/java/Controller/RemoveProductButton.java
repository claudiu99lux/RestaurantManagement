package Controller;

import Business.CompositeProduct;
import Business.MenuItem;
import Business.Restaurant;
import Presentation.AdministratorGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Remove product button functionality, deletes product from the restaurant menu
 */
public class RemoveProductButton implements ActionListener {
    JTable table;
    public RemoveProductButton(JTable table){
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MenuItem selectedItem=null;
        if(Restaurant.wellFormed(table)) {
            int row = table.getSelectedRow();
            int idOfSelection = Integer.parseInt(AdministratorGUI.tableModel.getValueAt(row, 0).toString());
            selectedItem = Restaurant.getProductById(idOfSelection);
            Restaurant.removeFromMenu(selectedItem);
            ArrayList<MenuItem> compositesToDelete = new ArrayList<MenuItem>();
            for (Business.MenuItem m : Restaurant.getMenu()) {
                if (m instanceof CompositeProduct)
                    if(((CompositeProduct) m).containsProduct(selectedItem))
                        compositesToDelete.add(m);
            }
            for(MenuItem m : compositesToDelete){
                for(int i=0; i<AdministratorGUI.tableModel.getRowCount(); i++){
                    if(AdministratorGUI.tableModel.getValueAt(i,1).equals(m.getName())){
                        AdministratorGUI.tableModel.removeRow(i);
                        break;
                    }
                }

                for(int i=0; i<AdministratorGUI.availableProductsModel.getSize();i++){
                    if(AdministratorGUI.availableProductsModel.elementAt(i).equals(m.getName())) {
                        AdministratorGUI.availableProductsModel.remove(i);
                        break;
                    }
                }
                Restaurant.removeFromMenu(m);
            }
            AdministratorGUI.tableModel.removeRow(row);
            AdministratorGUI.availableProductsModel.remove(row);
        }
        else
            JOptionPane.showMessageDialog(null,"Select the item you want to remove!");
    }
}

