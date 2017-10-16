import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


/*
 * File Name   : FinalProjectShinyeob
 * Author      : Shinyeob Kim
 * Created on  : 10/14/2017
 * Description : Reads crime statistics from a .csv file. 
 */


public class FinalProjectShinyeob {
   
    static String[][] crimeExcel; 
    static long timeElapse = System.currentTimeMillis()/1000; // Keep track of elapsed time (in seconds)
 
    public static void main(String[] args) {
        csvFile("/Users/Justin/Desktop/Crime.csv");  //File location
        mainMenu(); //Main menu
      
    }

    private static void csvFile(String csvFile) 
    {
        FileInputStream in = null;
        BufferedReader inputStream = null;
        crimeExcel = new String[21][20];
        int rowC = 0;
        
        String fileLine;
        try {
            inputStream = new BufferedReader(new FileReader("/Users/Justin/Desktop/Crime.csv")); //locate csv file
            while ((fileLine = inputStream.readLine() ) != null) {
                String[] InArray = fileLine.split(",");
                for (int x = 0; x < InArray.length; x++)
                {
                	crimeExcel[rowC][x] = InArray[x];
                }
                rowC++;
                 
            }
        } catch (IOException io) {
            System.out.println("File IO exception" + io.getMessage());
        } finally {
            // the streams          
            try {
                // Close the streams
                if (in != null) {
                    in.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }              
            } catch (IOException io) {
                System.out.println("Issue closing the Files" + io.getMessage());
            }
        }
    }  
 /*
  * 
  * Population growth in percentages is calculated from each consecutive year.
  */
    public static void getGrowthPercentages() {
        int x = 1;
        int y = 2;
        for (int i = 1; i < crimeExcel.length-1 ; i++) {
            float yrOne = Float.parseFloat(crimeExcel[x][1]);
            float yrTwo = Float.parseFloat(crimeExcel[y][1]);
            float growth = ((yrTwo-yrOne)/yrOne)*100;
            System.out.println(crimeExcel[x][0] + "-" + crimeExcel[y][0] + ": " + growth + "%");
            x++;
            y++;
        }
    }
    /*
     * Years where the maximum Murder rates occurred are calculated. 
     */
    public static String getMaxMurderYear() {
        String year = crimeExcel[1][0];
        int rate1 = Integer.parseInt(crimeExcel[1][4]);
        // determine the larger number and store it
        for (int i = 2; i < crimeExcel.length ; i++) {
            int rate2 = Integer.parseInt(crimeExcel[i][4]);
            if (rate2 > rate1) {
                rate1 = rate2;
                year = crimeExcel[i][0];
            }
        }
        return year;        
          
    }
 /*
  * Years where the minimum Murder rates occurred are calculated. 
  */
    public static String getMinMurderYear() {
        String year = crimeExcel[1][0];
        int rate1 = Integer.parseInt(crimeExcel[1][4]);
        for (int i = 2; i < crimeExcel.length ; i++) {
            int rate2 = Integer.parseInt(crimeExcel[i][4]);
            if (rate2 < rate1) {
                rate1 = rate2;
                year = crimeExcel[i][0];
            }
        }        
        return year;  
    }
 /*
  * Years where the maximum Robbery rates occurred are calculated.
  */
    public static String getMaxRobberyYear() {
        String year = crimeExcel[1][0];
        int rate1 = Integer.parseInt(crimeExcel[1][8]);
        // determine the larger number and store it
        for (int i = 2; i < crimeExcel.length ; i++) {
            int rate2 = Integer.parseInt(crimeExcel[i][8]);
            if (rate2 > rate1) {
                rate1 = rate2;
                year = crimeExcel[i][0];
            }
        }        
        return year;  
    }
 // Years where the minimum Robbery rates occurred are calculated.
    public static String getMinRobberyYear() {
        String year = crimeExcel[1][0];
        int rate1 = Integer.parseInt(crimeExcel[1][8]);
        // determine the lower number and store it
        for (int i = 2; i < crimeExcel.length ; i++) {
            int rate2 = Integer.parseInt(crimeExcel[i][8]);
            if (rate2 < rate1) {
                rate1 = rate2;
                year = crimeExcel[i][0];
            }
        }        
        return year;  
    }
    /*
     * Main Menu.  Shows options.
     */
    public static void mainMenu() {
        while (true) {
            String option = "";
            System.out.println("********** Welcome to the US Crime Statistical Application ************************** \n");
            System.out.println("Enter the number of the question you want answered. Enter 0 to quit the program :\n");
            System.out.println("1. What were the percentages in population growth for each consecutive year from 1994 - 2013?");
            System.out.println("2. What year was the Murder rate the highest?");
            System.out.println("3. What year was the Murder rate the lowest?");
            System.out.println("4. What year was the Robbery rate the highest?");
            System.out.println("5. What year was the Robbery rate the lowest?");
            System.out.println("Q. Quit the program\n");
            System.out.println("Enter your selection:");
            Scanner scan = new Scanner(System.in); //Scans user input
            option = scan.nextLine();
            
            switch (option) {
          
            case "Q": //Quit and shows elapsed time
                System.out.println("Thank you for trying the US Crimes Statistics Program.\n");
             
                long timeElapse1 = System.currentTimeMillis()/1000;
                
                System.out.println("Elapsed time in seconds was: " + (timeElapse1-timeElapse));
                scan.close();
                
                System.exit(0);

            case "1":  //Choice 1 shows growth percentage
                getGrowthPercentages();
                break;
            case "2":  // Choice 2 shows highest murder rate and its year.
                System.out.println("The Murder rate was highest in " + getMaxMurderYear()+ "\n");
                break;
            case "3":  // Choice 3 shows lowest murder rate and its year.
                System.out.println("The Murder rate was lowest in " + getMinMurderYear()+ "\n");
                break;
            case "4":  // Choice 4 shows highest robbery rate and its year.
                System.out.println("The Robbery rate was highest in " + getMaxRobberyYear()+ "\n");      
                break;
            case "5":  // Choice 5 shows lowest robbery rate and its year.
                System.out.println("The Robbery rate was lowest in " + getMinRobberyYear()+ "\n");
                break;
            default:
            	
                
            } //end switch
        } // end while
    }
}

