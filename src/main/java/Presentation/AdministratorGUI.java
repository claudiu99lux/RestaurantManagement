package Presentation;

import Business.CompositeProduct;
import Business.MenuItem;
import Controller.*;
import Business.Restaurant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * implements Administrator Panel
 */
public class AdministratorGUI {
    JFrame frame;
    public static DefaultTableModel tableModel;
    public static DefaultListModel availableProductsModel;

    public AdministratorGUI(){
        frame = new JFrame("Administrator Panel");
        //hide window when pressing X
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                hide();
                MainMenuGUI.adminHidden=true;
            }
        });

        JPanel wrapperPanel = new JPanel(new BorderLayout(0,15));
        JPanel tablePanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new GridBagLayout());

        availableProductsModel = new DefaultListModel();
        for(MenuItem m: Restaurant.getMenu()){
            availableProductsModel.addElement(m.getName());
        }

        //region <TABLE PANEL>
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);
        JButton back = new JButton("<");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
                MainMenuGUI.adminHidden=true;
            }
        });
        c.gridx=0;
        c.gridy=0;
        c.anchor=GridBagConstraints.WEST;
        c.ipady=10;
        tablePanel.add(back,c);
        c.ipady=0;
        JLabel tableInfo = new JLabel("Available products in restaurant menu");
        c.gridx=0;
        c.gridy=0;
        c.fill=GridBagConstraints.CENTER;
        c.anchor=GridBagConstraints.CENTER;
        tablePanel.add(tableInfo,c);
        Object data[][]=null;
        String col[] = {"id","Product name", "Type", "Price"};
        tableModel = new DefaultTableModel(data,col);
        JTable productTable = new JTable(tableModel);
        for(Business.MenuItem m : Restaurant.getMenu()){
            tableModel.addRow(m.prepareTableRecord());
        }
        JScrollPane scrollableTable = new JScrollPane(productTable);
        scrollableTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableTable.setPreferredSize(new Dimension(productTable.getPreferredSize().width*2,productTable.getRowHeight()*10));
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
        JButton insertBaseProduct = new JButton("Insert new product");
        JButton removeProduct = new JButton("Remove product");
        JButton editProduct = new JButton("Edit product");
        JButton createCompositeProduct = new JButton("Create new composite");
        insertBaseProduct.setPreferredSize(createCompositeProduct.getPreferredSize());
        removeProduct.setPreferredSize(createCompositeProduct.getPreferredSize());
        editProduct.setPreferredSize(createCompositeProduct.getPreferredSize());

        insertBaseProduct.addActionListener(new InsertProductButton());
        removeProduct.addActionListener(new RemoveProductButton(productTable));
        editProduct.addActionListener(new EditProductButton(productTable));
        createCompositeProduct.addActionListener(new CreateCompositeButton());

        c.ipady=15;
        c.ipadx=0;
        c.gridx=0;
        c.gridy=0;
        buttonPanel.add(insertBaseProduct,c);
        c.gridx=1;
        buttonPanel.add(removeProduct,c);
        c.gridx=0;
        c.gridy=1;
        buttonPanel.add(editProduct,c);
        c.gridx=1;
        buttonPanel.add(createCompositeProduct,c);

        //endregion

        wrapperPanel.add(tablePanel, BorderLayout.NORTH);
        wrapperPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(wrapperPanel);
        frame.pack();

    }

    /**
     * Hides the frame
     */
    public void hide(){
        frame.setVisible(false);
    }

    /**
     * Makes the frame visible
     */
    public void show(){
        frame.setVisible(true);
    }

}
