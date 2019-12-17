package Models;

/**
 *
 * @author 30240931 James Rourke
 */
public class Staff extends User
{
    //Private attributes
    private String position; 
    private double salary; 
    
    //Getters
    public String getPosition(){return position;}
    public double getSalary(){return salary;}
    
    //Setters
    public void setPosition(String positionIn){position=positionIn;}
    public void setSalary(double salaryIn){salary=salaryIn;}
    
    //Default constructor
    public Staff()
    {
        super();
        position="";
        salary=0;
    }
    
    //Overloaded constructor
    public Staff(String usernameIn, String passwordIn, String firstNameIn, String lastNameIn, String positionIn, double salaryIn)
    {
        super(usernameIn, passwordIn, firstNameIn, lastNameIn);
        position=positionIn;
        salary=salaryIn;
    }
    
    public String greeting()
    {
        String greeting = "<html>Welcome " + this.getFirstName()+ "<br>You are logged in as Staff</html>";
        return greeting; 
    }
}
