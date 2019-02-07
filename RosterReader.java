/**
 *  @Nigel Nahnfeldt
 *  @1.0
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Calendar;

/**
 * 
 * @author Nigel Nahnfeldt
 * @Version 1.0
 */

public class RosterReader 
{
    //Decleration of variables.
    private File fileToRead;

    private Scanner fileScanner;
    
    private Hero currentHero;
    
    // firstly, we only need 1 scanner object
        
        //Constructor Method.
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
        
        
        //Method counts how many lines are within the read file.
        public int getLineCount()
        {
            int count = 0;
            try
            {
              fileScanner.reset();
              while(fileScanner.hasNextLine())
              {
                fileScanner.nextLine();
                count++;
              }

              // Reset your scanner at the end, so when you're done counting the lines
              // the reader goes back to the first line. 
              fileScanner.reset();
            }
            catch(Exception e) 
            {
                System.out.println("Nigel, fix your code. There's a problem with getLineCount()");
            }
            
            return count;
        }
        
        public boolean readNextHero()
        {
          while(fileScanner.hasNextLine())
          {
            //currentHero = new Hero(); // we defined currentHero as a variable above.
            String line = fileScanner.nextLine();
            
            // actually, first we need to validate the data on the line, yeah I was going to say how would we validate?
            String[] data = line.split(",");
            
            if(data.length != 4) continue; // If all of the data isn't there OR there is too much data, continue on to the next line.
            
            if(data[0] != null)
            {
            	currentHero.name = data[0];
            }
            
            // Read in the birthdate
            Calendar cal = Calendar.getInstance();

            try
            {
            	int month = Integer.parseInt(data[1]);
              
              if(month > 0 && month <= 12)
              {
                cal.set(Calendar.MONTH, month);
              } else continue;

              int day = Integer.parseInt(data[2]); 
              if(day > 0 && day <= 31) 
              {
                cal.set(Calendar.DAY_OF_MONTH, day);
              }

              int year = Integer.parseInt(data[3]);
              cal.set(Calendar.YEAR, year);
              
            }
            catch(NumberFormatException e) {
            	continue; // move on to the next line
            }
            
            currentHero.date = cal;
            currentHero = new Hero(data);
            return true;
          }
          return false;
        }
        
        public Hero getCurrentHero()
        {
            return currentHero;
        }  
     
        public void close() throws IOException
        {
           //Closes the file.
           fileScanner.close();
        }

}