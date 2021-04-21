package Presentation;

import Business.CompositeProduct;
import Business.Restaurant;
import Controller.CompositeAddButton;
import Controller.CompositeRemoveButton;
import Controller.CreateCompositeButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Presentation.AdministratorGUI.availableProductsModel;

/**
 * Implements the composite creation frame
 */
public class CreateComposite {
    public static DefaultListModel currentItemsModel;
    /**
     * creates a temporary composite product to enable the user to add/remove items, the changes are made permanent once the create button is pressed
     */
    private static CompositeProduct tempProduct; //used when creating a composite product to allow configuring it before adding
    JTextField compositeNameField;

    public CreateComposite(){
        JFrame frame = new JFrame("Create composite");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tempProduct = new CompositeProduct(0, "");
        JPanel createComposite = new JPanel(new BorderLayout());
        JPanel createCompositeSubpanel1 = new JPanel(new GridBagLayout());
        JPanel createCompositeSubpanel2 = new JPanel(new GridBagLayout());
        JPanel createCompositeSubpanel3 = new JPanel(new GridBagLayout());
        JPanel createCompositeSubpanel4 = new JPanel(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();
        c.insets=new Insets(10,10,20,10);
        c.gridwidth=1;
        c.gridheight=1;
        //subpanel1
        ///// Select item label
        JLabel selectItemMessage = new JLabel("Select item to be added");
        c.gridx=0;
        c.gridy=0;
        c.weightx=1;
        c.ipady=30;
        c.fill=GridBagConstraints.CENTER;
        createCompositeSubpanel1.add(selectItemMessage,c);
        c.ipady=0;
        ///// List of available items
        JList availableItemsList = new JList(availableProductsModel);
        JScrollPane scrollableAvailableItemsList = new JScrollPane(availableItemsList);
        scrollableAvailableItemsList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        availableItemsList.setVisibleRowCount(7);
        scrollableAvailableItemsList.setPreferredSize(availableItemsList.getPreferredScrollableViewportSize());
        c.gridy=1;
        c.gridx=0;
        c.weightx=1;
        c.ipadx=0;
        c.ipady=0;
        c.fill=GridBagConstraints.BOTH;
        createCompositeSubpanel1.add(scrollableAvailableItemsList,c);

        //subpanel2
        ///// Choose composite name
        JLabel compositeItemName = new JLabel("Composite Name");
        c.gridx=0;
        c.gridy=0;
        c.fill = GridBagConstraints.CENTER;
        c.gridwidth=1;
        createCompositeSubpanel2.add(compositeItemName,c);
        compositeNameField = new JTextField("",30);
        c.gridx=1;
        c.ipady=15;
        c.weightx=0.7;
        c.fill = GridBagConstraints.HORIZONTAL;
        createCompositeSubpanel2.add(compositeNameField,c);
        ///// Items in current composite
        currentItemsModel = new DefaultListModel();
        JList itemsInComposite = new JList(currentItemsModel);
        for (Business.MenuItem m : tempProduct.getProductList()) {
            currentItemsModel.addElement(m.getName());
        }
        itemsInComposite.setVisibleRowCount(7);
        JScrollPane scrollableItemsInComposite = new JScrollPane(itemsInComposite);
        scrollableItemsInComposite.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableItemsInComposite.setPreferredSize(itemsInComposite.getPreferredScrollableViewportSize());
        c.gridy=1;
        c.gridx=0;
        c.weightx=1;
        c.gridwidth=2;
        c.ipadx=0;
        c.ipady=0;
        c.anchor=GridBagConstraints.CENTER;
        createCompositeSubpanel2.add(scrollableItemsInComposite,c);
        c.gridwidth=1;

        //subpanel3
        ///// +/- buttons
        c.insets=new Insets(5,10,5,10);
        c.gridx=0;
        c.gridy=0;
        c.ipady=30;
        createCompositeSubpanel3.add(new JLabel(""),c);
        JButton addToComposite = new JButton("+");
        addToComposite.addActionListener(new CompositeAddButton(availableItemsList,tempProduct,1));
        c.fill=GridBagConstraints.NONE;
        c.anchor=GridBagConstraints.SOUTH;
        c.gridy=1;
        c.gridheight=1;
        c.ipadx=0;
        c.ipady=15;
        createCompositeSubpanel3.add(addToComposite,c);
        JButton removeFromComposite = new JButton("-");
        removeFromComposite.addActionListener(new CompositeRemoveButton(itemsInComposite, tempProduct,1));
        c.gridy=2;
        c.anchor=GridBagConstraints.NORTH;
        createCompositeSubpanel3.add(removeFromComposite,c);

        //subpanel4
        c.insets=new Insets(10,20,20,10);

        JButton createCompositeButton = new JButton("Create Composite");
        createCompositeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(Restaurant.wellFormed(compositeNameField.getText())) {
                    CreateCompositeButton.create(compositeNameField);
                    frame.setVisible(false);
                    frame.dispose();
                }
                else
                    JOptionPane.showMessageDialog(null,"Name cannot be empty!");
            }
        });
        c.gridx=0;
        c.gridy=0;
        c.anchor=GridBagConstraints.CENTER;
        c.ipady=15;
        c.ipadx=30;
        createCompositeSubpanel4.add(createCompositeButton,c);
        c.ipadx=0;
        c.ipady=0;

        //add to wrapper panel

        createComposite.add(createCompositeSubpanel1,BorderLayout.WEST);

        createComposite.add(createCompositeSubpanel3,BorderLayout.CENTER);

        createComposite.add(createCompositeSubpanel2,BorderLayout.EAST);

        createComposite.add(createCompositeSubpanel4,BorderLayout.SOUTH);
        frame.getRootPane().setDefaultButton(createCompositeButton);
        frame.add(createComposite);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Updates the temporary product name and id
     * @param name
     * @param id
     */
    public static void updateTempProduct(String name, int id){
        tempProduct.setName(name);
        tempProduct.setId(id);
    }

    /**
     * Clears the product, preparing it for the next insertion
     */
    public static void clearTempProduct(){
        tempProduct=new CompositeProduct(0,"");
    }

    public static CompositeProduct getTempProduct(){
        return tempProduct;
    }
}
