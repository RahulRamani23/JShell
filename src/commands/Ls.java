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

import commands.Pwd;
import file.Directory;
import file.File;
import file.FileSystem;
import file.FileType;
import verify.command.ValidityCheckLs;

/**
 * This class represents the List command
 *
 */
public class Ls extends PathCheck implements Command {

  /**
   * Object instantiation to use Tree functionality
   */
  private Tree genTree;
  /**
   * The file system that ls will be operating on
   */
  private FileSystem fs;

  /**
   * Constructs the Cat command
   * 
   * @param system
   */
  public Ls(FileSystem system) {
    this.fs = system;
    genTree = new Tree(fs);
  }

  /**
   * Prints all directories and files in given current working directory
   * 
   * @param Working_dir: current working directory
   * @return string of concatenated names of files and directories in working
   *         dir
   */

  public String LsWorkingDir(Directory workingDir) {
    String result = "";
    // loop through each item in the working directory and print the filename
    for (int i = 0; i < workingDir.numFile(); i++) {
      result = result + (workingDir.getFile(i)).getFileName() + "\n";
    }
    return result;
  }

  /**
   * Prints all directories and files of the directory at the given path, if the
   * path exists, otherwise does nothing
   * 
   * @param root: root directory of file system
   * @param Working_dir: current working directory of user
   * @param path: path of directory that must have items in it listed
   * @param pwd: path of working directory
   * @return string of concatenated names of files and directories in given
   *         directory
   */

  private String LsGivenDir(Directory root, Directory workingDir, String path,
      Pwd pwd) {
    String resultStr = "";
    String initPath = path;
    // get current working path, and generate the absolute path
    String currWorkingPath = pwd.pwd();
    path = this.generate_fullpath(path, currWorkingPath);
    // check if path points to a file
    FileType typeFile = this.parseDirectory("File", workingDir, path);
    // if type is file and not directory
    if (typeFile != null) {
      this.LsGivenFile((File) typeFile);
    } else {
      // change into the desired directory based on path
      Directory lsDir =
          (Directory) this.parseDirectory("Directory", workingDir, path);
      // call LsworkingDir on new changed directory , to print all items in
      // that directory
      if (lsDir == null) {
        resultStr = path + ": Provided path does not exist \n";
      }
      // if the changed dir is not the root
      else if (lsDir != root) {
        // print each file and dir name
        resultStr = initPath + ":";
        for (int i = 0; i < lsDir.numFile(); i++) {
          resultStr = resultStr + " " + (lsDir.getFile(i)).getFileName();
        }
        resultStr = resultStr + "\n";
      }
    }

    return resultStr;
  }

  /**
   * Prints all directories and files of the root directory
   * 
   * @param root: root directory of file system
   * @param path: path of directory that must have items in it listed
   * @return string of concatenated names of files and directories in root
   *         directory
   */
  private String lsRootHelper(Directory root, String path) {
    // initial string var with path name
    String resultStr = path + ":";
    // loop thorough all files and directories in root and concatenate the names
    for (int i = 0; i < root.numFile(); i++) {
      resultStr = resultStr + " " + (root.getFile(i)).getFileName();
    }
    // add a newline character and return the string
    resultStr = resultStr + "\n";
    return resultStr;
  }

  /**
   * Prints name of given file
   * 
   * @param file
   * @return file name
   * 
   */

  public String LsGivenFile(File file) {
    // get and return file name
    return file.getFileName();
  }

  /**
   * Given 1 or more paths to a directory this method will list files and
   * subdirectories of each directory if
   * 
   * @param root: root directory of file system
   * @param Working_dir: current working directory of user
   * @param path: path of directory that must have items in it listed
   * @param pwd: path of working directory
   * @return string of concatenated names of files and directories of given
   *         paths
   */
  public String ls(Directory root, Directory currDir, String[] desiredPaths,
      Pwd work_dir) {
    String result = "";
    // loop through all paths and concatenate the resulting string names of file
    // and dirs
    for (int i = 0; i < desiredPaths.length; i++) {
      // if invalid path format
      if (desiredPaths[i].equals("/")) {
        // if files and directories of root must be listed
        result += this.lsRootHelper(root, desiredPaths[i]);
      } else if (desiredPaths[i].matches("/+")) {
        // print error message
        result = result + desiredPaths[i]
            + ": Provided path is not valid or does not exist \n";
        // check if the path is relative
      } else {
        // if given path is not root
        result += this.LsGivenDir(root, currDir, desiredPaths[i], work_dir);
      }
    }
    return result;
  }

  public String recursiveLs(String path) {
    // first check if the given path is absolute or relative
    Directory wantedDir;
    if (this.isPathRelative(path)) {
      // if the path is relative then call parseDirectory with the working dir
      wantedDir = (Directory) this.parseDirectory("Directory",
          fs.getWorkingDir(), path);
    } else {
      // otherwise check if the wanted directroy is the root
      if (path.equals("/")) {
        wantedDir = fs.getRoot();
      } else {
        wantedDir =
            (Directory) this.parseDirectory("Directory", fs.getRoot(), path);
      }
    }
    // Now if the returned Directory is not null then build the tree using
    // genTree
    String tree = "";
    if (wantedDir != null) {
      tree = genTree.buildTree(wantedDir, 0, "");
    } else {
      // Otherwise return an error
      System.out.println("Invalid Path");
    }
    return tree;
  }


  public String execute(String[] params) {
    String resultStr = null;
    // first check if the command is valid
    ValidityCheckLs test = new ValidityCheckLs();
    if (test.validate(params)) {
      // Case when we are doing ls on the working directory
      if (params.length == 1) {
        resultStr = this.LsWorkingDir(fs.getWorkingDir());
        // When we are given a specific path or paths
      } else if (params[1].equals("-R")) {
        resultStr = this.recursiveExecute(params);
      } else if (params.length > 1) {
        // Create an array list to store the desired paths
        String[] desiredPaths = new String[params.length - 1];
        // Run a loop to get all the paths that they want to show the contents
        // of
        for (int i = 1; i < params.length; i++) {
          desiredPaths[i - 1] = params[i];
        }
        resultStr = this.ls(fs.getRoot(), fs.getWorkingDir(), desiredPaths,
            fs.getWorkingPath());
        // Case when they don't give any params and just type ls
      }
    } else {
      System.out.println("Invalid command, please try again");
    }
    // print the result string of files and directories
    return resultStr;
  }

  private String recursiveExecute(String params[]) {
    // loop through every path given
    String result = null;
    // first check if a specific directory is supplied
    if (params.length < 3) {
      result = "Current Root: " + fs.getWorkingPath().pwd() + "\n"
          + this.recursiveLs(fs.getWorkingPath().pwd());
    } else {
      for (int i = 2; i < params.length; i++) {
        result +=
            "Current Root: " + params[i] + "\n" + this.recursiveLs(params[i]);
      }
    }
    return result;
  }

}

