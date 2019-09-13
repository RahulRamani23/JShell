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
import commands.MakeDirectory;
import file.*;

public class MakeDirectoryTest {
  FileSystem fs;
  MakeDirectory mkdir;
  Directory b;

  @Before
  public void setUp() throws Exception {
    fs = new FileSystem();
    mkdir = new MakeDirectory(fs);
    b = new Directory("b", fs.getRoot());
    fs.getRoot().append(b);
  }

  @Test
  public void testExecuteReturnsNullValidCommand() {
    String[] params = {"mkdir", "a"};
    assertEquals(null, mkdir.execute(params));
  }

  @Test
  public void testExecuteReturnsNullInvalidCommand() {
    String[] params = {"mkdir"};
    assertEquals(null, mkdir.execute(params));
  }

  @Test
  public void testExecuteValidSingleDirectory() {
    String[] params = {"mkdir", "a"};
    mkdir.execute(params);
    int expected = 1;
    int actual = fs.getRoot().find("a");
    assertEquals(expected, actual);
  }

  @Test
  public void testExecuteValidSingleDirectoryAbsPath() {
    String[] params = {"mkdir", "/a"};
    mkdir.execute(params);
    int expected = 1;
    int actual = fs.getRoot().find("a");
    assertEquals(expected, actual);
  }

  @Test
  public void testExecuteValidMultiDirectory() {
    String[] params = {"mkdir", "a", "c"};
    mkdir.execute(params);
    boolean expected =
        (1 == fs.getRoot().find("a")) && 2 == fs.getRoot().find("c");
    assertTrue(expected);
  }

  @Test
  public void testExecuteMakeValidNestedDirectory() {
    String[] params = {"mkdir", "b/a"};
    mkdir.execute(params);
    boolean expected = (0 == b.find("a"));
    assertTrue(expected);
  }

  @Test
  public void testExecuteMakeInvalidDirName() {
    String[] params = {"mkdir", "@@@"};
    mkdir.execute(params);
    boolean expected = (-1 == fs.getRoot().find("@@@"));
    assertTrue(expected);
  }
  
  @Test
  public void testExecuteMakeDirectoryAlreadyExists() {
    String[] params = {"mkdir", "b"};
    mkdir.execute(params);
    fs.getRoot().delete(0);
    boolean expected = (-1 == fs.getRoot().find("b"));
    assertTrue(expected);
  }
  
  @Test
  public void testExecuteValidMultiDirectoryAbsPath() {
    String[] params = {"mkdir", "/a", "/c"};
    mkdir.execute(params);
    boolean expected =
        (1 == fs.getRoot().find("a")) && 2 == fs.getRoot().find("c");
    assertTrue(expected);
  }

  @Test
  public void testExecuteMakeValidNestedDirectoryAbsPath() {
    String[] params = {"mkdir", "/b/a"};
    mkdir.execute(params);
    boolean expected = (0 == b.find("a"));
    assertTrue(expected);
  }

  @Test
  public void testExecuteMakeInvalidDirNameAbsPath() {
    String[] params = {"mkdir", "/@@@"};
    mkdir.execute(params);
    boolean expected = (-1 == fs.getRoot().find("@@@"));
    assertTrue(expected);
  }
  
  @Test
  public void testExecuteMakeDirectoryAlreadyExistsAbsPath() {
    String[] params = {"mkdir", "/b"};
    mkdir.execute(params);
    fs.getRoot().delete(0);
    boolean expected = (-1 == fs.getRoot().find("b"));
    assertTrue(expected);
  }

}
