
package com.mycompany.corsework;
import java.util.*;
import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Date;
/**
 *
 * @author Dumidu
 */
public class task_1 
{
    private static final Scanner Scan = new Scanner(System.in);
    
    public static void main(String[] args) 
    {
        String[] hotel = new String[12];

        initialise(hotel);

        while (true)
        {
            String option1;
            String main_loop_input;

            while (true) 
            {
                System.out.println("--------------------------------[ Cruise Ship Menu ]---------------------------------\n"); 
                System.out.println("Enter [V] for Views All cabins");
                System.out.println("Enter [A] for Adds customer to cabin");
                System.out.println("Enter [E] for Display Empty cabins");
                System.out.println("Enter [D] for Delete customer from cabin");
                System.out.println("Enter [F] for Find cabin from customer name");
                System.out.println("Enter [S] for Store program data into file");
                System.out.println("Enter [L] for Load program data from file");
                System.out.println("Enter [O] for View passengers Ordered alphabetically by name");
                System.out.println("Enter [Q] for Quit the Menue \n");
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.print("Select option : ");
                option1 = Scan.next().toUpperCase();
                if ((option1.equals("V")) |(option1.equals("A"))|(option1.equals("E"))|(option1.equals("D"))
                |(option1.equals("F"))|(option1.equals("S"))|(option1.equals("L"))|(option1.equals("O"))
                |(option1.equals("Q"))) 
                {
                        break;
                } 
                else 
                {
                        System.out.println("Sorry! Wrong input");	
                }
            }

            if (option1.equals("V")) 
            {
                views_all_cabins(hotel);
            } 
            else if (option1.equals("A"))
            {
                adds_customer_to_cabin(hotel);
            }
            else if (option1.equals("E"))
            {
                Display_Empty_cabins(hotel);
            }
            else if (option1.equals("D"))
            {
                delete_customer_from_cabin(hotel);
            }
            else if (option1.equals("F"))
            {
                Find_customer_name(hotel);
            }
            else if (option1.equals("S"))
            {
                Store_data_file(hotel);
            }
            else if (option1.equals("L"))
            {
                Load_data_from_file();
            }
            else if (option1.equals("O"))
            {
                hotel=ordered_alphabetically_name(hotel);
            }
            else if (option1.equals("Q"))
            {
                System.exit(0); //Terminate       
            }
            System.out.println();

            while (true) 
            {
                System.out.print("Would you want anothor services?[Yes/No] " );
                main_loop_input = Scan.next().toUpperCase();
                
                if (main_loop_input.equals("YES") ) 
                {
                    break;
                } 
                else if (main_loop_input.equals("NO"))
                {
                    System.exit(0); //Terminate   
                }
                else 
                {
                    System.out.println("Sorry! Wrong input");	
                }
            }
        }
    }

    //Methods

    private static void initialise( String hotelRef[])
    {
        for (int x = 0; x < 12; x++ ) 
        {
            hotelRef[x] = "not yet booked";
        }
        System.out.println( "initilise Done");
    }

    //Views All cabins method
    private static void views_all_cabins(String[] hotel)
            
    {   
        System.out.println("--------------------------------------------------------------------------------------");
        for (int x = 0; x < 12; x++ )
        {
            System.out.println("room " + x +" "+ hotel[x]);	
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    //Adds customer to cabin method
    private static void adds_customer_to_cabin(String[] hotel)
    {
        String roomName;
        int roomNum = 0;

        System.out.println() ;
        System.out.println("Would you like to book cabin of a cruise ship?" ) ;
        while (roomNum!=12)
        {
            //input validate(integer only)
            while (true) 
            {
                System.out.print("Enter room number (0-11) or 12 to stop: " );
                if (Scan.hasNextInt()) 
                {
                    roomNum = Scan.nextInt();
                    if ((roomNum>=0) & (roomNum<12)) 
                    {
                        if ((hotel[roomNum].equals("not yet booked"))) {
                            break;
                        } 
                        else {
                            System.out.println("Sorry! Already booked thisv cabin");
                        }
                    } 
                    else if (roomNum==12) 
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("Sorry! out of range cabin numbers");	
                    }
                } else 
                {
                    System.out.println("Wrong input");
                    Scan.next();
                } 
            }
            if (roomNum!=12) 
            {
                System.out.println("Enter name for room " + roomNum +" : " ) ;
                roomName = Scan.next();//getting room name
                hotel[roomNum] = roomName ;  //booking name to room
                System.out.println("--------------------------------------------------------------------------------------");
                for (int x = 0; x < 12; x++ )
                {
                    System.out.println("room " + x + " occupied by " + hotel[x]);
                }
                System.out.println("--------------------------------------------------------------------------------------");
            }
        }
    }

    //Display Empty cabins method
    private static void Display_Empty_cabins(String[] hotel)
    {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Display Empty cabins are:\n");
        for (int x = 0; x < 12; x++ )
        {
            if (hotel[x].equals("not yet booked"))
            {
                System.out.println("room " + x + " is not yet booked ");
            }
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    //Delete customer from cabin method
    private static void delete_customer_from_cabin(String[] hotel)
    {
        int remove_cus;

        while(true)
        {
            System.out.print("What cabin number want to clear? ");
            System.out.println("--------------------------------------------------------------------------------------");
            if (Scan.hasNextInt()) 
            {
                remove_cus = Scan.nextInt();
                if ((remove_cus>=0) & (remove_cus<=12)) 
                {
                    if (hotel[remove_cus].equals("not yet booked"))
                    {
                        System.out.println("Sorry! Already free cabin ");
                        break;
                    }
                    else
                    {
                        hotel[remove_cus]="not yet booked";
                        System.out.println("Updated");
                        break;
                    }
                }                                           
            } 
            else 
            {
                System.out.println("Wrong input");
                Scan.next();
            }
        }
        System.out.println();
        views_all_cabins(hotel);
    }

    //Find cabin from customer name method
    private static void Find_customer_name(String[] hotel) 
    {
        String find_name;
        int find_results=0;

        System.out.print("What name wish you like to find? ");
        find_name = Scan.next();
        System.out.println("--------------------------------------------------------------------------------------");
        for (int x = 0; x < 12; x++ )
        {
            if (hotel[x].contains(find_name)) 
            {
                System.out.println("room " + x );
                find_results+=1;	
            }
        }
        if (find_results==0) 
        {
            System.out.println("No results found");
        }
        else 
        {
            System.out.println(find_results + " results found");
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    //Store program data into file method
    public static void Store_data_file(String[] hotel) 
    {
        // display time and date
        Date date = new Date();
        
        try 
        {
            FileWriter myWriter = new FileWriter("logs.txt");
            myWriter.write("----------------------------------Cabin Details------------------------------------"+"\n");
            myWriter.write(String.format("Logs updated Date/Time : %tc", date )+"\n\n");
            for (int x = 0; x < 12; x++ )
            {
                myWriter.write("Cabin number \t-   "+String.valueOf(x)+"\tCabin name\t-   "+hotel[x]+"\n");
            }
            myWriter.write("--------------------------------------------------------------------------------------");
            myWriter.close();
            System.out.println("Successfully stored to the file.");
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //Load program data from file method
    public static void Load_data_from_file() 
    {
        try 
        {
            File myObj = new File("logs.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) 
            {
              String data = myReader.nextLine();
              System.out.println(data);
            }
            myReader.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //View passengers Ordered alphabetically by name
    public static String[] ordered_alphabetically_name(String[] hotel) 
    {
        boolean isordered;
        int elements=hotel.length;
        String[] temp=hotel.clone();
        int count=0;
        //Merge room number with name 
        for (int x=0;x<elements;x++)
        {
                hotel[x]=hotel[x]+" room "+x;
        }
        //sorting part
        for(int i=0;i<(elements-1);i++)
        {
            isordered=false;
            for(int j=0;j<(elements-1);j++)
            {
                if ( (hotel[j].compareTo(hotel[j+1])) > 0) 
                {
                    String temp_=hotel[j];
                    hotel[j]=hotel[j+1];
                    hotel[j+1]=temp_;
                    isordered=true;
                }
            }
            //When all sorted loop break
            if (isordered==false)
            {
                    break;
            }
        }
        // print view passengers Ordered alphabetically by name
        System.out.println("--------------------------------------------------------------------------------------");
        for (String element: hotel) 
        {
            if(!(element.contains("not yet booked")))
            {
                System.out.println(element);
                count++;
            }	
        }

        if(count==0)
        {
            System.out.println( "0 results found!");
        }
        System.out.println("--------------------------------------------------------------------------------------");
        hotel=temp;
        return hotel;
    }

}
