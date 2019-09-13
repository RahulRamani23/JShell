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

import verify.command.ValidityCheckTree;
import file.Directory;
import file.FileSystem;
import file.FileType;

/**
 * This class represents the tree command
 * 
 */
public class Tree implements Command {
  
  /**
   *
   * The file system that Tree will be operating on
   */
  private FileSystem fs;
  /**
   * Constructs the Tree object
   * @param fSystem the current working FileSystem
   */
  public Tree(FileSystem fSystem) {
    this.fs = fSystem;
  }

  /**
   * This method executes the tree command
   * 
   * @param root The root of the current file system
   * @return the resulting tree from the given file system
   */
  public String execute(String[] params) {
    // check validity
    ValidityCheckTree test = new ValidityCheckTree();
    if (test.validate(params)){
    // simply call buildTreeHelper to create the string
    return this.buildTree(this.fs.getRoot(), 0, "");
    }else{
      System.out.println("Invalid Command");
      return null;
    }
  }

  /**
   * This method prints out a tree of all directories starting from the
   * directory
   * 
   * @param root the current root directory containing sub-directories and files
   * @param depth the depth of the current directory i.e how many levels beneath
   *        the root directory
   * @param result the current resulting string
   * @return the tree representing the directory
   */
  public String buildTree(Directory root, int depth, String result) {
    // first check if the current directory is empty
    if (root.numFile() == 0) {
      // if there are no files just return the name of the current directory
      // plus any additional tabs required
      return (result + (addTabs(depth) + root.getFileName()) + "\n");
    } else {
      // if files do exist then loop through each one adding its children
      // recursively but first add the name of the current directory to result
      result += (addTabs(depth) + root.getFileName() + "\n");
      for (int i = 0; i < root.numFile(); i++) {
        // for each file in the current directory check the type of the file
        // add its name if its a file or go into it if its a directory
        FileType currFile = root.getFile(i);
        if (currFile.equalsType("file.Directory")) {
          result =
              this.buildTree((Directory) currFile, depth + 1, result);
        } else {
          result += (addTabs(depth + 1) + currFile.getFileName() + "\n");
        }
      }
    }
    // return the result
    return result;
  }

  /**
   * This method acts as helper for buildTree by returning a string which has
   * the correct amount of spaces based on a given depth which will be prefixed
   * onto a file name
   * 
   * @param depth the depth of the current file that is being dealt with
   * @return
   */
  private String addTabs(int depth) {
    String str = "";
    for (int i = 0; i < depth; i++) {
      str += "    ";
    }
    return str;
  }

}
