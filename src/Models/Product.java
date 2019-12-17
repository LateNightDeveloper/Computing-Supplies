package Models;

/*
*@author 30240931 James Rourke
*/
public class Product 
{
    //private attributes
    private int productId;
    private String productName;
    private double price;
    private int stockLevel;
    
    //override toString method to format information display
    @Override
    public String toString()
    {
        String display = productName + " | Price:  £" + price;
        return display; 
    }
    
    //getters
    public int getProductId(){return productId;}
    public String getProductName(){return productName;}
    public double getPrice(){return price;}
    public int getStockLevel(){return stockLevel;}
    
    //setters
    public void setProductId(int productIdIn){productId=productIdIn;}
    public void setProductName(String productNameIn){productName=productNameIn;}
    public void setPrice(double priceIn){price=priceIn;}
    public void setStockLevel(int stockLevelIn){stockLevel=stockLevelIn;}
    
    //default constructor
    public Product()
    {
       productId=0;
       productName="";
       price=0;
       stockLevel=0;
    }
    
    //overloaded constructor without productId parameter
    public Product(String productNameIn, double priceIn, int stockLevelIn)
    {
       productId=0;
       productName=productNameIn;
       price=priceIn;
       stockLevel=stockLevelIn;
    }
    
    //overloaded constructor with all parameters
    public Product(int productIdIn, String productNameIn, double priceIn, int stockLevelIn)
    {
       productId=productIdIn;
       productName=productNameIn;
       price=priceIn;
       stockLevel=stockLevelIn;
    }
}
