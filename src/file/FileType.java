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
 * abstract data type, that acts as base for any kind of file
 *
 */

public abstract class FileType {
    /**
     * The name given to the file
     */
    protected String fileName;
    
    /**
     * Returns the name that was given to the FileType
     * 
     * @return the name of the FileType
     */
    public String getFileName() {
      return this.fileName;
    }
    
    /**
     * Sets the files name to the given name
     * @param name The new name
     */
    public void setFileName(String name) {
      this.fileName = name;
    }
    
    /**
     * Returns true if the name of this matches the given className
     * ie True if <this> is a FileType and <className> = "file.FileType"
     * @param className string of class name to test
     * @return True if <this> class name matches className
     */
    public boolean equalsType(String className) {
      return className.equals(this.getClass().getName());
    }
}
