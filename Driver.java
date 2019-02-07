
/**
 * Write a description of class Driver here.
 *
 * @author Nigel Nahnfeldt
 * @version 1.0
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
        mainFrame.setTitle("Driver Searcher");
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
        fileSearchButton.addActionListener(new fileSearchButtonAction());
        window.add(fileSearchButton);
        
        displayButton = new JButton("Process & Display");
        displayButton.addActionListener(new displayButtonAction());
        window.add(displayButton);
    }
    
    private class fileSearchButtonAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
       
               //JFileChooser myChooser = new JFileChooser();
               String userHome = System.getProperty("user.home");
               userHome += "/Desktop";
               JFileChooser fileSelector = new JFileChooser(userHome);
               int returnFlag = fileSelector.showDialog(Driver.this, "Select input file");
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
            Scanner inputFile = null;
            String theLine, displayList = "";
            String[] tokens;
            Hero hero[] = new Hero[17];
            
            try {
                inputFile = new Scanner(file);
      
                while (inputFile.hasNext()) 
                {
                    int i = 0;
                    theLine = inputFile.nextLine();
                    tokens = theLine.split(",");
                    hero[i] = new Hero(tokens);
                    
                    String displayList += hero[i].name + " " + hero[i].date + "/n";
                    i++;
                }
                
                
                JOptionPane.showMessageDialog(null,displayText,"Heros",JOptionPane.INFORMATION_MESSAGE);
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null,"No input file has been selected",
                        "File Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
