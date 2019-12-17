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
public class Footwear extends Product
{
    //Private attribute
    private int size;
    
    //Getters
    public int getSize(){return size;}
    
    //Setters
    public void setSize(int sizeIn){size=sizeIn;}
    
    //Constructors
    public Footwear(String productNameIn, double priceIn, int stockLevelIn, int sizeIn)
    {
        super(productNameIn, priceIn, stockLevelIn);
        size=sizeIn;
    }
    
    public Footwear(int productIdIn, String productNameIn, double priceIn, int stockLevelIn, int sizeIn)
    {
        super(productIdIn, productNameIn, priceIn, stockLevelIn);
        size = sizeIn;
    }
}
