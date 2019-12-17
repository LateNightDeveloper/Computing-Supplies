package Models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 30240931 James Rourke
 */
public class OrderLine 
{
    //Private Attributes
    private int orderLineId;
    private Product product;
    private int quantity;
    private double lineTotal;
    
    //Getters
    public int getOrderLineId(){return orderLineId;}
    public Product getProduct(){return product;}
    public int getQuantity(){return quantity;}
    public double getLineTotal(){return lineTotal;}
    
    //Setters
    public void setOrderLineId(int orderLineIdIn){orderLineId = orderLineIdIn;}
    public void setProduct(Product productIn){product = productIn;}
    public void setQuantity(int quantityIn){quantity = quantityIn;}
    public void setLineTotal(double lineTotalIn){lineTotal = lineTotalIn;}
    
    //Constructor
    public OrderLine(int orderLineIdIn, Product productIn, int quantityIn)
    {
        orderLineId = orderLineIdIn;
        product = productIn;
        quantity = quantityIn;
        lineTotal = product.getPrice() * quantity;   
    }
    
    //Constructor
    public OrderLine(int orderLineIdIn, Product productIn, int quantityIn, double inLineTotal)
    {
        orderLineId = orderLineIdIn;
        product = productIn;
        quantity = quantityIn;
        this.lineTotal= inLineTotal;  
    } 
}
