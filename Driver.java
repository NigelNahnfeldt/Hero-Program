/**
 *  @Nigel Nahnfeldt
 *  @1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class Driver extends JFrame
{
    private JButton fileSearchButton, displayButton, parseButton;
    private JLabel promptLabel;
    private JTextField inputField;
    File file = null;
    
    public static void main()
    {
        Driver mainFrame = new Driver();
        mainFrame.setSize(350,90);
        mainFrame.setTitle("Driver Searcher");  //Works / I Understand
        mainFrame.createGUI();
        mainFrame.setVisible(true);
    }
    
    public void createGUI()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        
        // Use seperate classes to handle all buttons.
        fileSearchButton = new JButton("Select a File");
        fileSearchButton.addActionListener(new fileSearchButtonAction());  //Works/I understand
        window.add(fileSearchButton);
        
        displayButton = new JButton("Process & Display");
        displayButton.addActionListener(new displayButtonAction());
        
        window.add(displayButton);
    }
    
    private class fileSearchButtonAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
               String userHome = System.getProperty("user.home");
               userHome += "/Desktop";
               JFileChooser fileSelector = new JFileChooser(userHome);
               int returnFlag = fileSelector.showDialog(Driver.this, "Select input file"); // Works/ but confused.
               if (returnFlag == JFileChooser.APPROVE_OPTION) 
               {
                  file = fileSelector.getSelectedFile();
               }
               else 
               {
                     JOptionPane.showMessageDialog(null,"No input file was selected",
                        "File Warning",JOptionPane.WARNING_MESSAGE);
               }
        }  
    }
    
    private class displayButtonAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            
            RosterReader rosterReader = new RosterReader(file.getPath());
            int heroCount = rosterReader.getLineCount();
            Hero[] heroes = new Hero[heroCount];
            
            try 
            {
                int index = 0;
                while (rosterReader.readNextHero())
                {
                    heroes[index] = rosterReader.getCurrentHero(); // save off your hero
                    index++; // incrememnt hero count
                }

                String heroList = ""; 
                for(int i = 0; i < heroCount; i++)
                {
                    heroList += heroes[i].toString() + ", ";
                }
                
                heroList = heroList.substring(0, heroList.length() - 2); // remove the final ", " from the string
                
                // printHero is undefined
                JOptionPane.showMessageDialog(null, heroList, "Heroes",JOptionPane.INFORMATION_MESSAGE); 
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"No input file has been selected",
                        "File Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}