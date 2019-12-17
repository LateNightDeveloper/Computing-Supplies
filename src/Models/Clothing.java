package Models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 30240931 James Rourke
 * 
 */
public class Clothing extends Product 
{
    //Private Attribute
    private String measurement;
    
    //Getters
    public String getMeasurement(){return measurement;}
    
    //Setters
    public void setMeasurement(String measurementIn){measurement=measurementIn;}
    
    //Constructors
    public Clothing(String productNameIn, double priceIn, int stockLevelIn, String measurementIn)
    {
        super(productNameIn, priceIn, stockLevelIn);
        measurement = measurementIn;
    }
    
    public Clothing(int productIdIn, String productNameIn, double priceIn, int stockLevelIn, String measurementIn)
    {
        super(productIdIn, productNameIn, priceIn, stockLevelIn);
        measurement=measurementIn;
    }
    
    
}
