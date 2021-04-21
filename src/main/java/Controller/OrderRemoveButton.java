package Controller;

import Business.MenuItem;
import Business.Order;
import Business.Restaurant;
import Presentation.AdministratorGUI;
import Presentation.EditOrder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Remove from order button functionality
 */
public class OrderRemoveButton implements ActionListener {
    JList currentProductList;
    Order currentOrder;

    public OrderRemoveButton(JList currentProductList, Order currentOrder){
        this.currentProductList=currentProductList;
        this.currentOrder=currentOrder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MenuItem selectedProduct = null;
        if(Restaurant.wellFormed(currentProductList)) {
            int selectedItemPosition = currentProductList.getSelectedIndex();
            String selectedItemName = currentProductList.getSelectedValue().toString();
            selectedProduct = Restaurant.getProductByName(selectedItemName);
            Restaurant.removeFromOrder(currentOrder, selectedProduct);
            EditOrder.currentOrderContentModel.remove(selectedItemPosition);
        }
        else
            JOptionPane.showMessageDialog(null,"Select the item you want to remove from the current list!");
    }
}
