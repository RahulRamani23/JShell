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

/**
 * This class represents a simple text file
 *
 */

public class File extends FileType { 
    /**
     * The contents of the text file
     */
    private String contents;
    /**
     * Constructor
     * @param name: The desired name for the File
     */
    public File(String name) {
        contents = "";
        fileName = name;
    }
    
    /**
     * Writes more content to the file without deleting what content was
     * already present
     * @param add: Desired String to add to the File
     */
    public void append(String add) {
      this.contents = this.contents + add;
    }
    
    /**
     * Overwrites the entire file, all content inside will be lost
     * @param write: Desired String to overwrite the File with
     */
    public void rewrite(String write) {
      this.contents = write;
    }
    
    /**
     * Returns the contents of the File in a String format
     * @return: contents of the String
     */
    public String contents() {
      return this.contents;
    }
    
    /**
     * When called returns an object that is different than <this> but has
     * the same fileName and contents
     * @return File that has same name and contents
     */
    public File copy() {
      File ret = new File(this.fileName);
      ret.append(this.contents);
      return ret;
    }
}
