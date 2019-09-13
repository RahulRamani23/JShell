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
import commands.EchoAppend;
import org.junit.Before;
import org.junit.Test;
import file.*;

public class EchoAppendTest {
  
  /**
   * EchoAppend instance for testing
   */
  private EchoAppend e;
  
  /**
   * FileSystem instance for testing
   */
  private FileSystem fs;
  
  @Before
  public void setUp(){
    fs = new FileSystem();
    e = new EchoAppend(fs);
    String[] params = {">>", "file"};
    String content = "Test\n";
    e.run(params, content);
  }

  @Test
  public void testAppendsToNonExistentFile() {
    Directory root = fs.getRoot();
    int find = root.find("file");
    File f = (File) root.getFile(find);
    assertEquals("Test\n", f.contents());
  }
  
  @Test
  public void testAppendsToExistingFile() {
    String[] params = {">>", "file"};
    String content = "Test\n";
    e.run(params, content);
    Directory root = fs.getRoot();
    int find = root.find("file");
    File f = (File) root.getFile(find);
    assertEquals("Test\nTest\n", f.contents());
  }
  
  @Test
  public void testAppendDoesNotCreateFileWhenPassedNull() {
    String[] params = {">>", "file2"};
    String content = null;
    e.run(params, content);
    Directory root = fs.getRoot();
    int find = root.find("file2");
    assertEquals(-1, find);
  }
  
  @Test
  public void testAppendDoesNotChangeFileWhenPassedBlankString() {
    String[] params = {">>", "file"};
    String content = "";
    e.run(params, content);
    Directory root = fs.getRoot();
    int find = root.find("file");
    File f = (File) root.getFile(find);
    assertEquals("Test\n", f.contents());
  }
  
  @Test
  public void testAppendWithAbsolutePath() {
    String[] params = {">>", "\file2"};
    String content = "Test\n";
    e.run(params, content);
    Directory root = fs.getRoot();
    int find = root.find("file");
    File f = (File) root.getFile(find);
    assertEquals("Test\n", f.contents());
  }

}
