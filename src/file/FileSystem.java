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
// UT Student #:  1004107637
// Author: Prashyen Kanagarajah
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package file;

import commands.Pwd;

/**
 * 
 * This represents a File System on pc containing the root and subdirectories
 *
 */
public class FileSystem {
  // define some instance variables
  /**
   * The root of the file system
   */
  private Directory root;
  /**
   * the current working directory the file system is at
   */
  private Directory workingDir;
  /**
   * The path of working directory
   */
  private Pwd workingPath;

  /**
   * instantiates the FileSystem object
   */
  public FileSystem() {
    // instantiate the root and workingDir
    root = new Directory("/", null);
    workingDir = root;
    workingPath = new Pwd();
  }

  /**
   * given a directory object this changes the current working directory to the
   * given directory
   * 
   * @param dir The new working directory
   */
  public void changeWorkingDir(Directory dir) {
    workingDir = dir;
  }

  /**
   * This method will get the root of the given file system object
   * 
   * @return the root of the file system
   */
  public Directory getRoot() {
    return this.root;
  }

  /**
   * This will get the current working directory of the File System
   * 
   * @return The current working directory
   */
  public Directory getWorkingDir() {
    return this.workingDir;
  }
  /**
   * This will get the current pwd of the File system
   * @return the current path of working directory
   */
  public Pwd getWorkingPath(){
    return this.workingPath;
  }

}
