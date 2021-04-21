package Presentation;

import Business.CompositeProduct;
import Business.MenuItem;
import Controller.CompositeAddButton;
import Controller.CompositeRemoveButton;
import Controller.EditProductButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implements the frame for editing composite products
 */
public class CompositeProductEditFrame {
    JTextField nameField;
    public static DefaultListModel currentItemsModel;

    public CompositeProductEditFrame(MenuItem product){
        JFrame frame = new JFrame("Composite Product Editor");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //################### Create composite ###################
        JPanel editComposite = new JPanel(new GridBagLayout());
        JPanel editCompositeSubpanel1 = new JPanel(new GridBagLayout());
        JPanel editCompositeSubpanel2 = new JPanel(new GridBagLayout());
        JPanel editCompositeSubpanel3 = new JPanel(new GridBagLayout());
        JPanel editCompositeSubpanel4 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets=new Insets(10,20,20,10);
        c.gridwidth=1;
        c.gridheight=1;
        //subpanel1
        ///// Select item label
        JLabel selectItemMessage = new JLabel("Available items");
        c.gridx=0;
        c.gridy=0;
        c.weightx=1;
        c.ipady=30;
        c.fill=GridBagConstraints.CENTER;
        editCompositeSubpanel1.add(selectItemMessage,c);
        c.ipady=0;
        ///// List of available items
        JList availableItemsList = new JList(AdministratorGUI.availableProductsModel);
        JScrollPane scrollableAvailableItemsList = new JScrollPane(availableItemsList);
        scrollableAvailableItemsList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        availableItemsList.setVisibleRowCount(7);
        c.gridy=1;
        c.gridx=0;
        c.weightx=1;
        c.ipadx=0;
        c.ipady=0;
        c.fill=GridBagConstraints.BOTH;
        editCompositeSubpanel1.add(scrollableAvailableItemsList,c);

        //subpanel2
        ///// Edit composite name
        JLabel compositeItemName = new JLabel("Composite Name");
        c.gridx=0;
        c.gridy=0;
        c.fill = GridBagConstraints.CENTER;
        c.gridwidth=1;
        editCompositeSubpanel2.add(compositeItemName,c);
        nameField = new JTextField("",30);
        nameField.setText(product.getName());
        c.gridx=1;
        c.ipadx=300;
        c.ipady=15;
        c.weightx=0.7;
        c.fill = GridBagConstraints.HORIZONTAL;
        editCompositeSubpanel2.add(nameField,c);
        ///// Items in current composite
        currentItemsModel = new DefaultListModel();
        JList itemsInComposite = new JList(currentItemsModel);
        for (Business.MenuItem m : ((CompositeProduct)product).getProductList()) {
            currentItemsModel.addElement(m.getName());
        }
        itemsInComposite.setVisibleRowCount(7);
        JScrollPane scrollableItemsInComposite = new JScrollPane(itemsInComposite);
        scrollableItemsInComposite.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        c.gridy=1;
        c.gridx=0;
        c.weightx=1;
        c.gridwidth=2;
        c.ipadx=0;
        c.ipady=0;
        c.anchor=GridBagConstraints.CENTER;
        editCompositeSubpanel2.add(scrollableItemsInComposite,c);
        c.gridwidth=1;

        //subpanel3
        ///// +/- buttons
        c.insets=new Insets(5,10,5,10);
        c.gridx=0;
        c.gridy=0;
        c.ipady=30;
        editCompositeSubpanel3.add(new JLabel(""),c);
        JButton addToComposite = new JButton("+");
        addToComposite.addActionListener(new CompositeAddButton(availableItemsList, product, 2));
        c.fill=GridBagConstraints.NONE;
        c.anchor=GridBagConstraints.SOUTH;
        c.gridy=1;
        c.gridheight=1;
        c.ipadx=0;
        c.ipady=15;
        editCompositeSubpanel3.add(addToComposite,c);
        JButton removeFromComposite = new JButton("-");
        removeFromComposite.addActionListener(new CompositeRemoveButton(itemsInComposite, product,2));
        c.gridy=2;
        c.anchor=GridBagConstraints.NORTH;
        editCompositeSubpanel3.add(removeFromComposite,c);

        //subpanel4
        c.insets=new Insets(10,20,20,10);

        JButton edit = new JButton("Edit Composite");
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameField.getText().length()>0) {
                    EditProductButton.updateCompositeProduct(nameField.getText());
                    frame.setVisible(false);
                    frame.dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, "Name cannot be empty!");
            }
        });
        c.gridx=0;
        c.gridy=0;
        c.anchor=GridBagConstraints.CENTER;
        c.ipady=15;
        c.ipadx=30;
        editCompositeSubpanel4.add(edit,c);
        c.ipadx=0;
        c.ipady=0;

        //add to wrapper panel
        c.insets=new Insets(10,10,10,10);
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=1;
        c.gridheight=2;
        editComposite.add(editCompositeSubpanel1,c);

        c.gridx=1;
        c.gridy=1;
        c.gridheight=1;
        editComposite.add(editCompositeSubpanel3,c);

        c.ipadx=0;
        c.ipady=0;
        c.gridx=2;
        c.gridy=0;
        c.gridheight=2;
        editComposite.add(editCompositeSubpanel2,c);

        c.gridx=0;
        c.gridy=2;
        c.gridwidth=3;
        editComposite.add(editCompositeSubpanel4,c);

        frame.getRootPane().setDefaultButton(edit);
        frame.add(editComposite);
        frame.pack();
        frame.setVisible(true);
    }
}
