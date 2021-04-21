package Presentation;

import Business.Order;
import Business.Restaurant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Implements the All orders panel where all orders can be seen (finished and unfinished orders)
 */
public class AllOrders {

    public AllOrders(){
        JFrame frame = new JFrame("Waiter Panel");
        //hide window when pressing X
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                frame.setVisible(false);
                frame.dispose();
            }
        });

        JPanel tablePanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JLabel tableInfo = new JLabel("All orders");
        c.gridx=0;
        c.gridy=0;
        c.fill=GridBagConstraints.CENTER;
        c.anchor=GridBagConstraints.CENTER;
        tablePanel.add(tableInfo,c);
        Object data[][]=null;
        String col[] = {"Order ID","Table number", "Date","Finished", "Price"};
        DefaultTableModel openOrdersTableModel = new DefaultTableModel(data,col);
        JTable orderTable = new JTable(openOrdersTableModel);
        for(Order o : Restaurant.getOrders()){
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

        JButton close = new JButton("Close");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }
        });
        c.gridx=0;
        c.gridy=2;
        c.ipady=15;
        c.anchor=GridBagConstraints.CENTER;
        tablePanel.add(close,c);


        frame.add(tablePanel);
        frame.pack();
        frame.setVisible(true);
    }
}
