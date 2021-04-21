package Presentation;

import Controller.InsertProductButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implements the frame for product insertion
 */
public class InsertProduct {
    JTextField productNameField;
    JTextField priceField;

    public InsertProduct(){
        JFrame frame = new JFrame("Insert base Product");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel insertPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);
        //////////Product Name
        JLabel productNameLabel = new JLabel("Product name");
        c.gridx=0;
        c.gridy=0;
        c.fill = GridBagConstraints.CENTER;
        insertPanel.add(productNameLabel,c);
        productNameField = new JTextField("",30);
        c.gridx=1;
        c.ipady=15;
        c.weightx=0.7;
        c.fill = GridBagConstraints.HORIZONTAL;
        insertPanel.add(productNameField,c);
        //////////Price
        JLabel priceLabel = new JLabel("Price");
        c.gridwidth=1;
        c.ipadx=0;
        c.ipady=0;
        c.gridx=0;
        c.gridy=1;
        c.fill = GridBagConstraints.CENTER;
        insertPanel.add(priceLabel,c);
        priceField = new JTextField("",30);
        c.gridx=1;
        c.ipadx=0;
        c.ipady=15;
        c.weightx=0.7;
        c.fill = GridBagConstraints.HORIZONTAL;
        insertPanel.add(priceField,c);
        //////////Separator
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        c.ipadx=0;
        c.ipady=0;
        c.gridwidth=2;
        insertPanel.add(new JSeparator(), c);
        //////////Insert button
        JButton insertButton = new JButton("Insert product");
        c.ipadx=0;
        c.ipady=0;
        c.gridx=0;
        c.gridy=3;
        c.ipady=15;
        c.ipadx=30;
        c.fill = GridBagConstraints.CENTER;
        c.anchor = GridBagConstraints.CENTER;
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(productNameField.getText().length()>0 && priceField.getText().length()>0){
                    if(priceField.getText().matches("-?\\d+(\\.\\d+)?")) {
                        InsertProductButton.insert(productNameField.getText(), Float.parseFloat(priceField.getText()));
                        frame.setVisible(false);
                        frame.dispose();
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Price must be a number!");

                }
                else
                    JOptionPane.showMessageDialog(null, "Text fields cannot be empty!");
            }
        });
        insertPanel.add(insertButton,c);
        frame.getRootPane().setDefaultButton(insertButton);

        frame.add(insertPanel);
        frame.pack();
        frame.setVisible(true);
    }

}
