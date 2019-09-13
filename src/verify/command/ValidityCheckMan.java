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

public class ValidityCheckMan implements Validator{

  // Method to error check the man command
  public boolean validate(String[] arguments) {
    // Variable to hold whether or not the command is valid
    boolean valid = false;
    // String containing valid commands
    String commands = "cat cd cp curl tree find history !number"
        + " ls mkdir pushd popd pwd echo echo mv, grep";
    // If statement to ensure they only pass in one argument after 'man'
    if ((arguments.length == 2) && (commands.indexOf(arguments[1]) != -1)) {
      // Command is valid
      valid = true;
    }
    // Return the validity
    return valid;
  }

}
