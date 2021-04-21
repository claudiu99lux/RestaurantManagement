package Presentation;

import Business.MenuItem;
import Business.Observer;
import Business.Order;
import Business.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Implements the Chef Panel
 */
public class ChefGUI implements Observer {
    JFrame frame;
    JPanel contentPanel;
    JLabel message;
    ArrayList<OrderBox> orderBoxes;
    public ChefGUI(){
        orderBoxes=new ArrayList<OrderBox>();
        frame = new JFrame("Chef Panel");
        //hide window when pressing X
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                hide();
                MainMenuGUI.chefHidden=true;
            }
        });

        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        contentPanel = new JPanel(new GridLayout(0,2));
        JScrollPane scrollPanel = new JScrollPane(contentPanel);
        scrollPanel.setPreferredSize(new Dimension(600,400));
        scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);


        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);
        JButton back = new JButton("<");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
                MainMenuGUI.chefHidden=true;
            }
        });
        c.gridx=0;
        c.gridy=0;
        c.anchor=GridBagConstraints.WEST;
        c.ipady=10;
        wrapperPanel.add(back,c);
        message=new JLabel("");
        message.setFont(new Font("Serif", Font.BOLD, 20));
        message.setForeground(new Color(128, 0, 5));
        c.ipady=0;
        c.gridx=0;
        c.gridy=0;
        c.fill=GridBagConstraints.CENTER;
        c.anchor=GridBagConstraints.CENTER;
        wrapperPanel.add(message,c);

        c.gridy=1;
        wrapperPanel.add(scrollPanel, c);
        frame.add(wrapperPanel);
        frame.pack();

        for(Business.Order o: Restaurant.getOrders()){
            if(o.getFinished()==0){
                update(o);
            }
        }
    }


    public void hide(){
        frame.setVisible(false);
    }
    public void show(){
        frame.setVisible(true);
    }

    @Override
    public void update(Order o) {
        ArrayList<Business.MenuItem> content = Restaurant.getOrdersContent().get(o);
        boolean exists=false;
        OrderBox existingBox=null;
        for(OrderBox box : orderBoxes){
            if(box.getO().getId()==o.getId()){
                exists=true;
                existingBox=box;
                break;
            }
        }
        if(!exists) {
            OrderBox newBox = new OrderBox(o, content);
            orderBoxes.add(newBox);
            contentPanel.add(newBox.getScrollableBox());
            newBox.getScrollableBox().setPreferredSize(new Dimension(200,200));
            contentPanel.revalidate();
        }
        else
            if(o.getFinished()==1){
                contentPanel.remove(existingBox.getScrollableBox());
                contentPanel.validate();
                contentPanel.repaint();
            }
            else {
                contentPanel.remove(existingBox.getScrollableBox());
                existingBox.updateBox(o,content);
                contentPanel.add(existingBox.getScrollableBox());
                existingBox.getScrollableBox().setPreferredSize(new Dimension(200,200));
                contentPanel.validate();
                contentPanel.repaint();
            }
        message.setText("Created/Modified order "+o.getId());
    }
}
