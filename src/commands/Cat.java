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

import file.File;
import file.FileSystem;
import verify.command.ValidityCheckCat;
import file.Directory;
import commands.Pwd;

/**
 * This class represents the Cat command
 *
 */
public class Cat extends PathCheck implements Command {
  /**
   * The file system that Cat will be operating on
   */
  private FileSystem fs;

  /**
   * Constructs the Cat command
   * 
   * @param system
   */
  public Cat(FileSystem system) {
    this.fs = system;
  }

  /**
   * Print the contents of the given file
   * 
   * @param file: file that the contents are being printed
   * @return string contents of the given file
   */
  private String printContents(File file) {
    String resultStr = "";

    // call content function of class file to get contents
    resultStr = file.contents();
    return resultStr;
  }

  /**
   * This method parses through a given directory looking for a specific file
   * 
   * @param curr_dir The current working directory you're searching for the file
   *        in
   * @param pwd
   * @param path: the path of the directory you are looking for
   * @return the file you were looking for or if the file doesn't exist then
   *         returns null
   */
  private File getFile(String path, Directory currDir, Pwd pwd) {
    // use parseDirectory to find the file at the given path
    File file = (File) this.parseDirectory("File", currDir, path);

    return file;
  }

  /**
   * This method gets the contents of each of the files at the given paths and
   * prints them with a separation between the file contents by 3 newline
   * character.
   * 
   * @param curr_dir The current working directory you're searching for the file
   *        in
   * @param pwd
   * @param path: the path of the files you are looking for
   * @return The contents of each file separated by 3 newline characters
   */
  public String cat(String[] path, Directory currDir, Pwd pwd) {
    String result = "";
    // Run a loop to get all the paths that they want to show the contents
    // of
    for (int i = 0; i < path.length; i++) {
      // invalid path format
      if (path[i].matches("/+")) {
        // print error message
        System.out.println(result + path[i]
            + ": Provided path is not valid or does not exist");
      } else {
        // get the file at given path
        File file = this.getFile(path[i], currDir, pwd);
        // if file does exist, print contents
        if (file != null) {
          if (i + 1 == path.length && path.length == 1) {
            result = result + this.printContents(file);
          } else {
            result = result + this.printContents(file) + "\n \n \n";
          }
        } // check if file exists
        else {
          System.out.println(result + path[i] + ": File does not exist");
        }
      }
    }
    return result;

  }

  /**
   * This method will check if what is being passed in is valid and if it is it
   * will call the cat command on each file that is given
   * 
   * @param params: the string command split into an array separated by space
   * @return return contents of file as string or return null if file(s) does
   *         not exist
   */

  public String execute(String[] params) {
    String result = null;
    ValidityCheckCat test = new ValidityCheckCat();
    if (test.validate(params)) {
      // Make sure they are giving us at least one file to print
      if (params.length > 1) {
        // Create an array list to store the desired paths
        String[] desiredPaths = new String[params.length - 1];
        // Run a loop to get all the paths that they want to show the contents
        // of
        for (int i = 1; i < params.length; i++) {
          desiredPaths[i - 1] = params[i];
        }
        // get the contents of the files
        result =
            this.cat(desiredPaths, fs.getWorkingDir(), fs.getWorkingPath());
        // Invalid case when they only input cat
      }
    } else {
      System.out.println("Invalid command, please try again");
    }
    return result;
  }
}
