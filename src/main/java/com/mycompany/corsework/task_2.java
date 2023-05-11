
package com.mycompany.corsework;

import java.util.*; 
import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Date;

public class task_2 
{
    
    // class Scanner class object
    private static final Scanner Scan = new Scanner(System.in);
    
    //Main method
    public static void main(String[] args) 
    { 
        //creating ship cabin array
        cabin[] main_cabin = new cabin[12];
        
        //creting queue
        Queue waiting_queue= new Queue(12);

        
        //give cabin class
        for (int x=0;x<12;x++)
        {
            main_cabin[x]=new cabin();
        }
        
        //initialise cabin
        initialise(main_cabin);
        
        while (true)
        {
            String option1;
            String main_loop_input;
            boolean free_cabins_bool=false;

            while (true) 
            {
                System.out.println("--------------------------------[ Cruise Ship Menu ]---------------------------------\n");         
                System.out.println("Enter [V] for Views All cabins");
                System.out.println("Enter [A] for Adds Passenger to cabin");
                System.out.println("Enter [E] for Display Empty cabins");
                System.out.println("Enter [D] for Delete Passenger from cabin");
                System.out.println("Enter [F] for Find cabin from Passenger name");
                System.out.println("Enter [S] for Store program data into file");
                System.out.println("Enter [L] for Load program data from file");
                System.out.println("Enter [O] for View passengers Ordered alphabetically by name");
                System.out.println("Enter [T] for print the expenses per passenger as well as the total expenses of all passengers");
                System.out.println("Enter [Q] for Quit the Menu \n");
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.print("Select option : ");
                option1 = Scan.next().toUpperCase();
                if ((option1.equals("V")) |(option1.equals("A"))|(option1.equals("E"))|(option1.equals("D"))
                |(option1.equals("F"))|(option1.equals("S"))|(option1.equals("L"))|(option1.equals("O"))
                |(option1.equals("T"))|(option1.equals("Q"))) 
                {
                        break;
                } 
                else 
                {
                        System.out.println("Sorry! Wrong input");	
                }
            }

            //choose menu parts
            if (option1.equals("V")) 
            {
                views_all_cabins(main_cabin);
            } 
            else if (option1.equals("A"))
            {
                free_cabins_bool=adds_customer_to_cabin(main_cabin,waiting_queue);
            }
            else if (option1.equals("E"))
            {
                display_Empty_cabins(main_cabin);
            }
            else if (option1.equals("D"))
            {
                delete_customer_from_cabin(main_cabin,waiting_queue,free_cabins_bool);
            }
            else if (option1.equals("F"))
            {
                Find_customer_name(main_cabin);
            }
            else if (option1.equals("S"))
            {
                Store_data_file(main_cabin);
            }
            else if (option1.equals("L"))
            {
                Load_data_from_file();
            }
            else if (option1.equals("O"))
            {
                ordered_alphabetically_name(main_cabin);
            }
            else if (option1.equals("T"))
            {
                expenses_print(main_cabin);
            }
            else if (option1.equals("Q"))
            {
                System.exit(0); //Terminate
            }
            System.out.println();
           
            // main loop code finishing 
            while (true) 
            {
                System.out.print("Would you want another service?[Yes/No] " );
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
    
    // Methods
    // cabin initialise
    private static void initialise( cabin cabinRef[] ) 
    {
        for (int x = 0; x < 12; x++ )
        { 
            cabinRef[x].setName("not yet booked") ;
            cabinRef[x].setcabin_num(x);  
        } 
        System.out.println( "initilise Done "); 
    } 
    
    // Views All cabins method
    private static void views_all_cabins(cabin[] cabinRef)
    {
        String option;
        int choose_num;
        
        for (int x = 0; x < 12; x++ )
        { 
            System.out.println("cabin " + x +" "+ cabinRef[x].getName());
        }
        
        while (true) 
        {
            System.out.println();
            System.out.println("Would you want to see passangers ?[Yes/No] ");
            option=Scan.next().toUpperCase();
            if (option.equals("YES") ) 
            {
                while (true)
                {
                    System.out.print("Enter cabin number (0-11) or 12 to all passengers: " );
                    if (Scan.hasNextInt()) 
                    {
                        choose_num = Scan.nextInt();
                        if ((choose_num>=0) & (choose_num<=12))
                        {
                            break;
                        } 
                        else 
                        {
                            System.out.println("Sorry! out of range cabin numbers");	
                        }
                    } 
                    else 
                    {
                        System.out.println("Wrong input");
                        Scan.next();
                    } 
                }
                System.out.println("--------------------------------------------------------------------------------------");
                if (choose_num==12)
                {
                    for (int x = 0; x < 12; x++ )
                    { 
                        System.out.println();
                        cabinRef[x].Passger_detils();
                        System.out.println("--------------------------------------------------------------------------------------");
                    }
                }
                else
                {
                    cabinRef[choose_num].Passger_detils();
                    System.out.println("--------------------------------------------------------------------------------------");
                }
                break;
            } 
            else if (option.equals("NO"))
            {
                break;
            }else 
            {
                System.out.println("Sorry! Wrong input");	
            }
        }
    }

    // Adds customer to cabin method
    private static boolean adds_customer_to_cabin(cabin[] cabinRef,Queue waiting_queue)
    {
        int roomNum = 0;
        boolean free_cabins_bool=false;
        System.out.println() ;
        System.out.println("Would you like to book cabin of a cruise ship?" ) ;
        
        // counting free cabins 
        int free_cabins=0;
        for (int x=0;x<12;x++){
            if ((cabinRef[x].getName().equals("not yet booked")))
            {
                free_cabins++;
            }
        }
        while (1<free_cabins ) 
        { 
            while (true)
            {
                System.out.println("Enter cabin number (0-11) or 12 to stop:"); 
                if (Scan.hasNextInt()) 
                {
                    roomNum = Scan.nextInt();
                    if ((roomNum>=0) & (roomNum<12)) 
                    {
                        if ((cabinRef[roomNum].getName().equals("not yet booked"))) {
                            break;
                        } 
                        else {
                            System.out.println("Sorry! Already booked this cabin");
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
                } 
                else 
                {
                    System.out.println("Wrong input");
                    Scan.next();
                } 
            }
      
            if (roomNum!=12) 
            {
                cabinRef[roomNum].book_cabin(roomNum);                
                cabinRef[roomNum].setpassager();
                System.out.println("--------------------------------------------------------------------------------------");
                cabinRef[roomNum].Passger_detils();
                System.out.println("--------------------------------------------------------------------------------------");
                
                System.out.println("Successfully added!" ) ;
            }else
            {
                break;
            }
            free_cabins = 0;
            for (int x=0;x<12;x++)
            {
                if ((cabinRef[x].getName().equals("not yet booked")))
                {
                    free_cabins++;
                }
            }
        }
        if (free_cabins==1)
        {
            System.out.println("Cabins fully booked!" ) ;
            System.out.println("Ship is full. Adding passengers to the waiting list.");
            System.out.println("Enter first name : ");
            String firstName = Scan.next();
            System.out.println("Enter surname : ");
            String surname = Scan.next();
            System.out.println("Enter expenses : ");
            double expenses = Scan.nextDouble();
            waiting_queue.enQueue(new Passenger(firstName,surname,expenses));
            free_cabins_bool=true;
        }
        return free_cabins_bool;
    }

    // Display Empty cabins method
    private static void display_Empty_cabins(cabin[] cabinRef)
    {
        System.out.println("Display Empty cabins are:");
        System.out.println("--------------------------------------------------------------------------------------");
        for (int x = 0; x < 12; x++ )
        {
            if (cabinRef[x].getName().equals("not yet booked"))
            {
                System.out.println("cabin " + x + " is not yet booked ");
            }
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    // Delete customer from cabin method
    private static void delete_customer_from_cabin(cabin[] cabinRef,Queue waiting_queue,boolean free_cabins_bool)
    {
        int remove_cus;
        int remove_pass;
        String option;
        String queue_loop_input;

        while (true) 
        {            
            System.out.print("Enter[C] to clear cabin, Enter [P] to clear Passenger ");
            option=Scan.next().toUpperCase();
            if (option.equals("P")|option.equals("C"))
            {
                break;
            }else
            {
                System.out.println("Sorry! Invalid option");
            } 
        }
        
        if (option.equals("C")) 
        {
            while (true) 
            { 
                System.out.print("What cabin number want to clear? ");
                if (Scan.hasNextInt()) 
                {
                    remove_cus = Scan.nextInt();
                    if ((remove_cus >= 0) & (remove_cus <= 12)) 
                    {
                        if (cabinRef[remove_cus].getName().equals("not yet booked")) 
                        {
                            System.out.println("Sorry! Already free cabin ");
                            break;
                        }
                        else 
                        {
                            cabinRef[remove_cus].dataclear_cabin();
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
        } else 
        {
            while (true) 
            {
                System.out.println("Enter Passenger's cabin number " );
                if (Scan.hasNextInt()) 
                {
                    remove_cus = Scan.nextInt();
                    if ((remove_cus>=0) & (remove_cus<=12)) 
                    {
                        break;
                    } 
                    else 
                    {
                        System.out.println("Sorry! out of range cabin numbers");	
                    }
                } 
                else 
                {
                    System.out.println("Wrong input");
                    Scan.next();
                } 
            } 
            cabinRef[remove_cus].Passger_detils();
            
            while (true) 
            {
                System.out.print("Enter Passenger's  number: " );
                System.out.println("--------------------------------------------------------------------------------------");
                if (Scan.hasNextInt()) 
                {
                    remove_pass = Scan.nextInt();
                    if ((remove_pass>0) & ( remove_pass<=(cabinRef[remove_cus].getguests() ))) 
                    {
                        cabinRef[remove_cus].remove_pass((remove_pass-1));
                        System.out.println("Sucessfully removed passenger "+remove_pass);
                        
                        while (true) 
                        {
                            System.out.print("Would you add passengr from queue [Yes/No] " );
                            queue_loop_input = Scan.next().toUpperCase();
                            if (queue_loop_input.equals("YES") ) 
                            {       
                                    Passenger deQueuedPassenger = null;
                                    if (free_cabins_bool==true)
                                    {
                                         deQueuedPassenger = waiting_queue.deQueue();
                                    }
                                    if(deQueuedPassenger != null)
                                    {
                                        cabinRef[remove_cus].Update_passenger(remove_pass,deQueuedPassenger.getFirstname(),deQueuedPassenger.getSurname(),deQueuedPassenger.getExpenses());
                                        System.out.println("Passenger added to the ship from the waiting list");
                                    }
                                    break;
                            } 
                            else if (queue_loop_input.equals("NO"))
                            {
                                    break;   
                            }
                            else 
                            {
                                    System.out.println("Sorry! Wrong input");	
                            }
                        }
                        
            
                        break;
                    } 
                    else 
                    {
                        System.out.println("Sorry! out of range Passenger numbers");	
                    }
                } 
                else 
                {
                    System.out.println("Wrong input");
                    Scan.next();
                } 
                System.out.println("--------------------------------------------------------------------------------------");
            }
        }
        System.out.println();
    }

    // Find cabin from customer name method
    private static void Find_customer_name(cabin[] cabinRef) 
    {
        String find_name;
        int find_results=0;

        System.out.print("What name wish you like to find? ");
        find_name = Scan.next();
        System.out.println("--------------------------------------------------------------------------------------");
        for (int x = 0; x < 12; x++ )
        {
            if (cabinRef[x].getName().contains(find_name)) 
            {
                System.out.println("cabin " + x + " name");
                find_results+=1;	
            }

            find_results=cabinRef[x].return_find_names(find_name,find_results);
        }
        if (find_results==0) 
        {
            System.out.println("No results found");
        }else 
        {
            System.out.println(find_results + " results found");
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    // Store program data into file method
    public static void Store_data_file(cabin[] cabinRef ) 
    {
        // display time and date
        Date date = new Date();

        try 
        {
            FileWriter myWriter = new FileWriter("logs_task2.txt");
            myWriter.write("---------------Cabin Details----------------"+"\n");
            myWriter.write(String.format("Logs updated Date/Time : %tc", date )+"\n");
          
            for (int x = 0; x < 12; x++ )
            {
               cabinRef[x].store_detils(myWriter);
            }	
            myWriter.write("---------------------------------------------------------------------------\n");
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
            File myObj = new File("logs_task2.txt");
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
    public static void ordered_alphabetically_name(cabin[] cabinRef) 
    {
        boolean isordered;
        
        List<String> temp_list=new ArrayList<String>(); 
        List<String> namelist=new ArrayList<String>(); 

        for (int x = 0; x < 12; x++ )
        {
            temp_list=cabinRef[x].name_list();
            for (String element: temp_list) 
            {
                namelist.add(element);
            }
        }
        int elements=namelist.size();
     
        //sorting part
        for(int i=0;i<(elements-1);i++)
        {
            isordered=false;
            for(int j=0;j<(elements-1);j++)
            {
                if ((namelist.get(j).compareTo(namelist.get(j+1))) > 0) 
                {
                    String temp_=namelist.get(j);
                    namelist.set(j,namelist.get(j+1));
                    namelist.set((j+1),temp_);
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
        for (String element: namelist) 
        {
            System.out.println(element);
        }
        if(elements==0)
        {
            System.out.println( "0 results found!");
        }
    }
    
    // total expenses of all passengers
    public static void expenses_print(cabin[] cabinRef )
    {
        double expenses_total=0;
        for (int x = 0; x < 12; x++ )
        {
            //calculating total prices and printing
            expenses_total+=cabinRef[x].total_expenses();
        }
        //total passsangers print
        System.out.println("Total expenses of all passengers - "+ expenses_total);
    }
}
