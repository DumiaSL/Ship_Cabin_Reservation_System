
package com.mycompany.corsework;


import java.util.*;


public class Passenger 
{
    private String First_Name;
    private String Surname;
    private double Expenses;
    
    private static final Scanner Scan = new Scanner(System.in);
    
    //Defult Constructor
    public Passenger()
    {
        this.First_Name="Not reserved";
        this.Surname="Not reserved";
        this.Expenses=0;  
    }
    
    public Passenger(String firstName,String surname,double expenses)
    {
        this.First_Name=firstName;
        this.Surname=surname;
        this.Expenses=expenses;  
    }
    
    
    //Setters
    //Set First name input
    protected void setFirstname(int cabin_num,int x)
    {
        System.out.print("Enter cabin "+(cabin_num)+" Passenger "+ (x+1) +" first name :");
        this.First_Name=Scan.next();
    }
    // Set surnname input
    protected void setSurname(int cabin_num,int x)
    {
       System.out.print("Enter cabin "+(cabin_num)+" Passenger "+ (x+1) +" Surname :");
       this.Surname=Scan.next();
    }
    
    protected void SetExpenses(int cabin_num,int x)
    {
        while (true) 
        {
            System.out.print("Enter cabin "+(cabin_num)+" Passenger "+ (x+1) +" Expenses :");
            if (Scan.hasNextDouble()) 
            {
                this.Expenses=Scan.nextDouble();
                break;
            } 
            else 
            {
                System.out.println("Wrong input");
                Scan.next();
            } 
        }
    }
    
    
    // Getters
    //return firstname
    protected String getFirstname()
    {
        return this.First_Name;
    }
    
    //return surname
    protected String getSurname()
    {
        return this.Surname;
    }
    
    //return expenses
    protected double getExpenses()
    {
        return this.Expenses;
    }
    
    
    //data reset
    protected void dataclear_passenger()
    {
        this.Expenses=0;
        this.First_Name="Not reserved";
        this.Surname="Not reserved";
    }
    
    //update passenger in Queue
    protected void dataupdate_passenger(String First_Name,String Surname,double Expenses)
    {
        this.Expenses=Expenses;
        this.First_Name=First_Name;
        this.Surname=Surname;
    }
}
