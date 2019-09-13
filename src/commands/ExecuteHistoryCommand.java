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

import commands.History;
import verify.command.ValidityCheckExecHistory;

/**
 * This class represents the !number command
 *
 */
public class ExecuteHistoryCommand {
  /**
   * String object to store the command needed to be executed
   */
  private String command;

  /**
   * Get the Command from history at the given position, if no command is found
   * at given position then returns null
   * 
   * @param pos: position of the command in the history that needs to be
   *        retrieved
   * @return String command if found or null if command not found
   */
  private String executeHistNum(int pos, History hs) {
    // get the command from history at the given position
    command = hs.getHistoryNum(pos);
    return command;
  }

  /**
   * This method will check if what is being passed in is valid and if it is it
   * will call the ExecuteHistNum with the given integer and history as
   * parameters and return the the command as a string or null
   * 
   * @param params: the string command split into an array separated by space
   * @param cmdHisotry: the History object that stores the up to date history
   * 
   * @return null if command at position is not found or returns the command as
   *         a string
   */
  public String executeHistory(String[] params, History cmdHistory) {
    String cmdFromHist = null;
    ValidityCheckExecHistory test = new ValidityCheckExecHistory();
    if (test.validate(params)) {
      // pop the !number command from the history
      cmdHistory.popCom();
      int numlen = params[0].length();
      // get the input number of the command to execute from history
      int pos = Integer.parseInt(params[0].substring(1, numlen));
      // get the command at the given position
      cmdFromHist = this.executeHistNum(pos, cmdHistory);
    } else {
      System.out.print("Invalid command, Please try again \n");
    }
    return cmdFromHist;
  }

}
