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
package parser;

import java.util.*;
import commands.Cat;
import commands.ChangeDirectory;
import commands.Command;
import commands.Cp;
import commands.Curl;
import commands.Echo;
import commands.History;
import commands.Ls;
import commands.MakeDirectory;
import commands.Man;
import commands.PushAndPopd;
import commands.Tree;
import executor.Executor;
import commands.Find;
import commands.Grep;
import commands.ExecuteHistoryCommand;
import commands.MoveFiles;
import file.FileSystem;


/**
 * The ParseCommand class implements the ability to parse and error check the
 * commands being passed in and execute the required classes to achieve the
 * intended command action
 *
 */
public class ParseCommand {

  // Create the instance hashtable to store the commands
  private Hashtable<String, Command> commands;
  private Executor exec;
  private PushAndPopd pushAndPopd;
  private ExecuteHistoryCommand execHistory;

  /**
   * This is the parseCommand instructor which will instantiate all the objects
   * required for each command available to be executed
   * 
   * @param fs Which is the file system to be used in the other commands
   *        instantiations
   */
  public ParseCommand(FileSystem fs) {
    exec = new Executor(fs);
    commands = new Hashtable<String, Command>();
    commands.put("cat", new Cat(fs));
    commands.put("cd", new ChangeDirectory(fs));
    commands.put("echo", new Echo());
    commands.put("find", new Find(fs));
    commands.put("history", new History());
    commands.put("ls", new Ls(fs));
    commands.put("mkdir", new MakeDirectory(fs));
    commands.put("man", new Man());
    commands.put("tree", new Tree(fs));
    commands.put("curl", new Curl(fs));
    commands.put("mv", new MoveFiles(fs));
    commands.put("pwd", fs.getWorkingPath());
    commands.put("grep", new Grep(fs));
    commands.put("cp", new Cp(fs));
    pushAndPopd = new PushAndPopd(fs);
    execHistory = new ExecuteHistoryCommand();
  }


  /**
   * The parseTheCommand method will take what was passed in from JShell and
   * call the appropriate commands to execute the required tasks
   * 
   * @param arguments String representation of what the user inputs
   */
  public void parseTheCommand(String arguments) {

    // Store the command in an array and trim extra spaces
    String cmds[] = arguments.trim().split("\\s+");
    String command = "";
    // push the commands into history
    ((History) commands.get("history")).pushCom(cmds);
    // Special cases
    if (cmds[0].equals("pushd")) {
      pushAndPopd.executePushd(cmds);
    } else if (cmds[0].equals("popd")) {
      pushAndPopd.executePopd(cmds);
    } else if (cmds[0].charAt(0) == ('!')) {
      String cmdFromHist =
          (execHistory.executeHistory(cmds, (History) commands.get("history")));
      // call parser on the command, if position is valid
      if (cmdFromHist != null) {
        this.parseTheCommand(cmdFromHist);
      }
    } else {
      command = cmds[0];
      // Retrieve the object from hashtable and execute
      Command theCommand = commands.get(command);

      // Execute the command
      if (theCommand != null) {
        exec.executeCommand(theCommand, cmds);
      } else {
        System.out.println("Invalid command, please try again");
      }

    }
  }
}
