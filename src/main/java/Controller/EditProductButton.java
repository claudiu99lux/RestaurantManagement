package Controller;

import Business.BaseProduct;
import Business.Restaurant;
import Presentation.AdministratorGUI;
import Presentation.BaseProductEditFrame;
import Presentation.CompositeProductEditFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Edit product button functionality
 */
public class EditProductButton implements ActionListener {
    JTable table;
    private static Business.MenuItem selectedProduct;
    private static int editedItemRow;

    public EditProductButton(JTable table){
        this.table=table;
        selectedProduct=null;
        editedItemRow=0;
    }

    /**
     * Gets selected object and opens editor window depending on object type
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(Restaurant.wellFormed(table)) {
            editedItemRow = table.getSelectedRow();
            int idOfSelection = Integer.parseInt(AdministratorGUI.tableModel.getValueAt(editedItemRow, 0).toString());
            selectedProduct = Restaurant.getProductById(idOfSelection);
            if (selectedProduct instanceof BaseProduct) {
                BaseProductEditFrame baseEdit = new BaseProductEditFrame(selectedProduct.getName(), selectedProduct.computePrice());
            } else {
                CompositeProductEditFrame compositeEdit = new CompositeProductEditFrame(selectedProduct);
            }
        }
        else
            JOptionPane.showMessageDialog(null,"Select the item you want to edit!");
    }

    /**
     * Updates the selected composite product
     * @param newName
     */
    public static void updateCompositeProduct(String newName){
        selectedProduct.setName(newName);
        String[] newData = selectedProduct.prepareTableRecord();
        for(int i=0; i<4; i++){
            AdministratorGUI.tableModel.setValueAt(newData[i],editedItemRow,i);
        }
        AdministratorGUI.availableProductsModel.remove(editedItemRow);
        AdministratorGUI.availableProductsModel.add(editedItemRow,newName);
    }

    /**
     * Updates the selected base product
     * @param newName
     * @param newPrice
     */
    public static void updateBaseProduct(String newName, String newPrice){
        selectedProduct.setName(newName);
        selectedProduct.setPrice(Float.parseFloat(newPrice));
        String[] newData = selectedProduct.prepareTableRecord();
        for(int i=0; i<4; i++){
            AdministratorGUI.tableModel.setValueAt(newData[i],editedItemRow,i);
        }
        AdministratorGUI.availableProductsModel.remove(editedItemRow);
        AdministratorGUI.availableProductsModel.add(editedItemRow,newName);
    }
}
