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

import file.Directory;
import file.FileSystem;
import verify.command.ValidityCheckCd;
import commands.Pwd;


/**
 * This class represents the Change Directory command
 *
 */
public class ChangeDirectory extends PathCheck implements Command {
  /**
   * The file system that ChangeDirectory will be operating on
   */
  private FileSystem fs;

  /**
   * Constructs the ChangeDirectroy command
   * 
   * @param system
   */
  public ChangeDirectory(FileSystem system) {
    this.fs = system;
  }

  /**
   * Loop thorough to the directory to change to, and returns the directory
   * 
   * @param path: full path location of the directory to change into
   * @param curr_dir: current working directory
   * @param curr_working_path: path of current working directory
   * @param pwd
   * @param directories: the directories that must be changed into
   * @param dirindex: index of the current directory based of the directories
   *        array
   * @param origDir: the original directory before changing into sub directories
   * @return changed directory or original directory
   */
  private Directory changeDirHelper2(String path, Directory currDir,
      Directory root, Pwd pwd, String[] dirs, int dirIndex, Directory origDir) {
    int location_found = 0, index = 0;
    while (location_found != -1 && dirIndex < dirs.length) {
      if (dirs[dirIndex].equals("..")) { // if change to parent directory
        currDir = (Directory) this.parseDirectory("Directory", currDir,
            dirs[dirIndex]); // change one directory up
        index += 1; // move to next index
        dirIndex += 1;
        pwd.upDir(1); // change pwd to parent
      } else if (dirs[dirIndex].equals(".")) { // increment index
        index = +1;
        dirIndex += 1;
      } else {
        Directory newdir = (Directory) this.parseDirectory("Directory", currDir,
            dirs[dirIndex]);// get next sub directory in dirs
        index += 1; // increment index
        dirIndex += 1;
        if (newdir != null) {// if sub directory exists
          currDir = newdir;// set curr_dir to be the new sub directory
          pwd.dirChange(currDir);
        } else {
          pwd.upDir(index - 1); // change pwd back to original path
          System.out.println(path + ": Provided path is invalid");
          currDir = origDir; // set curr dir to original
          location_found = -1;
        }
      }
    }
    return currDir;
  }


  /**
   * Changes into sub directory based on given path, returns the new directory
   * 
   * @param path: full path location of the directory to change into that exist
   * @param curr_dir: current working directory
   * @param curr_working_path: path of current working directory
   * @return changed directory or original directory
   */

  private Directory changeDirHelper(String path, Directory currDir,
      Directory root, Pwd pwd) {
    // store original dir to return, in case given path doesn't exist
    Directory origDir = currDir;
    // if path is abs, then switch currDir to root, to start search from root
    if (path.indexOf("/") == 0) {
      currDir = root;
    } // make sure path is the absolute path
    path = this.generate_fullpath(path, pwd.pwd());
    // check if curr_dirr is root and the path is valid, then change pwd to
    // root
    if (this.parseDirectory("Directory", root, path) != null
        && currDir == root) {
      pwd.root();
    } // split path based on sub directories using the forward slashes
    String[] directories = path.split("/+");
    // index of the directory to change to from the directories array
    int dirIndex;
    // if current directory is root
    if (pwd.pwd().equals("/")
        || pwd.pwd().indexOf("/") == 0 && currDir == root) {
      dirIndex = 1;
    } else {
      // else get the index of the directory to change to from finding the
      // length of directories in pwd path
      String[] cur_directories = pwd.pwd().split("/");
      dirIndex = cur_directories.length;
    }
    currDir = changeDirHelper2(path, currDir, root, pwd, directories, dirIndex,
        origDir);
    return currDir;
  }

  /**
   * Changes into sub directory based on given path, returns the new directory,
   * if argument is invalid prints error, and return the original working
   * directory of user
   * 
   * @param path: full path location of the directory to change into
   * @param curr_dir: current working directory
   * @param curr_working_path: path of current working directory
   * @return changed directory or original directory if given path does not
   *         exist
   */
  public Directory changeDir(String path, Directory currDir, Directory root,
      Pwd pwd) {
    // if path is multiple forward slashes, then change directory to root
    if (path.matches("/+")) {
      // set current directory to root
      currDir = root;
      // set pwd to root
      pwd.root();
      // check for invalid path format with forward slashes
    } else if (path.matches("/+")) {
      // print error message
      System.out
          .println(path + ": Provided path is not valid or does not exist");
      // change directory to other paths excluding root
    } else {
      currDir = changeDirHelper(path, currDir, root, pwd);
    }
    return currDir;


  }

  /**
   * This method will check if what is being passed in is valid and will call
   * the cahngeWorkingDir method
   * 
   * @param params: the string command split into an array separated by space
   * @return null
   */
  public String execute(String[] params) {
    ValidityCheckCd test = new ValidityCheckCd();
    String result = null;
    if (test.validate(params)) {
      // Call change directory on the object
      fs.changeWorkingDir(this.changeDir(params[1], fs.getWorkingDir(),
          fs.getRoot(), fs.getWorkingPath()));
    } else {
      System.out.print("Invalid command, Please try again");
    }
    return result;
  }

}
