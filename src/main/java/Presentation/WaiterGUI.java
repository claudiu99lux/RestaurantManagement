package Presentation;

import Business.Order;
import Business.Restaurant;
import Controller.EditOrderButton;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Implements the waiter panel
 */
public class WaiterGUI {
    JFrame frame;
    public static DefaultTableModel openOrdersTableModel;
    public WaiterGUI(){
        frame = new JFrame("Waiter Panel");
        //hide window when pressing X
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                hide();
                MainMenuGUI.waiterHidden=true;
            }
        });

        JPanel wrapperPanel = new JPanel(new BorderLayout(0,15));
        JPanel tablePanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new GridBagLayout());


        //region <TABLE PANEL>
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);
        JButton back = new JButton("<");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
                MainMenuGUI.waiterHidden=true;
            }
        });
        c.gridx=0;
        c.gridy=0;
        c.anchor=GridBagConstraints.WEST;
        c.ipady=10;
        tablePanel.add(back,c);
        c.ipady=0;
        JLabel tableInfo = new JLabel("Open orders");
        c.gridx=0;
        c.gridy=0;
        c.fill=GridBagConstraints.CENTER;
        c.anchor=GridBagConstraints.CENTER;
        tablePanel.add(tableInfo,c);
        Object data[][]=null;
        String col[] = {"Order ID","Table number", "Date","Finished", "Price"};
        openOrdersTableModel = new DefaultTableModel(data,col);
        JTable orderTable = new JTable(openOrdersTableModel);
        for(Order o : Restaurant.getOrders()){
            if(o.getFinished()==0)
                openOrdersTableModel.addRow(o.prepareTableRecord());
        }
        JScrollPane scrollableTable = new JScrollPane(orderTable);
        scrollableTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableTable.setPreferredSize(new Dimension(orderTable.getPreferredSize().width*2,orderTable.getRowHeight()*10));
        c.gridx=0;
        c.gridy=1;
        c.weightx=1;
        c.weighty=1;
        c.fill=GridBagConstraints.BOTH;
        tablePanel.add(scrollableTable,c);
        //endregion

        //region <BUTTON PANEL>
        c=new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);
        JButton createOrder = new JButton("Create new order");
        JButton editOrder = new JButton("Edit selected order");
        JButton computePrice = new JButton("Compute price for selection");
        JButton viewAll = new JButton("View all orders");
        JButton createBill = new JButton("Finish order and generate bill");
        createOrder.setPreferredSize(createBill.getPreferredSize());
        editOrder.setPreferredSize(createBill.getPreferredSize());
        computePrice.setPreferredSize(createBill.getPreferredSize());
        viewAll.setPreferredSize(createBill.getPreferredSize());

        createOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean alreadyExists=false;
                String inputString = JOptionPane.showInputDialog("Table Number:");
                int tableNumber;
                if(inputString.length()>0) {
                    tableNumber = Integer.parseInt(inputString);
                    for(Order o : Restaurant.getOrders()){
                        if(o.getTable()==tableNumber && o.getFinished()==0) {
                            alreadyExists = true;
                            break;
                        }
                    }
                    if(!alreadyExists) {
                        Order o = Restaurant.createOrder(tableNumber);
                        openOrdersTableModel.addRow(o.prepareTableRecord());
                    }
                    else
                        JOptionPane.showMessageDialog(null, "An order for that table is already open");
                }
                else
                    JOptionPane.showMessageDialog(null,"Please enter table number!");

            }
        });

        editOrder.addActionListener(new EditOrderButton(orderTable));

        computePrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order selectedOrder=null;
                int row = orderTable.getSelectedRow();
                if(row!=-1){
                    int selectionId= Integer.parseInt(WaiterGUI.openOrdersTableModel.getValueAt(row,0).toString());
                    for(Order o : Restaurant.getOrders()){
                        if(o.getId()==selectionId){
                            selectedOrder=o;
                            break;
                        }
                    }
                    openOrdersTableModel.setValueAt(""+selectedOrder.computePrice(),row,4);
                }
                else
                    JOptionPane.showMessageDialog(null,"Select the order you want to compute the price for!");
            }
        });
        viewAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllOrders showAll = new AllOrders();
            }
        });

        createBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order selectedOrder=null;
                int row = orderTable.getSelectedRow();
                if(row!=-1){
                    int selectionId= Integer.parseInt(WaiterGUI.openOrdersTableModel.getValueAt(row,0).toString());
                    for(Order o : Restaurant.getOrders()){
                        if(o.getId()==selectionId){
                            selectedOrder=o;
                            break;
                        }
                    }
                    selectedOrder.setFinished(1);
                    openOrdersTableModel.removeRow(row);
                    BillCreator billCreator = new BillCreator(selectedOrder);
                    billCreator.createBill();

                }
                else
                    JOptionPane.showMessageDialog(null,"Select the order you want to compute the price for!");
            }
        });


        c.ipady=15;
        c.ipadx=0;
        c.gridx=0;
        c.gridy=0;
        buttonPanel.add(createOrder,c);
        c.gridx=1;
        buttonPanel.add(editOrder,c);
        c.gridx=0;
        c.gridy=1;
        buttonPanel.add(computePrice,c);
        c.gridx=1;
        buttonPanel.add(viewAll,c);
        c.gridx=0;
        c.gridy=2;
        c.gridwidth=2;
        buttonPanel.add(createBill,c);


        //endregion

        wrapperPanel.add(tablePanel, BorderLayout.NORTH);
        wrapperPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(wrapperPanel);
        frame.pack();
    }

    public void hide(){
        frame.setVisible(false);
    }
    public void show(){
        frame.setVisible(true);
    }

}
