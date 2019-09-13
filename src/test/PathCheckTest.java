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
import commands.PathCheck;
import file.*;

public class PathCheckTest {
  private PathCheck pc;

  @Before
  public void setUp() throws Exception {
    pc = new PathCheck();
  }

  @Test
  public void testIsPathRelativeWithRelativePath() {
    assertTrue(pc.isPathRelative("asdas"));
  }

  @Test
  public void testIsPathRelativeWithAbsoltuePath() {
    assertFalse(pc.isPathRelative("/asdas"));
  }

  @Test
  public void testIsPathRelativeWithRootAsPath() {
    assertFalse(pc.isPathRelative("/"));
  }

  @Test
  public void testParseDirectoryReturnsRootWhenPathIsRoot() {
    Directory root = new Directory("/", null);
    assertEquals(root, pc.parseDirectory("Directory", root, "/"));
  }

  @Test
  public void testParseDirectoryReturnsCorrectDirWithAbsolutePath() {
    Directory root = new Directory("/", null);
    Directory test = new Directory("test", root);
    root.append(test);
    assertEquals(test, pc.parseDirectory("Directory", root, "/test"));
  }

  @Test
  public void testParseDirectoryReturnsCorrectDirWithRelativePath() {
    Directory root = new Directory("/", null);
    Directory test1 = new Directory("test1", root);
    Directory test2 = new Directory("test2", test1);
    root.append(test1);
    test1.append(test2);
    assertEquals(test2, pc.parseDirectory("Directory", test1, "test2"));
  }

  @Test
  public void testParseDirectoryReturnsCorrectDirWithRelativePathCurrDirRoot() {
    Directory root = new Directory("/", null);
    Directory test1 = new Directory("test1", root);
    Directory test2 = new Directory("test2", test1);
    root.append(test1);
    test1.append(test2);
    assertEquals(test2, pc.parseDirectory("Directory", root, "test1/test2"));
  }

  @Test
  public void testParseDirectoryReturnsParentDirWithTwoDotsAsPath() {
    Directory root = new Directory("/", null);
    Directory test1 = new Directory("test1", root);
    root.append(test1);
    assertEquals(root, pc.parseDirectory("Directory", test1, ".."));
  }

  @Test
  public void testParseDirectoryReturnsSameDirWithOneDotAsPath() {
    Directory root = new Directory("/", null);
    Directory test1 = new Directory("test1", root);
    root.append(test1);
    assertEquals(test1, pc.parseDirectory("Directory", test1, "."));
  }

  @Test
  public void testParseDirectoryReturnsSameDirWithOneDotInPath() {
    Directory root = new Directory("/", null);
    Directory test1 = new Directory("test1", root);
    Directory test2 = new Directory("test2", test1);
    root.append(test1);
    test1.append(test2);
    assertEquals(test2,
        pc.parseDirectory("Directory", root, "test1/./test2/."));
  }

  @Test
  public void testParseDirectoryReturnsSameDirWithTwoDotsInPath() {
    Directory root = new Directory("/", null);
    Directory test1 = new Directory("test1", root);
    Directory test2 = new Directory("test2", test1);
    root.append(test1);
    test1.append(test2);
    assertEquals(test1,
        pc.parseDirectory("Directory", root, "test1/./test2/.."));
  }

  @Test
  public void testParseDirectoryReturnsNullInvalidAbsPathSearchForDir() {
    Directory root = new Directory("/", null);
    assertEquals(null, pc.parseDirectory("Directory", root, "/test"));
  }

  @Test
  public void testParseDirectoryReturnsNullInvalidRelativePathSearchForDir() {
    Directory root = new Directory("/", null);
    assertEquals(null, pc.parseDirectory("Directory", root, "test"));
  }

  @Test
  public void testParseDirectoryRetNullWhenSearchForDirectorySameNameAsFile() {
    Directory root = new Directory("/", null);
    File test1 = new File("test1");
    root.append(test1);
    assertEquals(null, pc.parseDirectory("Directory", root, "test1"));
  }
  

  @Test
  public void testParseDirectoryReturnsCorrectFileWithAbsolutePath() {
    Directory root = new Directory("/", null);
    File test = new File("test");
    root.append(test);
    assertEquals(test, pc.parseDirectory("File", root, "/test"));
  }

  @Test
  public void testParseDirectoryReturnsCorrectFileWithRelativePath() {
    Directory root = new Directory("/", null);
    Directory test1 = new Directory("test1", root);
    File test2 = new File("test2");
    root.append(test1);
    test1.append(test2);
    assertEquals(test2, pc.parseDirectory("File", test1, "test2"));
  }

  @Test
  public void testParseDirectoryReturnsCorrectFileWithRelativePathCurrDirRoot() {
    Directory root = new Directory("/", null);
    Directory test1 = new Directory("test1", root);
    File test2 = new File("test2");
    root.append(test1);
    test1.append(test2);
    assertEquals(test2, pc.parseDirectory("File", root, "test1/test2"));
  }

  @Test
  public void testParseDirectoryReturnsSameFileWithOneDotInPath() {
    Directory root = new Directory("/", null);
    Directory test1 = new Directory("test1", root);
    File test2 = new File("test2");
    root.append(test1);
    test1.append(test2);
    assertEquals(test2,
        pc.parseDirectory("File", root, "test1/./test2/"));
  }

  @Test
  public void testParseDirectoryRetNullWhenSearchForFileSameNameAsDir() {
    Directory root = new Directory("/", null);
    Directory test1 = new Directory("test1", root);
    root.append(test1);
    assertEquals(null, pc.parseDirectory("File", root, "test1"));
  }
  @Test
  public void testGenerateFulPathFromRoot() {
    assertEquals("/b/c", pc.generate_fullpath("b/c", "/"));
  }
  
  @Test
  public void testGenerateFullPathFromChild() {
    assertEquals("/a/b/c", pc.generate_fullpath("b/c", "/a"));
  }



}
