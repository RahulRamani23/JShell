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

import file.*;

/**
 * This class represents the base of most commands in which they will always
 * have the ability to check a given path and be able to parse through a
 * directory
 *
 */
public class PathCheck {

  /***
   * Given a path this method will check if a path is relative or absolute
   * 
   * @param path: the current working path to deal with
   * @return a boolean value stating if the current path is relative or not
   */
  public boolean isPathRelative(String path) {
    // If path is relative it will not start with a forward slash therefore just
    // check if it has a forward slash
    return (path.charAt(0) != '/');
  }

  /**
   * Given a path, if the path is relative returns the absolute path, otherwise
   * returns the unchanged absolute path
   * 
   * @param path: path that needs to be an absolute path
   * @param curr_work_path: path of current working directory
   * @return absolute path
   */
  public String generate_fullpath(String path, String curr_working_path) {
    // if path is relative, then concatenate the path of current working
    // directory with the relative path
    if (this.isPathRelative(path)) {
      // if curr working dir is root, no need for separator
      if (curr_working_path.equals("/")) {
        path = curr_working_path + path;
      } else {
        path = curr_working_path + "/" + path;
      }
    }
    return path;
  }

  /**
   * This method parses through a given directory looking for a sepcific file or
   * directory within that directory
   * 
   * @param searchType Type of file you are looking for i.e Directory or File
   * @param currDir The current working directory you're searching for the file
   *        in
   * @param searchDir the path of the directory you are looking for
   * @return the address to the directory you are looking for or if the
   *         directory doesn't exist then returns null
   */
  public FileType parseDirectory(String searchType, Directory currDir,
      String searchDir) {
    // check if the search dir is absolute or relative and initialize a
    // directories array
    String[] Directories;
    // first check if the input is just /
    if (searchDir.matches("/+") && searchType.equals("Directory")) {
      // if it is just return the currDir
      return currDir;
    }else if(searchDir.matches("/+")){
      return null;
    }
    // remove all trailing /'s from the file path to avoid errors
    searchDir = searchDir.replaceAll("/+$", "");
    if (this.isPathRelative(searchDir)) {
      // if it is relative simply split the file path with / as separators
      Directories = searchDir.split("/+");
    } else {
      // if it's absolute get rid of the leading / so the first index of the
      // array isn't blank when splitting

      Directories = searchDir.replaceAll("^/+", "").split("/+");

    }
    // use a helper method to actually get to the wanted file or directory
    return parseDirectoryHelper(searchType, currDir, Directories, 0);
  }

  /**
   * This method acts as a helper for parseDirectory and takes care of the
   * actual functionality for parseDirectory
   * 
   * @param searchType Type of file you are looking for i.e Directory or File
   * @param currDir The current working directory you're searching for the file
   *        in
   * @param Directories an array of all the names of the original directories in
   *        order
   * @param the index in Directories corresponding to the current directory
   *        we're looking at
   * @return the address to the directory you are looking for or if the
   *         directory doesn't exist then returns null
   */
  private FileType parseDirectoryHelper(String searchType, Directory currDir,
      String[] Directories, int index) {
    // check if the directory at current directory is .. or . indicating that
    // the parent directory or the current directory itself is to be searched
    if (Directories[index].equals("..") || Directories[index].equals(".")) {
      // if it does then check if the end of the directories list has been
      // reached and ensure they're looking for a file of type Directory

      if (index == Directories.length - 1 && searchType.equals("Directory")) {
        // then simply return the desired directory
        if (Directories[index].equals("..")) {
          return currDir.getParent();
        } else {
          return currDir;
        }
      } else if (index == Directories.length - 1) {
        // if the directories has reached the last directory however they are
        // looking for a file the file name .. is invalid so return null
        return null;
      } else {
        // if the end of the list has not been reached then just recursively
        // call parseDirectory helper sending in the correct directory
        if (Directories[index].equals("..")) {
          return this.parseDirectoryHelper(searchType, currDir.getParent(),
              Directories, index + 1);
        } else {
          return this.parseDirectoryHelper(searchType, currDir, Directories,
              index + 1);
        }
      }
    } else {
      // check if the directory at the current directory in directories at
      // index exists in the current working directory
      int indexOfDir = currDir.find(Directories[index]);
      // if it doesn't exist then return null
      if (indexOfDir == -1) {
        return null;
      } else if (index == Directories.length - 1
          && currDir.getFile(indexOfDir).equalsType("file." + searchType)) {
        // if the file/directory does exist and the current index is the last
        // element in directories then the wanted directory has been found
        return currDir.getFile(indexOfDir);
      } else if (index == Directories.length - 1) {
        // if the directory does exist but the file isn't the correct type then
        // return null
        return null;
      } else if (!(currDir.getFile(indexOfDir).equalsType("file.Directory"))) {
        // if we aren't at the end of directories and a file was found and if
        // that file is not a directory then return null
        return null;
      } else {
        // if the directory with the correct name was found and it is of Type
        // directory and we haven't reached the end of directories do a
        // recursive call
        return this.parseDirectoryHelper(searchType,
            (Directory) currDir.getFile(indexOfDir), Directories, index + 1);

      }
    }
  }

}
