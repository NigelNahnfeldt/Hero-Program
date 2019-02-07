/**
 * Write a description of class Hero here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Calendar;

public class Hero
{
    private String name;
    private Calendar date;
    
    public Hero(String[] tokens)
    {
        this.name = tokens[0];
        this.date.set(Integer.parseInt(tokens[3]),
                      Integer.parseInt(tokens[2]), 
                      Integer.parseInt(tokens[1]));
    }
    
    public void printHero()
    {
        
    }
}
