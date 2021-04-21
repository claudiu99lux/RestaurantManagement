package Presentation;

import Business.CompositeProduct;
import Business.Order;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Used in Chef GUI to dinamically generate order boxes for the chef
 */
public class OrderBox {
    private JScrollPane scrollableBox;
    private Order o;
    private ArrayList<Business.MenuItem> content;
    Box box;

    public OrderBox(Order o, ArrayList<Business.MenuItem> content){
        this.o=o;
        this.content=content;
        box = Box.createVerticalBox();
        JLabel orderNumber = new JLabel("Order number: "+o.getId());
        orderNumber.setFont(new Font("Serif", Font.BOLD, 15));
        box.add(orderNumber);
        for(Business.MenuItem m : content){
            JLabel temp = new JLabel(m.getName());
            temp.setFont(new Font("Serif", Font.PLAIN, 13));
            box.add(temp);
            if(m instanceof CompositeProduct){
                for(Business.MenuItem item : ((CompositeProduct)m).getProductList()){
                    JLabel subTemp = new JLabel("   "+item.getName());
                    subTemp.setFont(new Font("Serif", Font.PLAIN, 13));
                    box.add(subTemp);
                }
            }
        }
        scrollableBox = new JScrollPane(box);
        scrollableBox.setPreferredSize(new Dimension(250,250));
    }

    public void updateBox(Order o, ArrayList<Business.MenuItem> content){
        box = Box.createVerticalBox();
        JLabel orderNumber = new JLabel("Order number: "+o.getId());
        orderNumber.setFont(new Font("Serif", Font.BOLD, 15));
        box.add(orderNumber);
        for(Business.MenuItem m : content){
            JLabel temp = new JLabel(m.getName());
            temp.setFont(new Font("Serif", Font.PLAIN, 13));
            box.add(temp);
            if(m instanceof CompositeProduct){
                for(Business.MenuItem item : ((CompositeProduct)m).getProductList()){
                    JLabel subTemp = new JLabel("   "+item.getName());
                    subTemp.setFont(new Font("Serif", Font.PLAIN, 13));
                    box.add(subTemp);
                }
            }
        }
        scrollableBox = new JScrollPane(box);
        scrollableBox.validate();
        scrollableBox.repaint();
    }

    public JScrollPane getScrollableBox() {
        return scrollableBox;
    }

    public Order getO() {
        return o;
    }
}
