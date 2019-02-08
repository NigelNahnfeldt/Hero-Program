/**
 *  The Main Application that allows a user to Select a file to read from, display the file,
 *  search the file by specified date, then save a new file
 * 
 *  @Nigel Nahnfeldt
 *  @1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.io.PrintWriter;


public class Driver extends JFrame
{
    //Decleration of Variables
    private JButton fileSearchButton, displayButton, parseButton, searchButton;
    private boolean displayedFlag = false;
    File file = null;
    Hero[] heroes;
    
    //Main Method
    public static void main()
    {
        Driver mainFrame = new Driver();
        mainFrame.setSize(350,90);
        mainFrame.setTitle("Hero Searcher");
        
        //Call to .creatGUI method.
        mainFrame.createGUI();
        mainFrame.setVisible(true);
    }
    
    public void createGUI()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        
        //Creates a fileSearchButton, adds an action listener, adds it to the application window.
        fileSearchButton = new JButton("Select a File");
        fileSearchButton.addActionListener(new fileSearchButtonAction());  //Works/I understand
        window.add(fileSearchButton);
        
        //Creates a displayButton, adds an action listener, adds it to the application window.
        displayButton = new JButton("Process & Display");
        displayButton.addActionListener(new displayButtonAction());
        window.add(displayButton);
        
        //Creates a searchButton, adds an action listener, adds it to the application window.
        searchButton = new JButton("Search & Save");
        searchButton.addActionListener(new searchButtonAction());
        window.add(searchButton);
        
        
    }
    
    /*
     * Action Listener for fileSearchButton
     */
    private class fileSearchButtonAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
               //Uses the JFileChooser class to implemete a GUI file selection.
               String userHome = System.getProperty("user.home");
               userHome += "/Desktop";
               JFileChooser fileSelector = new JFileChooser(userHome);
               int returnFlag = fileSelector.showDialog(Driver.this, "Select input file");
               if (returnFlag == JFileChooser.APPROVE_OPTION) 
               {
                   //Sets file to the selected file path.
                   file = fileSelector.getSelectedFile();
               }
               else 
               {
                   //Displays message if cancel was hit on the JFileChooser window.
                   JOptionPane.showMessageDialog(null,"No input file was selected",
                        "File Warning",JOptionPane.WARNING_MESSAGE);
               }
        }  
    }
    
    /*
     *  displayButtons action listener
     */
    private class displayButtonAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            //Sets a check variable to true.
            displayedFlag = true;
            
            // Creates a new RosterReader passing it file and getting it's string path.
            RosterReader rosterReader = new RosterReader(file.getPath());
            
            //Reads how many lines in a file and returns it to heroCount.
            int heroCount = rosterReader.getLineCount();
            
            //Creates a new array of heros with heroCounts value: 17
            heroes = new Hero[heroCount];

            int index = 0;
            while (rosterReader.readNextHero())
            {
                heroes[index] = rosterReader.getCurrentHero(); // Saves off hero.
                index++; // incrememnt hero count
            }

            String heroList = ""; 
            for(int i = 0; i < heroCount; i++)
            {
                //Creates a new string to display ignoring null heros fields.
                if(heroes[i] != null){
                    heroList += heroes[i] + "\n";
                }
            }
            
            heroList = heroList.substring(0, heroList.length() - 2); // remove the final ", " from the string
            
            //Displays valid heros
            JOptionPane.showMessageDialog(null, heroList, "Heroes", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /*
     *  searchButtons Action Listener
     */
    private class searchButtonAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(displayedFlag = true){
                //Prompts for search date
                String searchString = JOptionPane.showInputDialog("Enter the date you wish to search before:" + "(mm/dd/yyyy)", 
                JOptionPane.INFORMATION_MESSAGE);
    
                //Assuming input is mm/dd/yyyy
                RosterReader rosterReader = new RosterReader(file.getPath());
                int heroCount = rosterReader.getLineCount();
                
                String month, date, year;
                month = searchString.substring(0,2); //mm
                date = searchString.substring(3,5); //dd
                year = searchString.substring(6, searchString.length()); //yyyy
                
                int m, d, y;
                m = Integer.parseInt(month);
                d = Integer.parseInt(date);
                y = Integer.parseInt(year);
                
                Calendar otherCal = new Calendar(y, m, d);
                
                //Creates a new list to display with searched for values.
                String heroList = " ";
                for(int i = 0; i < heroCount; i++){
                    if(heroes[i] != null){
                        if(heroes[i].getCalendar().compareTo(otherCal) < 1){
                            heroList += heroes[i] + "\n";
                        }
                    }
                }
                
                //Display the searched list.
                JOptionPane.showMessageDialog(null, heroList, "Heroes", JOptionPane.INFORMATION_MESSAGE);
                
                try{
                    //write to a new file determined by the user.
                    String fileName = JOptionPane.showInputDialog("Please name your file and add the a '.txt' extension", 
                    JOptionPane.INFORMATION_MESSAGE);
                    //Sets PrintWriter type outputStream = fileName
                    PrintWriter outputStream = new PrintWriter(fileName);
                    outputStream.println(heroList);
                    outputStream.close();
                    //Notifies File was saved
                    JOptionPane.showMessageDialog(null, "File Saved", "Notification", JOptionPane.INFORMATION_MESSAGE); 
                }catch(IOException e){
                    //If file cannot be found.
                    JOptionPane.showMessageDialog(null, "In-proper file name", "Error", JOptionPane.ERROR_MESSAGE); 
                }
            }else{
                //If displayed is not equal to true.
                JOptionPane.showMessageDialog(null, "Please display the data first", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
   }
   
}