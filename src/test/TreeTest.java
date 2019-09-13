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
import commands.Tree;
import file.*;

public class TreeTest {
  private Tree testTree;
  private FileSystem fs;

  @Before
  public void setUp() throws Exception {
    fs = new FileSystem();
    testTree = new Tree(fs);
  }

  @Test
  public void testExecuteReturnsStringWithValidCommand() {
    String[] params = {"tree"};
    assertTrue(testTree.execute(params).getClass().equals(String.class));
  }

  @Test
  public void testExecuteReturnsNullWithInvalidCommand() {
    String[] params = {"tree", "asdas"};
    assertEquals(testTree.execute(params), null);
  }

  @Test
  public void testBuildTreeReturnsCorrectTreeOnlyRoot() {
    String expected = "/\n";
    assertEquals(expected, testTree.buildTree(fs.getRoot(), 0, ""));
  }

  @Test
  public void testBuildTreeReturnsCorrectTreeOneSubDir() {
    fs.getRoot().append(new Directory("a", fs.getRoot()));
    String expected = "/\n    a\n";
    assertEquals(expected, testTree.buildTree(fs.getRoot(), 0, ""));
  }

  @Test
  public void testBuildTreeReturnsCorrectTreeMultipleSubDirs() {
    fs.getRoot().append(new Directory("a", fs.getRoot()));
    fs.getRoot().append(new Directory("b", fs.getRoot()));
    String expected = "/\n    a\n    b\n";
    assertEquals(expected, testTree.buildTree(fs.getRoot(), 0, ""));
  }
  
  @Test
  public void testBuildTreeReturnsCorrectTreeNestedSubDirs() {
    Directory a = new Directory("a", fs.getRoot());
    Directory b = new Directory("b", fs.getRoot());
    fs.getRoot().append(a);
    fs.getRoot().append(b);
    a.append(new Directory("c", a));
    b.append(new Directory("d", b));
    String expected = "/\n    a\n        c\n    b\n        d\n";
    assertEquals(expected, testTree.buildTree(fs.getRoot(), 0, ""));
  }
  
  @Test
  public void testBuildTreeReturnsCorrectTreeMultipleNestedSubDirs() {
    Directory a = new Directory("a", fs.getRoot());
    Directory b = new Directory("b", fs.getRoot());
    fs.getRoot().append(a);
    fs.getRoot().append(b);
    a.append(new Directory("c", a));
    a.append(new Directory("e", a));
    b.append(new Directory("d", b));
    String expected = "/\n    a\n        c\n        e\n    b\n        d\n";
    assertEquals(expected, testTree.buildTree(fs.getRoot(), 0, ""));
  }
  

}
