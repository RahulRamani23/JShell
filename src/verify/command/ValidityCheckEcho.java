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

public class ValidityCheckEcho implements Validator{

  // Method to error check the man command
  public boolean validate(String[] arguments) {
    // Variable to hold whether or not the command is valid
    boolean valid = false;
    // Variables to hold the desired string, type of echo, and file
    String theString = "", type = "";
    // Loop to get the string and check what type of echo
    for (int i = 1; i < arguments.length; i++) {
      // Check for the type specifier
      if ((arguments[i].equals(">")) || (arguments[i].equals(">>"))) {
        type = arguments[i];
        // Concatenate the string that they want to use
      } else if (type.equals("")) {
        theString += arguments[i];
      }
    }
    // Error checking to make sure no extra quotes in the given string
    boolean noXtraQuotes = true;
    for (int i = 1; i < theString.length() - 2; i++) {
      // If found another quote in the string its no longer valid
      if (theString.charAt(i) == ('"')) {
        noXtraQuotes = false;
      }
    }
    // Only execute echo arguments if the string is enclosed in quotes and
    // there aren't any extra quotes within the string
    if (!theString.equals("") && theString.charAt(0) == ('"')
        && theString.charAt(theString.length() - 1) == ('"') && noXtraQuotes) {
      valid = true;
    }
    // Return the validity
    return valid;
  }

}
