/**
 *  A Hero class to represent a Hero in the data file.
 * 
 *  @Nigel Nahnfeldt
 *  @1.0
 */


public class Hero
{
    //Variables
    private String name;
    public Calendar calendar;
    
    /*
     * No args Constructor
     */
    public Hero(){
        this.name = "";
        calendar = new Calendar(0,0,0);
    }
    
    /*
     *  Constructor
     */
    public Hero(String name, int year, int month, int date){
        this.name = name;
        calendar = new Calendar(year, month, date);
    }
    
    /*
     *  setter Method for name.
     */
    public void setName(String newName){
        this.name = newName;
    }
    
    /*
     * Getter Method for calender.
     */
    public Calendar getCalendar(){
        return calendar;
    }
    
    /*
     *  toString method for printing a hero.
     */
    @Override
    public String toString()
    {
        // month, day, year
        String birthday = Integer.toString(calendar.getMonth());
        birthday += ", " + Integer.toString(calendar.getDate()); 
        birthday += ", " + Integer.toString(calendar.getYear());
        return this.name + ", " + birthday;
    }
}
