// ********************************************************** 
// Assignment2: 
// Student1: Rahul Ramani 
// UTORID user_name: ramanira 
// UT Student #: 1004110758 
// Author: Rahul Ramani 
// 
// Student2: Justin Lyn 
// UTORID user_name: lynjust2 
// UT Student #: 1004178301 
// Author: Justin Lyn 
// 
// Student3: Ryan de Sao Jose  
// UTORID user_name: desaojo3 
// UT Student #: 1004401961 
// Author: Ryan de Sao Jose 
// 
// Student4: Prashyen Kanagarajah 
// UTORID user_name: kanaga60 
// UT Student #: 1004107637 
// Author: Prashyen Kanagarajah 
// 
// 
// Honor Code: I pledge that this program represents my own 
// program code and that I have coded on my own. I received 
// help from no one in designing and debugging my program. 
// I have also read the plagiarism section in the course info 
// sheet of CSC B07 and understand the consequences. 
// ********************************************************* 

package verify.command;

import java.util.ArrayList;

public class ValidityCheckFind implements Validator{

  // Method to error check the man command
  public boolean validate(String[] arguments) {
    // Variable to hold whether or not the command is valid
    boolean valid = false;
    // Declare variables to use when checking if the command is valid
    int foundType = 0, foundName = 0;
    // Array list which the directories we are going to search
    ArrayList<String> directories = new ArrayList<String>();
    // Make sure they passed in more that just 'find'
    if (arguments.length != 1) {
      // Check the input data
      for (int i = 1; i < arguments.length; i++) {
        // If we find '-type' make sure the proceeding argument is a valid
        // type (either 'f' or 'd') and check that they are only passing in
        // a single parameter for the file type
        if (arguments[i].equals("-type") && i + 1 < arguments.length
            && (arguments[i + 1].equals("f") || arguments[i + 1].equals("d"))
            && arguments[i + 2].equals("-name")) {
          // Store the index where '-type' is found
          foundType = i;
          // If we find '-name' make sure they are only inputting one file name
        } else if (arguments[i].equals("-name") && i + 1 < arguments.length
            && i + 2 == arguments.length) {
          // Make sure the file name is surrounded by double quotes
          if (arguments[i + 1].charAt(0) == ('"') && arguments[i + 1]
              .charAt(arguments[i + 1].length() - 1) == ('"'))
            // Store the index of '-name'
            foundName = i;
          // Keep adding the directories to search in
        } else if (foundType == 0) {
          directories.add(arguments[i]);
        }
      }
    }
    // Only call the necessary classes to execute find if all the
    // parameters are given and valid
    if (foundName != 0 && foundType != 0 && !directories.isEmpty()) {
      valid = true;
    }
    // Return the validity
    return valid;
  }
}
