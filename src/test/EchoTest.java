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
import commands.Echo;

public class EchoTest {
  
  // Create the echo object
  Echo echoObject = new Echo();

  @Test
  public void testValidCase() {
    
    // Array to hold the command and argument
    String[] commands = {"echo", "\"I", "like", "rice\""};
    
    assertEquals("I like rice\n", echoObject.execute(commands));
  }
  
  @Test
  public void testInvalidMissingQuote() {
    
    // Array to hold the command and argument
    String[] commands = {"echo", "\"I", "like", "rice"};
    
    assertEquals("", echoObject.execute(commands));
  }
  
  @Test
  public void testInvalidNoQuotes() {
    
    // Array to hold the command and argument
    String[] commands = {"echo", "I", "like", "rice"};
    
    assertEquals("", echoObject.execute(commands));
  }
  
  @Test
  public void testInvalidExcesiveQuotes() {
    
    // Array to hold the command and argument
    String[] commands = {"echo", "\"I",  "like\"", "rice\""};
    
    assertEquals("", echoObject.execute(commands));
  }

}
