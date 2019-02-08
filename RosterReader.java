/**
 *  @Nigel Nahnfeldt
 *  @1.0
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * 
 * @author Nigel Nahnfeldt
 * @Version 1.0
 * Read in from file
 *  Counts how many lines are in file for size of array
 *  Reads in hero one by one
 */

public class RosterReader 
{
    //Decleration of variables.
    private File fileToRead;
    private Scanner fileScanner;
    private Hero currentHero;
    private char[] abc = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        
        /*
         * Constructor method
         */
        public RosterReader(String filename)
        {
            fileToRead = new File(filename);
            try
            {
                fileScanner = new Scanner(fileToRead);
                fileScanner.useDelimiter(",");
            }
            catch(IOException e)
            {
                System.out.println("Error Processing File");
            }
        }
        
        
      /*
       * Method counts lines in a file.
       */
        public int getLineCount()
        {
            int count = 0;
            try
            {
              fileScanner = new Scanner(fileToRead);
              fileScanner.useDelimiter(",");
              while(fileScanner.hasNextLine())
              {
                fileScanner.nextLine();
                count++;
              }

              // Reset your scanner at the end, so when you're done counting the lines
              // the reader goes back to the first line. 
              fileScanner = new Scanner(fileToRead);
              fileScanner.useDelimiter(",");
            }
            catch(Exception e) 
            {
                System.out.println("Fix your code. There's a problem with getLineCount()");
            }
            return count;
        }
        
        /*
         * This method Determines the validity of the data
         */
        public boolean readNextHero()
        { 
          while(fileScanner.hasNextLine())
          {
            String line = fileScanner.nextLine();
            currentHero = new Hero();
            String[] data = line.split(",");
            
            if(data.length != 4) continue; // If all of the data isn't there OR there is too much data, continue on to the next line.
            
            
            if(data[0] != null)
            {
                currentHero.setName(data[0]);
            }
            // Read in the birthdate
            Calendar cal = new Calendar();

              int month = Integer.parseInt(data[1].trim());
              
              if(month > 0 && month <= 12)
              {
                cal.setMonth(month);
              } else continue;
              
              boolean invalid = false;
              String temp = data[2];
              //Take our current string, and check to see if anything in that string is in the alphabet. If it is, data[2] is invalid.
              for(int i = 0; i < 26; i++){
                  for(int j = 0; j < temp.length(); j++){
                      if (temp.charAt(j) == abc[i]){
                          invalid = true;
                      }
                  }
              }
              
              if(invalid == true){
                  continue;
              }
                      
              int day = Integer.parseInt(data[2].trim()); 
              if(day > 0 && day <= 31) 
              {
                cal.setDate(day);
              }
    
            int year = Integer.parseInt(data[3].trim());
            cal.setYear(year);
            currentHero.calendar = cal;
            currentHero = new Hero(data[0].trim(), year, month, day);
            return true;
          }
          return false;
        }
        
        /*
         * Method to return valid heros.
         */
        public Hero getCurrentHero()
        {
            return currentHero;
        }  
     
        /*
         * Method to close the file.
         */
        public void close() throws IOException
        {
           //Closes the file.
           fileScanner.close();
        }

}