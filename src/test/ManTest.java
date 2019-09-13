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

package test;

import static org.junit.Assert.*;
import org.junit.Test;
import commands.Man;

public class ManTest {
  
  // Create the man object
  Man manObject = new Man();


  @Test
  public void testValidCommand() {
    // Array of commands
    String[] commands = {"man", "cat"};
    
    assertEquals("\n\t\t -- CSCB07 JShell Man Pages -- \t\t\n\n"
        + "NAME\n\tcat -- display contents of file\n\nSYNOPSIS\n\tcat "
        + "file_name\n\nDESCRIPTION\n\tThe cat utility is designed to"
        + " print out to the shell the contents of the given files. \n"
        + "\tGiven multiple paths, the output of file contents is "
        + "separated using three newline characters. \n\nEXAMPLES\n\t"
        + "The following will display the contents of RiceBoy.txt on "
        + "the shell.\n\n\t\tcat Riceboy.txt\n\n\n\t\t-- Created on "
        + "June 29, 2018 --\t\t\n", manObject.execute(commands));
  }
  
  @Test
  public void testInvalidCommand() {
    // Array of commands
    String[] commands = {"man", "yolo"};
    
    assertEquals("", manObject.execute(commands));
  }

}
