package Models;

/*
*@author 30240931 James Rourke
*/
public class User 
{
    //Private Attributes
    private String username; 
    private String password;
    private String firstName;
    private String lastName;
    
    //Getters
    public String getUsername(){return username;}
    public String getPassword(){return password;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    
    //Setters
    public void setUsername(String usernameIn){username=usernameIn;}
    public void setPassword(String passwordIn){password=passwordIn;}
    public void setFirstName(String firstNameIn){firstName=firstNameIn;}
    public void setLastName(String lastNameIn){lastName=lastNameIn;}
    
    //Default constructor
    public User()
    {
       username="";
       password="";
       firstName="";
       lastName="";            
    }
    
    //Overloaded constructor
    public User(String usernameIn, String passwordIn, String firstNameIn, String lastNameIn)
    {
        username=usernameIn;
        password=passwordIn;
        firstName=firstNameIn;
        lastName=lastNameIn;
    }    
}
