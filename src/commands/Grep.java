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
import file.File;
import file.FileSystem;
import file.FileType;
import verify.command.ValidityCheckGrep;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class represents the Grep command
 *
 */

public class Grep extends PathCheck implements Command {
  /**
   * Object instantiation to store final lines that contain the regex
   */
  private String result;

  /**
   * The file system that Grep will be operating on
   */
  private FileSystem fs;

  /**
   * Constructs the Grep command
   * 
   * @param system
   */

  /**
   * The regex pattern that is meant to be matched
   */
  private Pattern p;
  /**
   * the matcher which compares a string to the given pattern
   */
  private Matcher m;

  /**
   * Constructs Grep object
   * 
   * @param system current working file system
   */
  public Grep(FileSystem system) {
    this.fs = system;
  }


  /**
   * Finds the file at the given path, and searches for the regex within the
   * contents of the file and prints every line that contains the regex
   * 
   * @param path: the path to the file
   * @param currDir: the current working directory
   * @param regex: the string regex to search for in the file
   */
  public String grepFile(String path, Directory currDir, Directory root,
      String regex) {
    FileType foundFile;
    // if path is abs, to start search from root
    if (!this.isPathRelative(path)) {
      foundFile = this.parseDirectory("File", root, path);
    } else {
      // get the file at given path
      foundFile = this.parseDirectory("File", currDir, path);
    }
    // if file exists
    if (foundFile != null) {
      // split contents of file by newline character
      String[] contents = ((File) foundFile).contents().split("\n");
      // get the lines that match the regex
      result = this.matchRegex(contents, regex, path);
    } else {
      System.out.println("Given File path does not exist");
      result = null;
    }
    return result;
  }

  /**
   * Checks the path given for any file that has a mathcing regex patter
   * 
   * @param path wanted file or directory to check
   * @param regex the matching pattern
   * @return String indicating which files have matching patterns
   */
  public String recursiveGrepFile(String path, String regex) {
    // obtain the file with the given path and check if it's a directory
    FileType file = this.obtainStartFile(path);
    // check if the file exists
    if (file == null) {
      System.out.println("Invalid Path");
      return "";
    } else if (file.equalsType("file.File")) {
      // if it is of type File then simply just use the normal grep
      return this.grepFile(path, fs.getWorkingDir(), fs.getRoot(), regex);
    } else {
      // call recursiveGrepHelper
      return this.recursiveGrepHelper((Directory) file, path, regex, "");
    }
  }

  /**
   * Acts as helper for recursiveGrep carries out the main functionality
   * traversing through everr file and subdirectory to check for matching
   * pattern
   * 
   * @param startDir the original directory passed in
   * @param path the current path to what directory you're in
   * @param regex the pattern to match
   * @param res the current resultant string
   * @return the full string indicating what lines match
   */
  private String recursiveGrepHelper(Directory startDir, String path,
      String regex, String res) {
    // loop through the entire contents of the current directory
    FileType currFile;
    String newPath;
    for (int i = 0; i < startDir.numFile(); i++) {
      currFile = startDir.getFile(i);
      newPath = path + "/" + currFile.getFileName();
      // check if the current file is a File or Directory
      if (currFile.equalsType("file.File")) {
        // if the current file is a File then check if it matches the pattern
        String[] contents = ((File) currFile).contents().split("\n");
        res += this.matchRegex(contents, regex, newPath);
      } else {
        // recursively go through the directory
        res =
            this.recursiveGrepHelper((Directory) currFile, newPath, regex, res);
      }
    }
    return res;
  }

  /**
   * Obtains the starting file with a given path check for a file or directory
   * at the path
   * 
   * @param path the path to the wanted file
   * @return the wanted file object or null if it doesn' exist
   */
  private FileType obtainStartFile(String path) {
    FileType file;
    if (this.isPathRelative(path)) {
      // if the path is relative search in the working dir
      file = this.parseDirectory("Directory", fs.getWorkingDir(), path);
      if (file == null) {
        file = this.parseDirectory("File", fs.getWorkingDir(), path);
      }
    } else {
      // check if given path is /
      if (path.equals("/")) {
        file = fs.getRoot();
      } else {
        file = this.parseDirectory("Directory", fs.getRoot(), path);
        if (file == null) {
          file = this.parseDirectory("File", fs.getWorkingDir(), path);
        }
      }

    }
    return file;
  }

  /**
   * Checks to see what strings in file match a given rgex
   * 
   * @param strings the list of strings to check
   * @param regex the regex to match
   * @param path the path to the file being checked
   * @return string indicating what lines match
   */
  private String matchRegex(String[] strings, String regex, String path) {
    // create an instance of patter and matcher
    p = Pattern.compile(regex);
    String res = "";
    // loop through every string in the list
    for (int i = 0; i < strings.length; i++) {
      m = p.matcher(strings[i]);
      // check if the regex exists in the string
      if (m.find()) {
        // if it exists add to the result
        res += path + ":" + i + ":" + strings[i] + "\n";
      }
    }
    return res;
  }

  /**
   * gets the regex from the parametes and last index in parameters where the
   * regex was
   * 
   * @param params the list of paramaters to execute the command
   * @param startIndex the first index of regex
   * @return array with 0 being regex and 1 being last index
   */
  private Object[] getRegexAndLastIndex(String[] params, int startIndex) {
    Object[] result = new Object[2];
    int i = startIndex;
    boolean quoteFound = false;
    // first check if both quotes are in 1 index
    if (params[i].charAt(0) == '\"'
        && params[i].charAt(params[i].length() - 1) == '\"') {
      // if it is then return the string between the quotes
      result[0] = params[i].substring(1, params[i].length() - 1);
      result[1] = i;
      return result;
    } else if (params[i].charAt(0) == '\"') {
      // if only the first character at the given index is a quote then loop
      // through until you find the second quote
      String ret;
      ret = params[i].substring(1);
      result[1] = i;
      i++;
      while (!quoteFound && i < params.length) {
        // first check if the string at the current index ends with a "
        if (params[i].charAt(params[i].length() - 1) == '\"') {
          quoteFound = true;
        } else {
          ret += params[i];
          result[1] = i;
          i++;
        }
      }
      // if quote not found or the index at the end of the params return null
      if (!quoteFound || i == params.length - 1) {
        return null;
      } else {
        // otherwise return the result
        result[0] = ret;
        return result;
      }
    } else {
      // if theres no quote in the beginning index there instantly is no valid
      // regex
      return null;
    }

  }


  /**
   * executes the grep command
   * 
   * @param params the parameters to run grep
   * @return the string indicating what lines match for grep
   */
  public String execute(String[] params) {
    String result = null;
    ValidityCheckGrep test = new ValidityCheckGrep();
    if (test.validate(params)) {
      if (params[1].equals("-R")) {
        // obtain the regex
        result = this.executeRecursiveGrep(params);
      } else {
        // obtain the regex
        result = this.executeGrep(params);
      }
    } else {
      System.out.print("Invalid command, Please try again");
      return null;
    }
    return result;
  }

  /**
   * executes grep command without recursive
   * 
   * @param params the parameters to run grep
   * @returnthe string indicating what lines match for grep
   */
  private String executeGrep(String[] params) {
    String result = "";
    // obtain the regex
    Object[] regexAndIndex = this.getRegexAndLastIndex(params, 1);
    if (regexAndIndex != null) {
      for (int i = (Integer) regexAndIndex[1] + 1; i < params.length; i++) {
        result += this.grepFile(params[i], fs.getWorkingDir(), fs.getRoot(),
            (String) regexAndIndex[0]);
      }
    } else {
      System.out.println("Invalid Command, please try again");
      return null;
    }
    return result;
  }

  /**
   * executes grep command with recursive
   * 
   * @param params the parameters to run grep
   * @returnthe string indicating what lines match for grep
   */

  private String executeRecursiveGrep(String[] params) {
    String result = "";
    // obtain the regex
    Object[] regexAndIndex = this.getRegexAndLastIndex(params, 2);
    if (regexAndIndex != null) {
      for (int i = (Integer) regexAndIndex[1] + 1; i < params.length; i++) {
        result += this.recursiveGrepFile(params[i], (String) regexAndIndex[0]);
      }
    } else {
      System.out.println("Invalid Command, please try again");
      return null;
    }
    return result;
  }

}
