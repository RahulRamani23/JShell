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

import verify.command.ValidityCheckMkdir;
import file.Directory;
import file.FileSystem;

/**
 * This class represents the mkdir command
 * 
 */
public class MakeDirectory extends PathCheck implements Command {
  /**
   * The file system that MakeDirectory will be operating on
   */
  private FileSystem fs;

  /**
   * Constructs the MakeDirectroy command
   * 
   * @param system
   */
  public MakeDirectory(FileSystem system) {
    this.fs = system;
  }

  /**
   * Given the path for a new directory to be in followed by the name of the new
   * directory i.e /dir1/newdir this method will create a new dir in the
   * specified directory, if no prior path is given then the directory will be
   * made in current working directory if the new directory to be made doesn't
   * already exist in the location to be made.
   * 
   * @param newDir the path of the new directory
   * @param currDir the current working directory for the new dir to be made in
   *        this will be root if an absolute path is passed
   * 
   */

  private void mkdirHelper(String newDir, Directory currDir) {
    // first remove the first character from newDir string if its an absolute
    // path
    if (!this.isPathRelative(newDir)) {
      newDir = newDir.replaceAll("^/+", "");
    }
    // check if all that is given is the name of the directory they want
    // to create i.e check if theres no /'s
    // to do this replace all slashes in newDir with nothing and subtract the
    // new length from original length
    // check if the path is absolute if it
    int numSlashes = newDir.length() - newDir.replace("/", "").length();
    if (numSlashes == 0) {
      // if the number of slashes is 0 then check if the directory t be made
      // already exists in the current directory but first check if the
      // directory contains any invalid characters
      if (newDir.matches("[a-zA-Z0-9]*")) {
        if (currDir.find(newDir) == -1) {
          // if the directory to be made doesnt exist then make it
          this.addDirectory(currDir, newDir);
        } else {
          // if it does exist then print an error to system.err
          System.out.println("The directory to be made already exists");
        }
      } else {
        System.out.println("The new directory contains invalid characters");
      }

    } else {
      // if there are slashes and the path is relative get the entire path until
      // the last slash so that the name of the new file name isnt included
      String srcDir = newDir.substring(0, newDir.lastIndexOf('/'));
      // now parse through the current directory until you are at the last
      // directory in the original path
      currDir = (Directory) this.parseDirectory("Directory", currDir, srcDir);
      // check to ensure the path exists other wise print an error
      if (currDir != null) {
        // if it does exists then check if the directroy to be made already
        // exists but first check if there are any invalid characters
        if (newDir.substring(newDir.lastIndexOf('/') + 1).matches(
            "[a-zA-Z0-9]*")) {
          if (currDir.find(newDir.substring(newDir.lastIndexOf('/') + 1)) == -1) {
            // if the directory to be made doesnt exist then make it
            this.addDirectory(currDir,
                newDir.substring(newDir.lastIndexOf('/') + 1));
          } else {
            // if it does exist then print an error to system.err
            System.out.println("The directory to be made already exists");
          }
        } else {
          // if there are invalid characters output it to user
          System.out.println("The new directory contains invalid characters");
        }

      } else {
        // if it does equal null then print an error to stderr
        System.out.println("The input directory " + newDir + " is not Valid");
      }
    }

  }

  /**
   * Given 1 or more paths to a new directory this method will create each new
   * directory if all sub-directories before it exist and the new directories
   * name doesn't already exists and doesn't contain any special characters i.e
   * /dir1/newdir this method will create a new dir in the specified directory,
   * if no prior path is given then the directory will be made in current
   * working directory
   * 
   * @param params list of all the parameters given for the command where the
   *        first index is the name of the command and remaining indices are
   *        file paths
   */
  public String execute(String[] params) {
    // first check if the command is valid
    ValidityCheckMkdir test = new ValidityCheckMkdir();
    if (test.validate(params)) {
      for (int i = 1; i < params.length; i++) {
        // check if the path is relative
        if (this.isPathRelative(params[i])) {
          // if it is relative call mkdir pass in the current working dir
          this.mkdirHelper(params[i].replaceAll("/+$", ""), fs.getWorkingDir());
        } else {
          // check if the path is the root indicating an erro
          if (params[i].matches("/+")) {
            System.out.println(params[i] + ": is not a valid path");
          } else {
            // otherwise pass in the root directory
            this.mkdirHelper(params[i].replaceAll("/+$", ""), fs.getRoot());
          }
        }
      }
    }else{
      System.out.println("Invalid Command");
    }
    return null;
  }

  /**
   * Given a directory and a name of a newDirectory this method creates the new
   * directory object and appends it on to the contents of the given directory
   * 
   * @param currDir directory object in which a new directory will be added in
   * @param dirName the name of the new directory to be made
   */
  private void addDirectory(Directory currDir, String dirName) {
    Directory newDirectory = new Directory(dirName, currDir);
    currDir.append(newDirectory);
  }



}
