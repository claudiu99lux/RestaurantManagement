package Controller;

import Business.Order;
import Business.Restaurant;
import Presentation.AdministratorGUI;
import Presentation.EditOrder;
import Presentation.WaiterGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Edit order button functionality
 */
public class EditOrderButton implements ActionListener {

    JTable table;

    public EditOrderButton(JTable table){
        this.table = table;
    }

    /**
     * Get selected item and open editing window
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Order selectedOrder=null;
        if(Restaurant.wellFormed(table)) {
            int row = table.getSelectedRow();
            int idOfSelection = Integer.parseInt(WaiterGUI.openOrdersTableModel.getValueAt(row, 0).toString());
            selectedOrder = Restaurant.getOrderById(idOfSelection);
            EditOrder edit = new EditOrder(selectedOrder);
        }
        else
            JOptionPane.showMessageDialog(null,"Select the order you want to edit!");

    }
}

