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
import org.junit.Before;
import org.junit.Test;
import commands.Pwd;
import file.Directory;

public class PwdTest {
  Pwd pwd;

  @Before
  public void setUp() throws Exception {
    pwd = new Pwd();
  }

  @Test
  public void testPwdReturnsCorrectDirectory() {
    assertEquals("/", pwd.pwd());
  }

  @Test
  public void testExecuteReturnCorrectPathWithValidCommand() {
    String[] params = {"pwd"};
    assertEquals("/\n", pwd.execute(params));
  }

  @Test
  public void testExecuteReturnsNullInvalidCommand() {
    String[] params = {"pwd", "asda"};
    assertEquals(null, pwd.execute(params));
  }

  @Test
  public void testChangeDirChangesToCorrectDir() {
    Directory testDir = new Directory("a", null);
    pwd.dirChange(testDir);
    assertEquals("/a", pwd.pwd());

  }

  @Test
  public void testUpDirChangesDirectoryUpOneTime() {
    Directory testDir = new Directory("a", null);
    pwd.dirChange(testDir);
    pwd.upDir(1);
    assertEquals("/", pwd.pwd());
  }
  
  @Test 
  public void testUpDirChangesDirectoryUpMultipleTimes() {
    Directory testDir = new Directory("a", null);
    pwd.dirChange(testDir);
    pwd.dirChange(testDir);
    pwd.dirChange(testDir);
    pwd.upDir(3);
    assertEquals("/", pwd.pwd());
  }
  
  @Test
  public void testRootChangesPathToRootSingleDir() {
    Directory testDir = new Directory("a", null);
    pwd.dirChange(testDir);
    pwd.root();
    assertEquals("/", pwd.pwd());
  }
  @Test
  public void testRootChangesPathToRootMultipleDir() {
    Directory testDir = new Directory("a", null);
    pwd.dirChange(testDir);
    pwd.dirChange(testDir);
    pwd.dirChange(testDir);
    pwd.dirChange(testDir);
    pwd.root();
    assertEquals("/", pwd.pwd());
  }

}
