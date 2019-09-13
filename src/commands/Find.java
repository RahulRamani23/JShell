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

import java.util.ArrayList;
import file.Directory;
import file.FileSystem;
import verify.command.ValidityCheckFind;

/**
 * This class represents the find command
 *
 */
public class Find extends PathCheck implements Command {
  /**
   * Validity checker for the find command
   */
  private ValidityCheckFind valid;
  /**
   * The file System find will work on
   */
  private FileSystem fs;

  /**
   * Constructs the Find command
   * 
   * @param fSystem the current working file system
   */
  public Find(FileSystem fSystem) {
    valid = new ValidityCheckFind();
    this.fs = fSystem;
  }

  /**
   * This method acts as a helper for the findFile and actually checks if a
   * given file name exists at a given directory
   * 
   * @param type the type of file that is being searched for i.e Directory or
   *        File
   * @param currDir the current working directory
   * @param searchDir the path of the directory we're searching for the wanted
   *        file in
   * @param searchFile The wanted file
   * @return a boolean value indicating if the file exists
   */
  private boolean findFileHelper(String type, Directory currDir,
      String searchDir, String searchFile) {
    // if parse directory doesn't return null then the file exists
    // concatenate the searchDir and the searchFile also drop quotes from file
    // name
    String totalDir;
    totalDir = searchDir + "/" + searchFile;

    // find out with the search type is as well
    String searchType;
    if (type.equals("f")) {
      searchType = "File";
    } else {
      searchType = "Directory";
    }
    return (this.parseDirectory(searchType, currDir, totalDir) != null);
  }

  /**
   * This method checks if a file/directory with a given name exists at any
   * number of locations and returns true if it does
   * 
   * @param directories the directories to search for the file in
   * @param nameOfFile the name of the file to search for
   * @param type the type of the file to search for either d for directory or f
   *        for file
   * @param fs the current working file system
   */
  public String findFile(ArrayList<String> directories, String nameOfFile,
      String type) {
    // store the boolean value of whether the file exists
    boolean exists;
    // Store the output
    String output = "";
    // now loop through each given directory
    for (int i = 0; i < directories.size(); i++) {
      // check if the given directory using find file helper
      if (this.isPathRelative(directories.get(i))) {
        exists = this.findFileHelper(type, this.fs.getWorkingDir(),
            directories.get(i), nameOfFile);
      } else {
        exists = this.findFileHelper(type, this.fs.getRoot(),
            directories.get(i), nameOfFile);
      }
      // add the output to the output variable
      if (exists) {
        output += ("The file " + nameOfFile + " of type " + type + " exists at "
            + directories.get(i) + "\n");
      } else {
        output += ("The file " + nameOfFile + " of type " + type
            + " does not exist at " + directories.get(i) + "\n");
      }
    }
    return output;
  }

  /**
   * Given a list of parameters representing the find command i.e find then the
   * given paths to search in then -type [f|d] to indicate what type of file to
   * search for then the actual name of the file will check each directory
   * provided to see if the directory contains the file
   * 
   * @param theString the list of parameters for the find command starting with
   *        find
   */
  public String execute(String[] theString) {
    String ret = "";
    // error check what is being passed in
    if (valid.validate(theString)) {
      // Declare strings to hold file type and its name
      String type = "", name = "";
      // Declare ints to use when checking if the command is valid
      int foundType = 0, foundName = 0;
      // Arraylist which the directories we are going to search in are stored
      ArrayList<String> directories = new ArrayList<String>();
      // Sort the input data
      for (int i = 1; i < theString.length; i++) {
        // If we find '-type' make sure the proceeding argument is a valid
        // type (either 'f' or 'd') and check that they are only passing in
        // a single parameter for the filetype
        if (theString[i].equals("-type") && i + 1 < theString.length
            && (theString[i + 1].equals("f") || theString[i + 1].equals("d"))
            && theString[i + 2].equals("-name")) {
          // Store the index where '-type' is found to store the file type later
          foundType = i;
          // If we find '-name' make sure they are only inputting one file name
        } else if (theString[i].equals("-name") && i + 1 < theString.length
            && i + 2 == theString.length) {
          // Make sure the file name they are passing is surrounded by double
          // quotes
          if (theString[i + 1].charAt(0) == ('"') && theString[i + 1]
              .charAt(theString[i + 1].length() - 1) == ('"'))
            // Store the index of '-name' so we can store the name later
            foundName = i;
          // Keep adding the directories to search in while haven't found type
        } else if (foundType == 0) {
          directories.add(theString[i]);
        }
        // Only get type and name arguments if they exist
        if (foundName != 0 && foundType != 0) {
          type = theString[foundType + 1];
          name = theString[foundName + 1].substring(1,
              theString[foundName + 1].length() - 1);
        }
      }

      // call the find file method now
      ret = findFile(directories, name, type);

    } else {
      System.out.println("Invalid command, please try again");
      return null;
    }

    return ret;
  }
}
