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
package Stack;

import java.util.ArrayList;

/**
 * This class represents the stack container with LIFO functionality
 *
 */
public class Stack {
  /**
   * The contents of the stack
   */
  private ArrayList<Object> contents;

  /**
   * instantiates the stack object
   */
  public Stack() {
    // instantiate the array list
    contents = new ArrayList<Object>();
  }

  /**
   * This object inserts an object to the top of the stack
   * 
   * @param obj object to be added to the stack
   */
  public void push(Object obj) {
    // insert the given object onto the top of the stack
    contents.add(obj);
  }

  /**
   * Removes an object of the top of the stack
   * 
   * @return the object at the top of the stack
   */
  public Object pop() {
    // store the item at the top of the stack then remove it
    Object item = contents.get(contents.size() - 1);
    contents.remove(contents.size() - 1);
    return item;
  }

  /**
   * This method gives the size of the stack
   * 
   * @return the amount of objects in the stack
   */
  public int size() {
    int size = contents.size();
    return size;
  }
}
