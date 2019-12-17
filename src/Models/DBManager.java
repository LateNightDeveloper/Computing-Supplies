/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 30240931 James Rourke
 */
public class DBManager 
{
    //Private Attributes
    //variable which holds the location of the driver jar
    private final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    
    //connection string holding database location
    private final String connectionString = "jdbc:ucanaccess://C:\\Users\\james\\Desktop\\30240931AssessmentOOP\\Assessment30240931\\Data\\ShopDB.accdb";
    
    //Function to load customers from database
    public HashMap<String, Customer>loadCustomers()
    {
        //String - as Primary Key is username
        
        HashMap<String, Customer> customers = new HashMap();
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * From Customers");   //SQL statement to select all from Customers table
            
            while(rs.next())
            {
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String addressLine1 = rs.getString("AddressLine1");
                String addressLine2 = rs.getString("AddressLine2");
                String town = rs.getString("Town");
                String postcode = rs.getString("Postcode");
                
                //String usernameIn, String passwordIn, String firstNameIn, String lastNameIn, String addressLine1In, String addressLine2In, String townIn, String postcodeIn
                Customer customer = new Customer(username, password, firstName, lastName, addressLine1, addressLine2, town, postcode);
                customers.put(username, customer);//insert into HashMap using username as the key
                
            }
            conn.close();   //Close db connection
        }
        catch(Exception ex)
        { 
            String message = ex.getMessage();
        }
        finally
        {
            //function that retrieve customers Orders
            customers = loadCustomerOrders(customers);
            //function that retrieve customers OrderLines
            customers = LoadCustomerOrderLines(customers);
            return customers;
        }
    }
    
    //Function to validate customer login usinbg username and password
    public Customer customerLogin(String username, String password)
    {
        //load customers from database
        HashMap<String, Customer>customers = loadCustomers();
           
        
        //if username is valid
        if(customers.containsKey(username))
        {
            //Store customer
            Customer foundCustomer = customers.get(username);
            
            
            //Validate foundCustomer password is equal to user input
            if(foundCustomer.getPassword().equals(password))
            {
                //send the foundCustomer back to calling code
                return foundCustomer;
            }
            else
            {
                //return null object
                return null;
            }
        }        
        else
        {
            //return null object
         return null;   
        }
    }
    
    public HashMap<String, Staff> loadStaff()
    {
     
        //String - as Primary Key is username
        
        HashMap<String,Staff> allStaff = new HashMap();
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * From Staff");   //SQL statement to select all from Staff table
            
            while(rs.next())
            {
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String position = rs.getString("Position");
                double salary = rs.getDouble("Salary");
                
                //String usernameIn, String passwordIn, String firstNameIn, String lastNameIn, String positionIn, double salaryIn
                
                Staff staff = new Staff(username, password, firstName, lastName, position, salary);
                allStaff.put(username, staff);//insert into HashMap using username as the key
                
            }
            conn.close();   //Close db connection
        }
        catch(Exception ex)
        { 
            String message = ex.getMessage();
        }
        finally
        {
            return allStaff;
        }   
    }
    
    public Staff staffLogin(String username, String password)
    {
        //load Staff from database
        HashMap<String, Staff>allStaff = loadStaff();
        
        //if username is valid
        if(allStaff.containsKey(username))
        {
            //Store staff member
            Staff foundStaff = allStaff.get(username);
            //Validate foundStaff password is equal to user input
            if(foundStaff.getPassword().equals(password))
            {
                //send the foundStaff back to calling code
                return foundStaff;
            }
            else
            {
                return null;
            }
        }        
        else
        {
         return null;   
        }
    }
    
    public HashMap<Integer, Product> loadProducts()
    {
     
        //Integer - as Primary Key is productId
        
        HashMap<Integer, Product> products = new HashMap();
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * From Products");   //SQL statement to select all from Products table
            
            while(rs.next())
            {
                //Get information from database
                int productId = rs.getInt("ProductId");
                String productName = rs.getString("ProductName");
                double price = rs.getDouble("Price");
                int stockLevel = rs.getInt("StockLevel");
                String measurement = rs.getString("Measurement");
                int size = rs.getInt("Size");           
                
                //if measurement is empty
                if (measurement.equals("")) 
                {
                    //create new footwear product
                    Footwear footwear = new Footwear(productId, productName, price, stockLevel, size);
                    products.put(productId, footwear);  //save footwear in products hashmap
                }
                else
                {
                    //int productIdIn, String productNameIn, double priceIn, int stockLevelIn, String measurementIn
                    Clothing clothing = new Clothing(productId, productName, price, stockLevel, measurement);
                    products.put(productId, clothing);
                }    
            }
            conn.close();   //Close db connection
        }
        catch(Exception ex)
        { 
            String message = ex.getMessage();
        }
        finally
        {
            return products;
        }   
    }
    
    //method to register new customer and save in database
    public boolean registerCustomer(Customer customer)
    {
        try
        {
           Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            
            //Insert the information into the database
            stmt.executeUpdate("INSERT INTO Customers(Username, Password, FirstName, LastName, AddressLine1, AddressLine2, Town, Postcode) " + "VALUES('" + customer.getUsername()+ "', '" + customer.getPassword() + "', '" + customer.getFirstName() + "', '" + customer.getLastName() + "', '" + customer.getAddressLine1() + "', '" + customer.getAddressLine2() + "', '" + customer.getTown() + "', '" + customer.getPostcode() + "')");
            conn.close(); //close db connection
            return true; 
        }
        catch(Exception ex)
        {
            String message = ex.getMessage();
            return false;
        }
    }
    
    //method for adding products to the database    
    public void addProduct(Product product)
    {
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            
            String measurement = "";
            String size = "NULL";
            
            //if statment that find if the selected Product is in the clothing class or footwear class 
            if (product.getClass().getName().equals("Models.Clothing")) 
            {
                Clothing clothing = (Clothing)product;
                measurement = clothing.getMeasurement();
            }
            else
            {
                Footwear footwear = (Footwear)product;
                size = String.valueOf(footwear.getSize());
            }
            
            //Insert the information into the database
            stmt.executeUpdate("INSERT INTO Products(ProductName, Price, StockLevel, Measurement, Size) " + "VALUES('" + product.getProductName() + "', '" + product.getPrice() + "', '" + product.getStockLevel() + "', '" + measurement + "', " + size + ")");
            conn.close(); //close db connection
        }
        catch(Exception ex)
        {
            String message = ex.getMessage();
        }
    }
    
    //method for editing products in the database    
    public void editProduct(Product product)
    {
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            
            String measurement = "";
            String size = "NULL";
            
            //if statment that find if the selected Product is in the clothing class or footwear class 
            if (product.getClass().getName().equals("Models.Clothing")) 
            {
                Clothing clothing = (Clothing)product;
                measurement = clothing.getMeasurement();
            }
            else
            {
                Footwear footwear = (Footwear)product;
                size = String.valueOf(footwear.getSize());
            }
            
            //Update the information into the database
            stmt.executeUpdate("UPDATE Products SET ProductName = '" + product.getProductName() + "', Price = '" + product.getPrice() + "', StockLevel = '" + product.getStockLevel() + "', Measurement = '" + measurement + "', Size = " + size + " WHERE ProductId = '" + product.getProductId() + "'");
            conn.close(); //close db connection
        }
        catch(Exception ex)
        {
            String message = ex.getMessage();
        }
    }
    
    
    public void editCustomer(Customer customer)
    {
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            
            stmt.executeUpdate("UPDATE Customers SET Password = '" + customer.getPassword() + "', FirstName = '" + customer.getFirstName() + "', LastName = '" + customer.getLastName() + "', AddressLine1 = '" + customer.getAddressLine1() + "', AddressLine2 = '" + customer.getAddressLine2() + "', Town = '" + customer.getTown() + "', Postcode = '" + customer.getPostcode() + "' WHERE Username = '" + customer.getUsername() + "'");
            
            //Insert the information into the database
            stmt.executeUpdate("INSERT INTO Customers(Username, Password, FirstName, LastName, AddressLine1, AddressLine2, Town, Postcode) " + "VALUES('" + customer.getUsername() + "', '" + customer.getPassword() + "', '" + customer.getFirstName() + "', '" + customer.getLastName() + "', '" + customer.getAddressLine1() + "', '" + customer.getAddressLine2() + "', '" + customer.getTown() + "', '" + customer.getPostcode() + "')");
            conn.close(); //close db connection
        }
        catch(Exception ex)
        {
            String message = ex.getMessage();            
        }
    }
    
    public void deleteProduct(Product product)
    {
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            
            //Delete product from database
            stmt.executeUpdate("DELETE FROM Products WHERE ProductID = '" + product.getProductId() + "'");
            conn.close();   //close db connection
        }
        catch(Exception ex)
        {
            String message = ex.getMessage();
        }
    }
    
    public void unregisterCustomer(Customer customer)
    {
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            
            //Delete customer from database
            stmt.executeUpdate("DELETE FROM Customers WHERE Username = '" + customer.getUsername() + "'");
            conn.close();   //close db connection
        }
        catch(Exception ex)
        {
            String message = ex.getMessage();
        }
    }
    
    //method to add an order line
    public int addOrderLine(OrderLine ol, int orderId)
    {
        int orderLineId = 0;
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            
            //Insert the information into the database
            stmt.executeUpdate("INSERT INTO OrderLines(ProductId, Quantity, LineTotal, OrderId) " + "VALUES('" + ol.getProduct().getProductId() + "', '" + ol.getQuantity() + "', '" + ol.getLineTotal() + "', '" + orderId + "')");
            
            ResultSet rs = stmt.getGeneratedKeys();
            
            if (rs.next()) 
            {
                orderLineId = rs.getInt(1);
            }            
            conn.close(); //close db connection
        }
        catch(Exception ex)
        {
            String message = ex.getMessage();
        }
        return orderLineId;
    }
    
     public void editOrderLine(OrderLine orderline)
    {
        
        // Exception handling
        try
        {
            
            // Database connectivity, statment and resultsets
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            
            // SQL update query
            stmt.executeUpdate("UPDATE OrderLines SET Quantity = '" + orderline.getQuantity() + 
                    "', LineTotal = '" + orderline.getLineTotal() + "' WHERE OrderLineId = '" + orderline.getOrderLineId()+ "'");
                         
            
            // Close the database connection
            conn.close();
            
           //updateOrderTotal(order.getOrderId(), order.getOrderTotal());
           
        }
        catch(Exception ex)
        {
            
            // Store the exception message in local String variable
            String message = ex.getMessage();
        
        }
    }
    
    
    //method to add an order
    public int addOrder(Order o, String customerUsername)
    {
        int orderId = 0;
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            
            //Insert the information into the database
            stmt.executeUpdate("INSERT INTO Orders(OrderDate, Username, OrderTotal, Status) " + "VALUES('" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(o.getOrderDate()) + "', '" + customerUsername + "', '" + o.getOrderTotal() + "', '" + o.getStatus() + "')");
            
            ResultSet rs = stmt.getGeneratedKeys();
            
            if (rs.next()) 
            {
                orderId = rs.getInt(1);
            }            
            conn.close(); //close db connection
        }
        catch(Exception ex)
        {
            String message = ex.getMessage();
        }
        return orderId;
    }
    
    public void updateOrderTotal(int orderId, double newTotal)
    {
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            
            //Updates OrderTotal information on a order ticket 
            stmt.executeUpdate("UPDATE Orders SET OrderTotal = '" + newTotal + "' WHERE OrderId = '" + orderId + "'");
            
            conn.close(); //close db connection
        }
        catch(Exception ex)
        {
            String message = ex.getMessage();            
        }        
    }
     public void completeOrder(int orderId)
    {
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            
            //Updates Status information on a order ticket 
            stmt.executeUpdate("UPDATE Orders SET Status = 'Complete' WHERE OrderId = '" + orderId + "'");
                                                                        
            conn.close();
        }
        catch(Exception ex)
        {
            String message = ex.getMessage();
        }
    }
     
     public void deleteOrderLine(int orderLineId)
    {
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            
            //Deletes information related to a orderLine
            stmt.executeUpdate("DELETE FROM OrderLines WHERE OrderLineId = '" + orderLineId + "'");
            conn.close();
        }
        catch(Exception ex)
        {
            String message = ex.getMessage();
        }   
    }
    
    public HashMap<String, Customer> loadCustomerOrders(HashMap<String, Customer> customers)
    {
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            
            //Reads Orders data from the database 
            ResultSet rs = stmt.executeQuery("SELECT * FROM Orders");
            while(rs.next())
            {
                int orderId = rs.getInt("OrderId");
                Date orderDate = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("OrderDate"));
                String customer = rs.getString("Username");
                double orderTotal = rs.getDouble("OrderTotal");
                String status = rs.getString("Status");
                
                //checks to see if the current customerOrder matches the customer
                Customer customerWithOrder = customers.get(customer);
                //creates that Order with the Order Class Constructor
                Order loadedOrder = new Order(orderId, orderDate, customerWithOrder, orderTotal, status);
                //Inserts the orders into the customers account
                customerWithOrder.getOrders().put(orderId, loadedOrder);
            }
        }
        catch(Exception ex)
        {
            String message = ex.getMessage();
        }
        finally
        {
            return customers;
        }   
    }
    
    
    public HashMap<String, Customer> LoadCustomerOrderLines(HashMap<String, Customer> customers)
    {
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM OrderLines");
            while(rs.next())
            {
                int orderLineId = rs.getInt("OrderLineId");
                int productId = rs.getInt("ProductID");
                int quantity = rs.getInt("Quantity");
                double lineTotal = rs.getDouble("LineTotal");
                int orderId = rs.getInt("OrderId");
                
                for(Map.Entry<String, Customer> customer : customers.entrySet())
                {
                    Customer actualCustomer = customer.getValue();
                    if(actualCustomer.getOrders().containsKey(orderId))
                    {
                        Order orderForOrderLine = actualCustomer.getOrders().get(orderId);
                        
                        Product productOrdered = loadProducts().get(productId);
                        OrderLine loadedOrderLine = new OrderLine(orderLineId, productOrdered, quantity, lineTotal);
                        
                        orderForOrderLine.getOrderLines().put(orderLineId, loadedOrderLine);
                    }
                }
            }     
        }
        catch(Exception ex)
        {
            String message = ex.getMessage();
        }
        finally
        {
            return customers;
        }
    }
    
    //db.updateStockLevel(orderedProduct.getProductId(), actualOrderLine.getQuantity());
    public void updateStockLevel(Product product, int quantity)
    {
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();

            
            int newStockLevel = product.getStockLevel() - quantity;
            stmt.executeUpdate("UPDATE Products SET StockLevel = '" + newStockLevel + "' WHERE ProductId = '" + product.getProductId() + "'");
                                                                        
            conn.close();
        }
        catch(Exception ex)
        {
            String message = ex.getMessage();
        }
    }
}
    
     
