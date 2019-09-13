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

package test;

import commands.Command;

/**
 * Resembles a command and is used for testing executor
 *
 */
public class MockCommand implements Command {
  
  /**
   * Returns "Test succeeded" when called 
   */
  public String execute(String[] params) {
    String ret;
    if (params[0].equals("good")) {
      ret = "Test succeeded\n";
    } else if (params[0].equals("")) {
      ret = "";
    } else {
      ret = null;
    }
    return ret;
  }

}
