/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListModel;
import Models.Customer;
import Models.DBManager;
import Models.Order;
import Models.OrderLine;
import Models.Product;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author 30240931 James Rourke
 */
public class ViewProducts extends javax.swing.JFrame {
    
    private Customer loggedInCustomer; 
    private HashMap<Integer, Product> products;
    private Order currentOrder;
    
    /**
     * Creates new form ViewProducts
     */
    public ViewProducts(Customer customer) {
        loggedInCustomer = customer; 
        
       
        
        DBManager db = new DBManager();
        //Loads the Products
        products = db.loadProducts();
        
            
        initComponents();
        
        
        if(!loggedInCustomer.getIsRegistered())
        {
            btnViewBasket.setVisible(false);
            btnBack.setText("Back to MAIN MENU");
        }
        else
        {
            //Finds the Latest Order
            currentOrder = loggedInCustomer.findLatestOrder();
            btnBack.setText("Back to HOMEPAGE");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JButton();
        btnViewBasket = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstProductCategory = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstProducts = new javax.swing.JList<>();
        cboQuantity = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btnAddToBasket = new javax.swing.JButton();
        lblMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnBack.setText("Return to Customer Home");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnViewBasket.setText("View Basket");
        btnViewBasket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewBasketActionPerformed(evt);
            }
        });

        jLabel1.setText("Products");

        jLabel2.setText("Categories");

        jLabel3.setText("Product");

        lstProductCategory.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Clothing", "Footwear" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstProductCategory.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstProductCategoryValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstProductCategory);

        jScrollPane2.setViewportView(lstProducts);

        cboQuantity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        jLabel4.setText("Quantity:");

        btnAddToBasket.setText("Add To Basket");
        btnAddToBasket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToBasketActionPerformed(evt);
            }
        });

        lblMessage.setText("jLabel5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(89, 89, 89))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnViewBasket)))))
                .addGap(42, 42, 42))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(btnAddToBasket))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(lblMessage)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnViewBasket)
                    .addComponent(jLabel1))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(25, 25, 25)
                .addComponent(btnAddToBasket)
                .addGap(18, 18, 18)
                .addComponent(lblMessage)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane1, jScrollPane2});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        
        //Is the User Registered?
        if(loggedInCustomer.getIsRegistered() == false)
        {
            //go to Main Menu Page
            MainMenu mainMenu = new MainMenu();
            this.dispose();
            mainMenu.setVisible(true);
        }
        else
        {
            //go to Customer Home Page
            CustomerHome customerHome = new CustomerHome(loggedInCustomer);
            this.dispose();
            customerHome.setVisible(true);
        }   
    }//GEN-LAST:event_btnBackActionPerformed

    private void lstProductCategoryValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstProductCategoryValueChanged
       String selectedCategory = lstProductCategory.getSelectedValue();
        
       //Creates Model Table
        DefaultListModel productListModel = new DefaultListModel();
        
        //for each loop through HashMap
        for(Map.Entry<Integer, Product> productEntry : products.entrySet())
        {
            Product product = productEntry.getValue();
            
            if (product.getClass().getName().equals("Models." + selectedCategory)) 
            {
                productListModel.addElement(product);
            }
        }
        lstProducts.setModel(productListModel);
    }//GEN-LAST:event_lstProductCategoryValueChanged

    private void btnAddToBasketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToBasketActionPerformed
        
        try
        {
            

            if(this.lstProducts.getSelectedIndex() != -1)
            { // If: lstProducts get selected index != -1

                    // Get the selected product
                    Object selectedObject = (Object)this.lstProducts.getSelectedValue();
                    Product selectedProduct = (Product)selectedObject;

                    // Get the selected quantitiy
                    Object selectedQuantityObject = (Object)this.cboQuantity.getSelectedItem();
                    String selectedQuantityString = (String)selectedQuantityObject;
                    int selectedQuantityInt = Integer.valueOf(selectedQuantityString);

                    // Check to see if the selected product has a quantity greater than
                    // or equal to one. If so, continue.
                    if(selectedProduct.getStockLevel() >= 1)
                    { // If: selected product get stock level >= 1

                        Optional<OrderLine> alreadyInBasket = currentOrder.getProductAlreadyInBasket(selectedProduct.getProductId());

                        if(alreadyInBasket.isPresent())
                        { // If: alreadyInBasket is present

                            OrderLine productAlreadyInBasket = alreadyInBasket.get();

                            //YOUR DESIGN: IF (ProductsStockLevel - OrderLineQuantity) <= QuantityWishToPurchase
                            if((selectedProduct.getStockLevel() - productAlreadyInBasket.getQuantity()) >= selectedQuantityInt)   // this means it is not in basket
                            {         

                                // Amend the orderline with the same product that is being added again
                                productAlreadyInBasket.setQuantity(productAlreadyInBasket.getQuantity() + selectedQuantityInt);

                                // Multiply the cost * quantity you are trying to buy
                                productAlreadyInBasket.setLineTotal(productAlreadyInBasket.getLineTotal() + (selectedProduct.getPrice() * selectedQuantityInt));

                                // Create an instance of the database manager
                                DBManager db = new DBManager();

                                // Update the orderline
                                db.editOrderLine(productAlreadyInBasket);
                                this.currentOrder.calculateOrderTotal();

                                // Display success message
                                JOptionPane.showMessageDialog(null, "Product added to basket!", "Success", JOptionPane.INFORMATION_MESSAGE);

                            } 
                            else
                            {
                                // Display error message
                                JOptionPane.showMessageDialog(null, "You can not buy any more of this product as the stock is not available.", "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        } // End If: alreadyInBasket is present
                        else
                        {

                            // Create instance of OrderLine 'ol'
                            double LineTotal;
                            OrderLine orderLine = new OrderLine(this.currentOrder.generateUniqueOrderLineId(),
                            selectedProduct,
                            selectedQuantityInt);

                            // Add the 'ol' instance of an OrderLine to the current
                            // order.
                            currentOrder.addOrderLine(orderLine);
                            
                            // Display success message
                            JOptionPane.showMessageDialog(null, "Product added to basket!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
            }
        }
        catch(Exception ex)
        {
           String message = ex.getMessage();
        }
        
    }//GEN-LAST:event_btnAddToBasketActionPerformed

    private void btnViewBasketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewBasketActionPerformed
         ViewBasket vb = new ViewBasket(loggedInCustomer);
         this.dispose();
         vb.setVisible(true);
    }//GEN-LAST:event_btnViewBasketActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new ViewProducts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToBasket;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnViewBasket;
    private javax.swing.JComboBox<String> cboQuantity;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JList<String> lstProductCategory;
    private javax.swing.JList<String> lstProducts;
    // End of variables declaration//GEN-END:variables
}
