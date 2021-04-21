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
 * + button functionality in composite creation/edit
 */
public class CompositeAddButton implements ActionListener {
    JList availableItemsList;
    Business.MenuItem compositeProduct;
    int mode; //1 = create, 2=edit

    public CompositeAddButton(JList availableItemsList, MenuItem currentProduct, int mode){
        this.availableItemsList=availableItemsList;
        compositeProduct=currentProduct;
        this.mode=mode;
    }

    /**
     * Gets selection from JList, finds the product based on selection id and adds it to current composite items list
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        MenuItem selectedProduct = null;
        if(Restaurant.wellFormed(availableItemsList)) {
            int selectedItemPosition = availableItemsList.getSelectedIndex();
            int idOfSelection = Integer.parseInt(AdministratorGUI.tableModel.getValueAt(selectedItemPosition, 0).toString());
            selectedProduct = Restaurant.getProductById(idOfSelection);
            ((CompositeProduct) compositeProduct).addProduct(selectedProduct);
            if (mode == 1)
                CreateComposite.currentItemsModel.addElement(selectedProduct.getName());
            else
                CompositeProductEditFrame.currentItemsModel.addElement(selectedProduct.getName());
        }
        else
            JOptionPane.showMessageDialog(null, "Select the item you want to add from the available items list!");
    }
}
