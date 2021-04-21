package Controller;

import Business.CompositeProduct;
import Business.MenuItem;
import Business.Restaurant;
import Presentation.AdministratorGUI;
import Presentation.CompositeProductEditFrame;
import Presentation.CreateComposite;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * - button functionality in composite creation/editing
 */
public class CompositeRemoveButton implements ActionListener {
    JList currentList;
    Business.MenuItem compositeProduct;
    int mode;

    public CompositeRemoveButton(JList currentList, MenuItem currentProduct, int mode){
        this.currentList=currentList;
        compositeProduct=currentProduct;
        this.mode=mode;
    }

    /**
     * Gets id of selection, removes it from current composite item list
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        MenuItem selectedProduct = null;
        if(Restaurant.wellFormed(currentList)) {
            int selectedItemPosition = currentList.getSelectedIndex();
            String selectedItemName = currentList.getSelectedValue().toString();
            selectedProduct = Restaurant.getProductByName(selectedItemName);
            ((CompositeProduct) compositeProduct).removeProduct(selectedProduct);
            if (mode == 1)
                CreateComposite.currentItemsModel.remove(selectedItemPosition);
            else
                CompositeProductEditFrame.currentItemsModel.remove(selectedItemPosition);
        }
        else
            JOptionPane.showMessageDialog(null,"Select the item you want to remove from the current list!");
    }
}
