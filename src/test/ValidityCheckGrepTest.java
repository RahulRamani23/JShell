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
import verify.command.ValidityCheckGrep;

public class ValidityCheckGrepTest {
  
  ValidityCheckGrep valid = new ValidityCheckGrep();
  

  @Test
  public void testValidCase() {
    String[] commands = {"grep","REGEX","Pathway"};
    assertTrue(valid.validate(commands));
  }
  
  @Test
  public void testValidCaseRecursive() {
    String[] commands = {"grep","-R", "REGEX","Pathway"};
    assertTrue(valid.validate(commands));
  }
  
  @Test
  public void testInValidNoPath() {
    String[] commands = {"grep","REGEX"};
    assertFalse(valid.validate(commands));
  }
  
  @Test
  public void testInValidRecursive() {
    String[] commands = {"grep", "-J", "REGEX", "PATH"};
    assertFalse(valid.validate(commands));
  }

}
