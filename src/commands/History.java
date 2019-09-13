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

import Stack.Stack;
import verify.command.ValidityCheckHistory;

/**
 * This class represents the History command
 *
 */
public class History implements Command {

  /**
   * Object instantiation to use stack functionality
   */
  private Stack savedCmds = new Stack();
  /**
   * Object instantiation to use stack functionality
   */
  private Stack tempCmds = new Stack();

  /**
   * Inserts given string array of command into History stack
   * 
   * @param command: the command being called in an array
   */
  public void pushCom(String[] command) {
    // push the command into the stack
    int len = command.length;
    String result = "";
    for (int i = 0; i < len; i++) {
      result = result + " " + command[i];
    }
    savedCmds.push(result);
  }

  /**
   * Remove the most recent command from History stack
   * 
   * @param command: the command being called in an array
   */
  public void popCom() {
    // pop the recent command off the stack
    savedCmds.pop();
  }

  /**
   * Returns the numbers of commands stored in history
   * 
   * @return the number of commands in history
   */
  public int getSize() {
    return savedCmds.size();
  }

  /**
   * Returns all the commands from the history stack
   * 
   * @return
   * 
   */
  public String outputHist() {
    return this.outputNumHist(this.getSize());
  }


  /**
   * Returns the given amount of recent commands from the history stack
   * 
   * @param amount: number of commands to print from the history stack
   * @return
   */
  public String outputNumHist(int amount) {
    // keep track of the position on the command, and initialize a string
    // variable
    int counter = 0;
    String result = "";
    // total number of commands saved in history
    int totalCmds = savedCmds.size();
    // if requested amount is greater than the number of commands stored
    if (amount > savedCmds.size()) {
      System.out
          .println("OutOfBoundError: exceeded maximum amount of commands");
    } else {
      // while the current command position does not exceed the amount requested
      while (counter < amount) {
        // pop from saved commands
        String command = (String) savedCmds.pop();
        // temporarily store it in another stack
        tempCmds.push(command);
        // append the position and the command name to the result
        counter += 1;
      }
      // position of command in terms of most recent
      int pos = totalCmds - amount;
      // temporary stack size
      int tempCmdsSize = tempCmds.size();
      // loop through the temporary stack, and put back the commands that were
      // extracted from the saved_commands stack
      for (int i = 0; i < tempCmdsSize; i++) {
        String cmdReturn = (String) tempCmds.pop();
        savedCmds.push(cmdReturn);
        // proceed to next position
        pos += 1;
        // append to result
        result = result + pos + ". " + cmdReturn + "\n";
      }
    }
    return result;
  }

  /**
   * Get the Command String from history at the given position, if no command is
   * found at given position then returns null
   * 
   * @param position: position of the command in the history that needs to be
   *        retrieved
   * @return String command if found or null if command not found
   */
  public String getHistoryNum(int position) {
    // total number of commands saved in history
    int totalCmds = savedCmds.size();
    String command = null;
    // if requested amount is greater than the number of commands stored
    if (position > savedCmds.size() || position < 0) {
      System.out
          .println("OutOfBoundError: exceeded maximum amount of commands");
    } else {
      // while the current command position does not exceed the amount requested
      while (totalCmds >= 0 && totalCmds != position) {
        // pop from saved commands
        command = (String) savedCmds.pop();
        // temporarily store it in another stack
        tempCmds.push(command);
        // append the position and the command name to the result
        totalCmds -= 1;
      }
      if (totalCmds != position) {
        command = null;
      } else {
        // pop from saved commands
        command = (String) savedCmds.pop();
        // temporarily store it in another stack
        tempCmds.push(command);
      }
    }
    // temporary stack size
    int tempCmdSize = tempCmds.size();
    // loop through the temporary stack, and put back the commands that were
    // extracted from the saved_commands stack
    for (int i = 0; i < tempCmdSize; i++) {
      String cmdReturn = (String) tempCmds.pop();
      savedCmds.push(cmdReturn);
    }
    return command;
  }

  /**
   * This method will check if what is being passed in is valid and based on the
   * number of parameters will call outputHist if no parameters are given or
   * will call outputNumHist if a integer valuse is given as parameter
   * 
   * @param params: the string command split into an array separated by space
   * @return null
   */

  public String execute(String[] params) {
    ValidityCheckHistory test = new ValidityCheckHistory();
    String result = null;
    if (test.validate(params)) {
      // if complete history needs to be displayed
      if (params.length == 1) {
        result = this.outputHist();
        // display only given amount of recent commands
      } else {
        // get the integer amount to display
        int amount = Integer.parseInt(params[1]);
        result = this.outputNumHist(amount);
      }
    } else {
      System.out.print("Invalid command, Please try again");
    }
    return result;
  }

}
