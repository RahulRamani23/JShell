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
import commands.Cp;
import commands.Ls;
import file.Directory;
import file.File;
import file.FileSystem;

public class CpTest {
  private FileSystem fs;
  private Cp cp;
  private Ls ls;
  private Directory dirA;
  private Directory dirAB;
  private Directory dirABC;
  private Directory testdir;
  private File testfile1;
  private File testfile2;
  private File testfile3;

  @Before
  public void setUp() {
    fs = new FileSystem();
    dirA = new Directory("a", fs.getRoot());
    fs.getRoot().append(dirA);
    dirAB = new Directory("b", fs.getRoot());
    dirA.append(dirAB);
    dirABC = new Directory("c", fs.getRoot());
    dirAB.append(dirABC);
    testfile1 = new File("testfile1.txt");
    dirA.append(testfile1);
    testfile2 = new File("testfile2.txt");
    dirAB.append(testfile2);
    cp = new Cp(fs);
    ls = new Ls(fs);
    testdir = new Directory("testdir", fs.getRoot());
    testfile3 = new File("testfile3.txt");
    fs.getRoot().append(testdir);
    testdir.append(testfile3);

  }

  @Test
  public void testCopyFileToSameDirSameName() {
    String[] cmds = {"cp", "/testdir/testfile3.txt", "/testdir/testfile3.txt"};
    String[] cmdsLS = {"ls", "testdir"};
    assertEquals(null, cp.execute(cmds));
    assertEquals("testdir: testfile3.txt\n", ls.execute(cmdsLS));
  }

  @Test
  public void testCopyFileToSameDirDiffName() {
    String[] cmds = {"cp", "/testdir/testfile3.txt", "/testdir/DiffName.txt"};
    String[] cmdsLS = {"ls", "testdir"};
    assertEquals(null, cp.execute(cmds));
    assertEquals("testdir: testfile3.txt\n", ls.execute(cmdsLS));
  }

  @Test
  public void testCopyFileToRoot() {
    String[] cmds = {"cp", "/testdir/testfile3.txt", "/"};
    String[] cmdsLS = {"ls", "testdir"};
    String[] cmdsLSroot = {"ls"};
    assertEquals(null, cp.execute(cmds));
    assertEquals("testdir: testfile3.txt\n", ls.execute(cmdsLS));
    assertEquals("a\ntestdir\n", ls.execute(cmdsLSroot));
  }

  @Test
  public void testInvalidDestPath() {
    String[] cmds = {"cp", "/testdir/testfile3.txt", "/kjsfkjbw"};
    String[] cmdsLS = {"ls", "testdir"};
    String[] cmdsLSroot = {"ls"};
    assertEquals(null, cp.execute(cmds));
    assertEquals("testdir: testfile3.txt\n", ls.execute(cmdsLS));
    assertEquals("a\ntestdir\n", ls.execute(cmdsLSroot));
  }

  @Test
  public void testInvalidSrcPath() {
    String[] cmds = {"cp", "/testdir/klnslnfsf", "/"};
    String[] cmdsLS = {"ls", "testdir"};
    String[] cmdsLSroot = {"ls"};
    assertEquals(null, cp.execute(cmds));
    assertEquals("testdir: testfile3.txt\n", ls.execute(cmdsLS));
    assertEquals("a\ntestdir\n", ls.execute(cmdsLSroot));
  }


  //////////////////////////////////////////////
  @Test
  public void testCopyDirToSameDirSameName() {
    String[] cmds = {"cp", "/testdir", "/"};
    String[] cmdsLS = {"ls", "testdir"};
    assertEquals(null, cp.execute(cmds));
    assertEquals("testdir: testfile3.txt\n", ls.execute(cmdsLS));
  }

  @Test
  public void testCopyDirToSameDirDiffName() {
    String[] cmds = {"cp", "/testdir", "/DiffName"};
    String[] cmdsLS = {"ls", "testdir"};
    assertEquals(null, cp.execute(cmds));
    assertEquals("testdir: testfile3.txt\n", ls.execute(cmdsLS));
  }

  @Test
  public void testCopyDirToRoot() {
    String[] cmds = {"cp", "/a/b/c", "/"};
    String[] cmdsLS = {"ls", "a/b"};
    String[] cmdsLSroot = {"ls"};
    assertEquals(null, cp.execute(cmds));
    assertEquals("a/b: c testfile2.txt\n", ls.execute(cmdsLS));
    assertEquals("a\ntestdir\nc\n", ls.execute(cmdsLSroot));
  }


}
