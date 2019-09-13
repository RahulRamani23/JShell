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
import commands.Ls;
import commands.MakeDirectory;
import file.FileSystem;

public class LsTest {
  private Ls ls;
  private FileSystem fs;
  private MakeDirectory mkdir;

  @Before
  public void setUp() throws Exception {
    fs = new FileSystem();
    ls = new Ls(fs);
    mkdir = new MakeDirectory(fs);
    String[] cmds = {"mkdir", "a"};
    mkdir.execute(cmds);
    String[] cmds1 = {"mkdir", "a/b"};
    mkdir.execute(cmds1);
    String[] cmds2 = {"mkdir", "a/b/c"};
    mkdir.execute(cmds2);
    String[] cmds3 = {"mkdir", "a/b/d"};
    mkdir.execute(cmds3);
  }

  @Test
  public void testLsCurrDirr() {
    assertEquals("a\n", ls.LsWorkingDir(fs.getWorkingDir()));
  }

  @Test
  public void testLsRoot() {
    String[] params = {"/"};
    assertEquals("/: a\n",
        ls.ls(fs.getRoot(), fs.getWorkingDir(), params, fs.getWorkingPath()));
  }

  @Test
  public void testLsChildAbsPath() {
    String[] params = {"/a"};
    assertEquals("/a: b\n",
        ls.ls(fs.getRoot(), fs.getWorkingDir(), params, fs.getWorkingPath()));
  }

  @Test
  public void testLsInvalidPath() {
    String[] params = {"invalid"};
    assertEquals("/invalid: Provided path does not exist \n",
        ls.ls(fs.getRoot(), fs.getWorkingDir(), params, fs.getWorkingPath()));
  }

  @Test
  public void testLsExecute() {
    String[] params = {"ls",};
    assertEquals("a\n", ls.execute(params));
  }

  @Test
  public void testRecursiveLsReturnsCorrectTreeWithRootDirAsRoot() {
    String expected = "/\n    a\n        b\n            c\n            d\n";
    assertEquals(expected, ls.recursiveLs("/"));
  }

  @Test
  public void testRecursiveLsReturnsCorrectTreeWithRootAsSubDir() {
    String expected = "b\n    c\n    d\n";
    assertEquals(expected, ls.recursiveLs("/a/b"));
  }

  @Test
  public void testRecursiveLsReturnsCorrectTreeWithRootAsSubDirRelativePath() {
    String expected = "a\n    b\n        c\n        d\n";
    assertEquals(expected, ls.recursiveLs("a"));
  }



}
