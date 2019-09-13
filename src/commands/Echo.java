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
package commands;

import verify.command.ValidityCheckEcho;

/**
 * The echo class implements the ability of a given string that has been inputed
 * to be printed out on the shell
 *
 */
public class Echo implements Command {

  private ValidityCheckEcho valid;

  /**
   * This is the Echo constructor which will instantiate the validity object
   */
  public Echo() {
    valid = new ValidityCheckEcho();
  }

  /**
   * Designed to output to the terminal the string when using the echo command
   * and no output file is given
   * 
   * @param theString: which is the string that will be returned
   * @return ret Which is the string they want to print or empty if invalid
   */
  public String execute(String[] theString) {
    // Variable to hold the return and create object to test
    String ret = "";
    // Check if valid
    if (valid.validate(theString)) {
      // Run a loop to reconstruct the given string
      for (int i = 1; i < theString.length; i++) {
        // Make sure to only add spaces until the last word
        if (i != theString.length - 1) {
          ret += theString[i] + " ";
        } else {
          ret += theString[i];
        }
      }
      // Remove the quotes
      ret = ret.replace("\"", "") + "\n";
    } else {
      System.out.println("Invalid command, please try again");
      return null;
    }
    // return to the shell the given string
    return ret;
  }

}
