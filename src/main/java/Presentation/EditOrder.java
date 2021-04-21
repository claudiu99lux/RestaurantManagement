package Presentation;

import Business.Order;
import Business.Restaurant;
import Controller.OrderAddButton;
import Controller.OrderRemoveButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implements Edit order frame
 */
public class EditOrder {
    public static DefaultListModel currentOrderContentModel;
    public EditOrder(Order editedOrder){
        JFrame frame = new JFrame("Edit Order");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel editOrderPanel = new JPanel(new BorderLayout());
        JPanel subpanel1 = new JPanel(new GridBagLayout());
        JPanel subpanel2 = new JPanel(new GridBagLayout());
        JPanel subpanel3 = new JPanel(new GridBagLayout());
        JPanel subpanel4 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(10,10,10,10);
        //subpanel1
        ///// Available products message
        JLabel availableProductsMessage = new JLabel("Available products");
        c.gridx=0;
        c.gridy=0;
        c.fill = GridBagConstraints.CENTER;
        subpanel1.add(availableProductsMessage,c);
        ///// Available products list
        JList availableItems = new JList(AdministratorGUI.availableProductsModel);
        JScrollPane scrollableAvailableItems = new JScrollPane(availableItems);
        scrollableAvailableItems.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        c.gridy=1;
        c.weightx=1;
        c.ipadx=0;
        c.ipady=0;
        c.anchor=GridBagConstraints.CENTER;
        c.fill=GridBagConstraints.BOTH;
        subpanel1.add(scrollableAvailableItems,c);

        //subpanel2
        ///// + button
        JLabel filler = new JLabel("");
        c.gridx=0;
        c.gridy=0;
        subpanel2.add(filler,c);
        JButton addToOrder = new JButton("+");
        addToOrder.addActionListener(new OrderAddButton(availableItems, editedOrder));
        c.gridx=0;
        c.gridwidth=1;
        c.ipady=20;
        c.gridy=1;
        subpanel2.add(addToOrder,c);
        JButton removeFromOrder = new JButton("-");
        c.gridwidth=1;
        c.ipady=20;
        c.gridy=2;
        subpanel2.add(removeFromOrder,c);
        c.ipady=0;

        //subpanel 3
        JLabel currentOrderMessage = new JLabel("Order content");
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=1;
        c.weightx=1;
        c.fill=GridBagConstraints.CENTER;
        subpanel3.add(currentOrderMessage,c);
        ///// List of products in current order
        currentOrderContentModel = new DefaultListModel();
        JList currentOrderProductList = new JList(currentOrderContentModel);
        JScrollPane scrollableOrderProductList = new JScrollPane(currentOrderProductList);
        scrollableOrderProductList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        for(Business.MenuItem m : Restaurant.getOrdersContent().get(editedOrder)){
            currentOrderContentModel.addElement(m.getName());
        }
        c.gridy=1;
        c.gridx=0;
        c.weightx=1;
        c.ipadx=0;
        c.ipady=0;
        c.fill=GridBagConstraints.BOTH;
        subpanel3.add(scrollableOrderProductList,c);
        removeFromOrder.addActionListener(new OrderRemoveButton(currentOrderProductList,editedOrder));

        //subpanel4
        JButton modify = new JButton("Modify");
        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }
        });
        c.gridx=0;
        c.gridy=0;
        c.ipady=15;
        c.anchor=GridBagConstraints.CENTER;
        subpanel4.add(modify,c);

        editOrderPanel.add(subpanel1,BorderLayout.WEST);
        editOrderPanel.add(subpanel2, BorderLayout.CENTER);
        editOrderPanel.add(subpanel3, BorderLayout.EAST);
        editOrderPanel.add(subpanel4, BorderLayout.SOUTH);

        frame.getRootPane().setDefaultButton(modify);
        frame.add(editOrderPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
