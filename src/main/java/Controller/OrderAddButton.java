package Controller;
import Business.MenuItem;
import Business.Order;
import Business.Restaurant;
import Presentation.AdministratorGUI;
import Presentation.EditOrder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Add to order button functionality
 */
public class OrderAddButton implements ActionListener {
    JList availableItemsList;
    Order currentOrder;

    public OrderAddButton(JList availableItemsList, Order currentOrder){
        this.availableItemsList=availableItemsList;
        this.currentOrder=currentOrder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MenuItem selectedProduct = null;
        if(Restaurant.wellFormed(availableItemsList)) {
            int selectedItemPosition = availableItemsList.getSelectedIndex();
            int idOfSelection = Integer.parseInt(AdministratorGUI.tableModel.getValueAt(selectedItemPosition, 0).toString());
            selectedProduct = Restaurant.getProductById(idOfSelection);
            Restaurant.addToOrder(currentOrder, selectedProduct);
            EditOrder.currentOrderContentModel.addElement(selectedProduct.getName());
        }
        else
            JOptionPane.showMessageDialog(null,"Select the item you want to add from the available list!");
    }
}
