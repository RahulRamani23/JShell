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

import file.FileSystem;
import file.File;
import file.Directory;
import file.FileType;
import verify.command.ValidityCheckCp;


/**
 * This represents the copy command that copies a directory, directories or file
 * to the desired target location
 */
public class Cp extends PathCheck implements Command {

  /**
   * Instance of the file system that is running
   */
  private FileSystem fs;
  /**
   * Instance of our validity checker for params
   */
  private ValidityCheckCp c;


  /**
   * Instantiates Cp object
   * 
   * @param f the working FileSystem
   */
  public Cp(FileSystem f) {
    fs = f;
    c = new ValidityCheckCp();
  }


  /**
   * Executes copy, copies the directory or file at params[1] to the directory
   * at params[2], params[0] is "cp"
   */
  public String execute(String[] params) {
    FileType src;
    Directory target;
    if (c.validate(params)) { // validate parameters
      if (this.isPathRelative(params[1])) { // get source directory
        src = this.parseDirectory("Directory", fs.getWorkingDir(), params[1]);
      } else {
        src = this.parseDirectory("Directory", fs.getRoot(), params[1]);
      }
      if (src == null) { // source doesn't exist, print error
        System.out.println("Source directory or file does not exist");
      } else if (params[1] == "/") { // source is root, not valid
        System.out.println("Cannot copy root directory");
      } else { // source exists
        if (this.isPathRelative(params[2])) { // get target directory
          target = (Directory) this.parseDirectory("Directory",
              fs.getWorkingDir(), params[2]);
        } else {
          target = (Directory) this.parseDirectory("Directory", fs.getRoot(),
              params[2]);
        }
        if (target != null) { // target exists
          this.copy(target, src); // copy src to target
        } else { // target does not exist
          System.out.println("Target directory does not exist");
        }
      }
    }
    return null;
  }


  /**
   * Copies the FileType to the target location, deleting another file type that
   * may have the same name.
   * 
   * @param target the target directory
   * @param src the file type that is being copied
   */
  private void copy(Directory target, FileType src) {
    int find;
    // if we have a directory
    if (src.equalsType("file.Directory")) {
      Directory cp = (Directory) src; // type cast directory
      cp = cp.copy(target); // copy directory
      // find if another FileType shares the name
      find = target.find(cp.getFileName());
      if (find != -1) { // delete it if it does
        target.delete(find);
      }
      target.append(cp); // append the copied directory
    } else { // we have a file
      // copy the file
      File cp = (File) src;
      cp = cp.copy();
      find = target.find(cp.getFileName());
      if (find != -1) { // if FileType with name already exists, remove
        target.delete(find);
      }
      target.append(cp); // replace with the copy
    }
  }
}
