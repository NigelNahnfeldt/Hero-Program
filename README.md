# Hero-Program

Program One: Heroes
Write an application that first reads the names and birthday of heroes from a file and stores them in an array. Following this, the application should allow the user to enter a date.  Finally, the application should allow the user to select all of the heroes with a birthday before or equal to the user specified date and display those heroes and also write them to a file.




Specific requirements:

Use a GUI for all interactions (buttons, fields, dialog boxes, ...)
Ask the user for the names of both the input and output files
Use the Calendar class to store dates
Assume the input/output file format is: Name, Birth Month, Birth Day, Birth Year
One hero per line
Each field is separated by a comma
Except in the name field cblank spaces are ignored
Use a linear search algorithm for finding heroes to be selected



A Few Hints:

Use Calendar methods for all date operations
In the Calendar class month is zero-based
If needed, use trim method to remove leading blank spaces
If needed, use the split method: String[] tokens = inputLine.split(",");



Extra Credit: Allow the user to enter two dates (i.e., a range) for use in selection

