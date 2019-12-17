package Models;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 30240931 James Rourke
 */
public class Customer extends User 
{
    //Private attributes
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String postcode;
    private boolean isRegistered;
    private HashMap<Integer, Order> orders;
    
    //Getters
    public String getAddressLine1(){return addressLine1;}
    public String getAddressLine2(){return addressLine2;}
    public String getTown(){return town;}
    public String getPostcode(){return postcode;}
    public boolean getIsRegistered(){return isRegistered;}
    public HashMap<Integer, Order> getOrders(){return orders;}
    
    //Setters
    public void setAddressLine1(String addressLine1In){addressLine1=addressLine1In;}
    public void setAddressLine2(String addressLine2In){addressLine2=addressLine2In;}
    public void setTown(String townIn){town=townIn;}
    public void setPostcode(String postcodeIn){postcode=postcodeIn;}
    public void setIsRegistered(boolean isRegisteredIn){isRegistered=isRegisteredIn;}
    public void setOrders(HashMap<Integer, Order> ordersIn){orders = ordersIn;}
    
    //Default constructor
    public Customer()
    {
        super();
        addressLine1="";
        addressLine2="";
        town="";
        postcode="";
        isRegistered=false;
        orders = new HashMap();
    }
    
    //Overloaded constructor
    public Customer(String usernameIn, String passwordIn, String firstNameIn, String lastNameIn, String addressLine1In, String addressLine2In, String townIn, String postcodeIn)
    {
        super(usernameIn, passwordIn, firstNameIn, lastNameIn);
        addressLine1=addressLine1In;
        addressLine2=addressLine2In;
        town=townIn;
        postcode=postcodeIn;
        isRegistered=true;
        orders = new HashMap();
        
                
    }
    
    //function for displaying greeting message
    public String greeting()
    {
        //set greeting message to display Welcome with customer name
        String greeting = "<html><center>Welcome, " + this.getFirstName()+ "<br>You are logged in as Customer</center></html>";
        return greeting;    //return greeting
    }
    
    //fuction for generating Unique OrderId
    public int generateUniqueOrderId()
    {
        int orderId = 0;
        
        for(Map.Entry<Integer, Order> orderEntry : orders.entrySet())
        {
            if (orders.containsKey(orderId)) 
            {
                orderId++;
            }  
        }        
        return orderId;
    }
    
    //function that adds Order to a Customer
    public void addOrder(Order o)
    {
        DBManager db = new DBManager();
        int orderId = db.addOrder(o, this.getUsername());
        
        orders.put(orderId, o);
        
        orders.get(orderId).setOrderId(orderId);
        
    }
    
    //fuction that the latest order the customer has created
    public Order findLatestOrder()
    {
        Order lastOrder = new Order();
        if(orders.isEmpty())
        {
            addOrder(lastOrder);
        }
        else
        {
            lastOrder = orders.entrySet().iterator().next().getValue();
            for(Map.Entry<Integer, Order> oEntry : orders.entrySet())
            {
                Order actualOrder = oEntry.getValue();
                if(actualOrder.getOrderDate().after(lastOrder.getOrderDate()))
                {
                    lastOrder = actualOrder;
                }
            }
            
            if(lastOrder.getStatus().equals("Complete"))
            {
                lastOrder = new Order();
                addOrder(lastOrder);
            }
        }
        return lastOrder;  
    }
}

