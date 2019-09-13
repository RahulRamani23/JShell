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
package file;

import java.util.ArrayList;

/**
 * This class represents a directory that stores other FileType objects
 *
 */

public class Directory extends FileType {
  /**
   * The contents of the directory
   */
  private ArrayList<FileType> contents;

  /**
   * The directory that contains me
   */
  private Directory parent;

  /**
   * Instantiates the Directory object
   * 
   * @param name name of the directory
   * @param p Parent directory, null if no parent
   */
  public Directory(String name, Directory p) {
    this.contents = new ArrayList<FileType>();
    fileName = name;
    this.parent = p;
  }

  /**
   * Add FileTypes into the directory (Directories or Files)
   * 
   * @param newContent the content that needs to be added to the directory
   */
  public void append(FileType newContent) {
    this.contents.add(newContent);
  }

  /**
   * Remove the FileType in the directory at the given index
   * 
   * @param index index of FileType to be removed
   */
  public void delete(int index) {
    this.contents.remove(index);
  }

  /**
   * Obtain the FileType at the given index <index>
   * 
   * @param index index of desired FileType
   * @return the requested FileType
   */
  public FileType getFile(int index) {
    return this.contents.get(index);
  }

  /**
   * Obtain the number of files contained inside the directory
   * 
   * @return number of files in the this directory
   */
  public int numFile() {
    return this.contents.size();
  }

  /**
   * Returns the position of the file or directory named findFile, returns -1 if
   * file does not exist
   * 
   * @param findFile name of file or directory to be found
   * @return location of file
   */
  public int find(String findFile) {
    int i = 0, index = -1, size = this.contents.size();
    // loop to find the desired FileType
    while (i < size && index == -1) {
      FileType f = this.contents.get(i);
      // obtain the index of the FileType if it has the right name
      if (f.getFileName().equals(findFile)) {
        index = i;
      } else {
        i++;
      }
    }
    return index;
  }

  /**
   * Returns the parent Directory of the object
   * 
   * @return object's parent Directory
   */
  public Directory getParent() {
    return this.parent;
  }

  /**
   * Returns a copy of the Directory, with a new parent that is given by
   * newParent
   * 
   * @param newParent: the new parent for the copied directory
   * @return
   */
  public Directory copy(Directory newParent) {
    // new directory for return
    Directory ret = new Directory(this.fileName, newParent);
    // copy all contents of this directory to the new directory
    for (FileType i : this.contents) {
      // if we have another directory
      if (i.equalsType("file.Directory")) {
        // copy that directory and append it to our copy
        Directory curr = (Directory) i;
        ret.append(curr.copy(ret));
      } else {
        // otherwise we have a file, copy it and append it to new directory
        File curr = (File) i;
        curr = curr.copy();
        ret.append(curr);
      }
    }
    return ret; // return the copied directory

  }

  /**
   * Returns whether the Directories <this> and <comp> are equal, which means
   * they have the same contents, name and parent.
   * @param comp directory to compare <this> to
   * @return true iff <this> equals <contents>
   */
  public boolean equals(Directory comp) {
    return this.contents.equals(comp.contents)
        && this.fileName.equals(comp.fileName) && this.parent == comp.parent;
  }
}
