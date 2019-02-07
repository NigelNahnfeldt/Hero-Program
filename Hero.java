/**
 *  @Nigel Nahnfeldt
 *  @1.0
 */

import java.util.Calendar;

public class Hero
{
    public String name;
    public Calendar date;
    
    public Hero(String[] tokens)
    {
        this.name = tokens[0];
        this.date.set(Integer.parseInt(tokens[3]),
                      Integer.parseInt(tokens[2]), 
                      Integer.parseInt(tokens[1]));
    }
    
    @Override
    public String toString()
    {
        // month, day, year
        String birthday = this.date.get(Calendar.MONTH) + ", " + this.date.get(Calendar.DAY_OF_MONTH) + ", " + this.date.get(Calendar.YEAR);
        return this.name + ", " + birthday;
    }
}
