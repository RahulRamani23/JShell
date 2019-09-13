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
import file.*;
import commands.EchoCreate;

public class EchoCreateTest {

  /**
   * EchoCreate instance for testing
   */
  private EchoCreate e;
  
  /**
   * FileSystem for testing
   */
  private FileSystem fs;
  
  @Before
  public void setUp(){
    fs = new FileSystem();
    e = new EchoCreate(fs);
    String[] params = {">", "file"};
    String content = "Test\n";
    e.run(params, content);
  }

  @Test
  public void testWritesToNonExistentFile() {
    Directory root = fs.getRoot();
    int find = root.find("file");
    File f = (File) root.getFile(find);
    assertEquals("Test\n", f.contents());
  }
  
  @Test
  public void testOverWritesToExistingFile() {
    String[] params = {">", "file"};
    String content = "Overwritten\n";
    e.run(params, content);
    Directory root = fs.getRoot();
    int find = root.find("file");
    File f = (File) root.getFile(find);
    assertEquals("Overwritten\n", f.contents());
  }
  
  @Test
  public void testWriteDoesNotCreateFileWhenPassedNull() {
    String[] params = {">", "file2"};
    String content = null;
    e.run(params, content);
    Directory root = fs.getRoot();
    int find = root.find("file2");
    assertEquals(-1, find);
  }

  @Test
  public void testWriteClearsFileWhenPassedBlankString() {
    String[] params = {">>", "file"};
    String content = "";
    e.run(params, content);
    Directory root = fs.getRoot();
    int find = root.find("file");
    File f = (File) root.getFile(find);
    assertEquals("", f.contents());
  }
  
  @Test
  public void testCreateWithAbsolutePath() {
    String[] params = {">", "\file2"};
    String content = "Test\n";
    e.run(params, content);
    Directory root = fs.getRoot();
    int find = root.find("file");
    File f = (File) root.getFile(find);
    assertEquals("Test\n", f.contents());
  }
}
