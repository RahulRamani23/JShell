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

package executor;

import commands.Command;
import commands.EchoAppend;
import commands.EchoCreate;
import java.util.Arrays;
import file.FileSystem;

/**
 * This class represents the executor that executes commands and directs
 * their outputs
 */
public class Executor {
  
  /**
   * Our instance to append output to files
   */
  private EchoAppend eA;
  
  /**
   * Out instance to rewrite files with output
   */
  private EchoCreate eC;
  
  /**
   * Instantiates an Executor object
   * @param fs the working FileSystem
   */
  public Executor(FileSystem fs) {
    eA = new EchoAppend(fs);
    eC = new EchoCreate(fs);
  }
  
  /**
   * Executes the desired command <commandToExec> and passes the params to the
   * command. This will control where the output is sent, either to the screen
   * or to a file
   * @param commandToExec the command that needs to be executed
   * @param params the parameters to send to the command
   */
  public void executeCommand(Command commandToExec, String[] params) {
    int index = 0;
    boolean found = false;  // determines if we printed to file
    while (index < params.length && !found) {  // check for print to files
      if (params[index].equals(">")) { // echo create
        eC.run(Arrays.copyOfRange(params, index, params.length),
            commandToExec.execute(Arrays.copyOfRange(params, 0, index)));
        found = true;
      } else if (params[index].equals(">>")) {  // echo append
        eA.run(Arrays.copyOfRange(params, index, params.length),
            commandToExec.execute(Arrays.copyOfRange(params, 0, index)));
        found = true;
      }
      index++;
    }
    // didn't print to file, so print to screen
    if (!found) {
      String output = commandToExec.execute(params);
      if (output != null) {
        System.out.print(commandToExec.execute(params));
      }
    }
  }

}
