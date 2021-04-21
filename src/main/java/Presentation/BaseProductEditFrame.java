package Presentation;

import Controller.EditProductButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implements the frame for the base product editor
 */
public class BaseProductEditFrame {
    JTextField nameField;
    JTextField priceField;

    public BaseProductEditFrame(String name, float price){
        JFrame frame = new JFrame("Base Product Editor");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JLabel requestedProductName = new JLabel("Product name");
        c.gridx=0;
        c.gridy=0;
        c.fill = GridBagConstraints.CENTER;
        c.gridwidth=1;
        panel.add(requestedProductName,c);
        nameField = new JTextField("");
        nameField.setText(name);
        c.gridx=1;
        c.ipadx=300;
        c.ipady=15;
        c.weightx=0.7;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(nameField,c);
        //////////Price
        JLabel requestedPriceLabel = new JLabel("Price");
        c.gridwidth=1;
        c.ipadx=0;
        c.ipady=0;
        c.gridx=0;
        c.gridy=1;
        c.fill = GridBagConstraints.CENTER;
        panel.add(requestedPriceLabel,c);
        priceField = new JTextField("");
        priceField.setText(""+price);
        c.gridx=1;
        c.ipadx=300;
        c.ipady=15;
        c.weightx=0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(priceField,c);

        JButton edit = new JButton("Modify");
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameField.getText().length()>0 && priceField.getText().length()>0) {
                    if(priceField.getText().matches("-?\\d+(\\.\\d+)?")) {
                        EditProductButton.updateBaseProduct(nameField.getText(), priceField.getText());
                        frame.setVisible(false);
                        frame.dispose();
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Price must be a number!");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Text fields cannot be empty!");
                }
            }
        });
        c.gridx=0;
        c.gridy=2;
        c.gridwidth=2;
        c.anchor=GridBagConstraints.CENTER;
        panel.add(edit,c);
        frame.getRootPane().setDefaultButton(edit);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
