package Presentation;

import Business.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Implements the main menu panel, where the user can open/close different panels
 */
public class MainMenuGUI {
    AdministratorGUI admin;
    public static boolean adminHidden=true;
    ChefGUI chef;
    public static boolean chefHidden=true;
    WaiterGUI waiter;
    public static boolean waiterHidden=true;

    public MainMenuGUI(){
        // prepare the other frames, initially hidden
        admin = new AdministratorGUI();
        chef= new ChefGUI();
        waiter = new WaiterGUI();
        Restaurant.registerObserver(chef);
        // Main menu frame
        JFrame frame = new JFrame("Restaurant Management System");
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                Restaurant.saveState();
                System.exit(0);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(10,10,10,10);
        //Label
        JLabel welcomeMessage = new JLabel("Welcome!");
        Font f = welcomeMessage.getFont();
        welcomeMessage.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        c.gridx=0;
        c.gridy=0;
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        panel.add(welcomeMessage,c);
        //Separator
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        c.ipady=0;
        panel.add(new JSeparator(), c);
        //Admin button
        final JButton adminFrameOpener = new JButton("Admin Panel");
        c.gridx=0;
        c.gridy=2;
        c.fill= GridBagConstraints.CENTER;
        c.ipadx=40;
        c.ipady=15;
        adminFrameOpener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(adminHidden) {
                    admin.show();
                    adminHidden=false;
                }
                else {
                    admin.hide();
                    adminHidden=true;
                }
            }
        });
        panel.add(adminFrameOpener,c);
        //Waiter button
        JButton waiterFrameOpener = new JButton("Waiter Panel");
        c.gridy=3;
        waiterFrameOpener.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(waiterHidden){
                    waiter.show();
                    waiterHidden=false;
                }
                else{
                    waiter.hide();
                    waiterHidden=true;
                }
            }
        });
        waiterFrameOpener.setPreferredSize(adminFrameOpener.getPreferredSize());
        panel.add(waiterFrameOpener,c);
        //Chef Button
        JButton chefFrameOpener = new JButton("Chef Panel");
        c.gridy=4;
        chefFrameOpener.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(chefHidden){
                    chef.show();
                    chefHidden=false;
                }
                else{
                    chef.hide();
                    chefHidden=true;
                }
            }
        });
        chefFrameOpener.setPreferredSize(adminFrameOpener.getPreferredSize());
        panel.add(chefFrameOpener,c);

        frame.setPreferredSize(new Dimension(adminFrameOpener.getPreferredSize().width*3,adminFrameOpener.getPreferredSize().height*10));
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
