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
package driver;

import java.io.*;
import file.FileSystem;
import parser.ParseCommand;

public class JShell {

  public static void main(String[] args) throws IOException {

    // Declare buffered reader to get user input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // Declare command variable
    String command = "";
    // Create the root directory and set it to the current directory
    FileSystem fileSystem = new FileSystem();
    // Create the parse object
    ParseCommand cmd = new ParseCommand(fileSystem);


    // run a while loop until the user types 'exit'
    while (!command.equals("exit")) {
      // Variable to store and reset the command variables
      command = "";
      // output the prompt and get the users command
      System.out.print(fileSystem.getWorkingPath().pwd() + "# ");
      command = br.readLine();
      // Parse the given command if not empty or equal to exit
      if (command.equals("")) {
        System.out.println("Invalid command, please try again");
      } else if (!command.equals("exit")) {
        cmd.parseTheCommand(command);
      }
    }

  }
}
