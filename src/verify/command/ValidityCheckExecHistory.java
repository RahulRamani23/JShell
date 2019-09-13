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

public class ValidityCheckExecHistory implements Validator {

  // Method to error check the man command
  public boolean validate(String[] arguments) {
    // Variable to hold whether or not the command is valid
    boolean valid = false;
    if (arguments[0].substring(1).matches("[0-9].*")) {
      int numlen = arguments[0].length();
      // get the input number of the command to execute from history
      int pos = Integer.parseInt(arguments[0].substring(1, numlen));
      // If statement to ensure they pass in "!" followed by a number greater
      // than
      // 0
      if (arguments[0].charAt(0) == '!' && arguments.length == 1 && pos > 0) {
        valid = true;
      }
    }

    // Return the validity
    return valid;
  }

}
