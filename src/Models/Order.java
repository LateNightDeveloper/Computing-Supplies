package Models;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 30240931 James Rourke
 */
public class Order 
{
    //Private Attributes
    private int orderId;
    private Date orderDate;
    private double orderTotal;
    private String status;
    private HashMap<Integer, OrderLine> orderLines;
    
    //Getters
    public int getOrderId(){return orderId;}
    public Date getOrderDate(){return orderDate;}
    public double getOrderTotal(){return orderTotal;}
    public String getStatus(){return status;}
    public HashMap<Integer, OrderLine> getOrderLines(){return orderLines;}
    
    //Setters
    public void setOrderId(int orderIdIn){orderId = orderIdIn;}
    public void setOrderDate(Date orderDateIn){orderDate = orderDateIn;}
    public void setOrderTotal(double orderTotalIn){orderTotal = orderTotalIn;}
    public void setStatus(String statusIn){status = statusIn;}
    public void setOrderLines(HashMap<Integer, OrderLine> orderLinesIn){orderLines = orderLinesIn;}
    
    //Constructors
    public Order()
    {
        orderId=0;
        orderDate = new Date();
        orderTotal=0;
        status = "In Progress";
        orderLines = new HashMap();    
    }
    
    public Order(int orderIdIn, Date orderDateIn, Customer customer ,double orderTotalIn, String statusIn)
    {
        orderId= orderIdIn;
        orderDate = orderDateIn;
        orderTotal = orderTotalIn;
        status = statusIn;        
        orderLines = new HashMap();
    }
    
    public int generateUniqueOrderLineId()
    {
        int orderLineId = 0;
        
        for(Map.Entry<Integer, OrderLine> orderLineEntry : orderLines.entrySet())
        {
            if (orderLines.containsKey(orderLineId)) 
            {
                orderLineId++;
            }  
        }        
        return orderLineId;
    }
    
        public Optional<OrderLine> getProductAlreadyInBasket(int productId)
    {
        Optional<OrderLine> foundProduct = Optional.empty();
        for(Map.Entry<Integer, OrderLine> olEntry : this.orderLines.entrySet())
        {
            OrderLine actualOrderLine = olEntry.getValue();
            Product product = actualOrderLine.getProduct();
            
            if(product.getProductId() == productId)
            {
                foundProduct = Optional.of(actualOrderLine);
            }
        }
        return foundProduct;
    }
    
    public void addOrderLine(OrderLine ol)
    {
        DBManager db = new DBManager();
        int orderLineId = db.addOrderLine(ol, orderId);
        
        orderLines.put(orderLineId, ol);
        
        orderLines.get(orderLineId).setOrderLineId(orderLineId);
        
        calculateOrderTotal();
        
    }
    
    public void calculateOrderTotal()
    {
        orderTotal = 0;
        
        for(Map.Entry<Integer, OrderLine> orderLineEntry : orderLines.entrySet())
        {
            OrderLine ol =  orderLineEntry.getValue();
            orderTotal  = orderTotal + ol.getLineTotal();
        }
        
        DBManager db = new DBManager();
        db.updateOrderTotal(orderId, orderTotal);
    }
    
    public void removeOrderLine(int productId)
    {
        Iterator<Map.Entry<Integer, OrderLine>> iter = orderLines.entrySet().iterator();
        while(iter.hasNext())
        {
            OrderLine actualOrderLine = iter.next().getValue();
            if(actualOrderLine.getProduct().getProductId() == productId)
            {
                int orderLineId = actualOrderLine.getOrderLineId();
                DBManager db = new DBManager();
                db.deleteOrderLine(orderLineId);
                
                iter.remove();
                
                orderTotal = orderTotal - actualOrderLine.getLineTotal();
                db.updateOrderTotal(orderId, orderTotal);
            }
        }
    }
    public void changeStockLevel()
    {
        for(Map.Entry<Integer, OrderLine> olEntry : orderLines.entrySet())
        {
            OrderLine actualOrderLine = olEntry.getValue();
            
            Product orderedProduct = actualOrderLine.getProduct();
            
            DBManager db = new DBManager();
            

            db.updateStockLevel(orderedProduct, actualOrderLine.getQuantity());
        }
    }
}
