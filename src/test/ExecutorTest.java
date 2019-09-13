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
import executor.Executor;
import file.*;

public class ExecutorTest {

  private Executor e;
  private FileSystem fs;
  private MockCommand mc;
  
  @Before
  public void setUp() {
    fs = new FileSystem();
    e = new Executor(fs);
    mc = new MockCommand();
  }

  @Test
  public void testPrintToFileWhenCommandReturnsContent() {
    String[] params = {"good", ">", "file"};
    e.executeCommand(mc, params);
    Directory root = fs.getRoot();
    int find = root.find("file");
    File f = (File) root.getFile(find);
    assertEquals("Test succeeded\n", f.contents());
  }
  
  @Test
  public void testAppendToFileThatAlreadyHasContent() {
    String[] params = {"good", ">>", "file"};
    e.executeCommand(mc, params);
    e.executeCommand(mc, params);
    Directory root = fs.getRoot();
    int find = root.find("file");
    File f = (File) root.getFile(find);
    assertEquals("Test succeeded\nTest succeeded\n", f.contents());
  }
  
  @Test
  public void testOverWriteFileThatAlreadyHasContent() {
    String[] params = {"good", ">", "file"};
    e.executeCommand(mc, params);
    String[] newParams = {"good", ">", "file"};
    e.executeCommand(mc, newParams);
    Directory root = fs.getRoot();
    int find = root.find("file");
    File f = (File) root.getFile(find);
    assertEquals("Test succeeded\n", f.contents());
  }
  
  @Test
  public void testFileNotCreatedWhenNoOutuputIsCreatedWithOverWrite() {
    String[] params = {"bad", ">", "file"};
    e.executeCommand(mc, params);
    Directory root = fs.getRoot();
    int find = root.find("file");
    assertEquals(-1, find);
  }
  
  @Test
  public void testFileNotCreatedWhenNoOutuputIsCreatedWithAppend() {
    String[] params = {"bad", ">>", "file"};
    e.executeCommand(mc, params);
    Directory root = fs.getRoot();
    int find = root.find("file");
    assertEquals(-1, find);
  }
  
  @Test
  public void testFileIsCreatedWithOverWriteWhenBlankOuputIsCreated() {
    String[] params = {"", ">", "file"};
    e.executeCommand(mc, params);
    Directory root = fs.getRoot();
    int find = root.find("file");
    assertEquals(0, find);
  }
  
  @Test
  public void testFileIsCreatedWithAppendWhenBlankOutputIsCreated() {
    String[] params = {"", ">>", "file"};
    e.executeCommand(mc, params);
    Directory root = fs.getRoot();
    int find = root.find("file");
    assertEquals(0, find);
  }
  
  @Test
  public void testFileWithContentIsClearedWhenOverwriteGetsBlankOutput() {
    String[] params = {"good", ">", "file"};
    e.executeCommand(mc, params);
    String [] newParams = {"", ">", "file"};
    e.executeCommand(mc, newParams);
    Directory root = fs.getRoot();
    int find = root.find("file");
    File f = (File) root.getFile(find);
    assertEquals("", f.contents());
  }

}
