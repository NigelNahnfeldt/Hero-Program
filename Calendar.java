
/**
 * A new Calendar class that implements comparable.
 * Methods to compare greater or equals too
 *
 * @author Nigel Nahnfeldt
 * @version 1.0
 */
public class Calendar implements Comparable<Calendar>
{
    // instance variables
    private int year;
    private int month;
    private int date;

    /**
     * Constructor for objects of class Calendar
     */
    public Calendar()
    {
        year = 0;
        month = 0;
        date = 0;
    }
    
    public Calendar(int newYear, int newMonth, int newDate)
    {
        year = newYear;
        month = newMonth;
        date = newDate;
    }
    
    //Getter for Year
    public int getYear(){
        return year;
    }
    
    //Getter for Month
    public int getMonth(){
        return month;
    }
    
    //Getter for Date
    public int getDate(){
        return date;
    }
    
    //Setter for Year
    public void setYear(int newYear){
        year = newYear;
    }
    
    //Setter for Month
    public void setMonth(int newMonth){
        month = newMonth;
    }
    
    //Setter for Date
    public void setDate(int newDate){
        date = newDate;
    }
       
    /*
     * Allows us to say stuff like 'if(cal1 == cal2)'
     */
    @Override
    public boolean equals(Object otherCal){
        if(otherCal instanceof Calendar){
            Calendar otherCalendar = (Calendar) otherCal;
            return this.year == otherCalendar.getYear() &&
            this.month == otherCalendar.getMonth() &&
            this.date == otherCalendar.getDate();
        }else{
            return false;
        }
    }
    
    /*
     * 
     * Allows us to say stuff like 
     * 'if(cal1.compareTo(cal2) == -1) do things //which means cal1 is older
     * 'if(cal1.compareTo(cal2) == 0) do things //which means they're equal dates
     * 'if(cal1.compareTo(cal2) == 1) do things //which means cal2 is older
     */
    @Override
    public int compareTo(Calendar otherCalendar) {
        if(this.year < otherCalendar.getYear() || 
           this.year == otherCalendar.getYear() && this.month < otherCalendar.getMonth() ||
           this.year == otherCalendar.getYear() && this.month == otherCalendar.getMonth() && this.date < otherCalendar.getDate()){
               return -1;
        }else if(this.equals(otherCalendar)){
            return 0;
        }else{
            return 1;
        }
    }
    
}
