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
import commands.ChangeDirectory;
import commands.MakeDirectory;
import file.Directory;
import file.File;
import file.FileSystem;

public class ChangeDirectoryTest {
  private ChangeDirectory cd;
  private FileSystem fs;
  private File testfile1;
  private MakeDirectory mkdir;
  private Directory d1;
  private Directory d2;
  private Directory d3;
  private Directory d4;
  
  @Before
  public void setUp() throws Exception {
    fs = new FileSystem();
    cd = new ChangeDirectory(fs);
    mkdir = new MakeDirectory(fs);
    String[] cmds = {"mkdir", "a"};
    mkdir.execute(cmds);
    String[] cmds1 = {"mkdir", "a/b"};
    mkdir.execute(cmds1);
    String[] cmds2 = {"mkdir", "a/b/c"};
    mkdir.execute(cmds2);
    String[] cmds3 = {"mkdir", "a/b/d"};
    mkdir.execute(cmds3);
    d1 = new Directory("d1", fs.getRoot());
    fs.getRoot().append(d1);
    d2 = new Directory("d2", fs.getRoot());
    d1.append(d2);
    d3 = new Directory("d3", fs.getRoot());
    d2.append(d3);
    d4 = new Directory("d4", fs.getRoot());
    d1.append(d4);
    testfile1 = new File("testfile1.txt");
    d2.append(testfile1);
  }

  @Test
  public void testChangTochild() {
    assertEquals(d1, cd.changeDir("d1", fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath()));
  }
  @Test
  public void testChangToRoot() {
    assertEquals(d3, cd.changeDir("d1/d2/d3", fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath()));
    assertEquals(fs.getRoot(), cd.changeDir("/", fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath()));
  }
  @Test
  public void testChangToParent() {
    cd.changeDir("a/b", fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath());
    assertEquals(fs.getRoot().getFile(fs.getRoot().find("a")), cd.changeDir("..", fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath()));
    
  }
  @Test
  public void testChangToParentsParent() {
    cd.changeDir("a/b/c", fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath());
    assertEquals(fs.getRoot().getFile(fs.getRoot().find("a")), cd.changeDir("../..", fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath()));
    
  }
  @Test
  public void testChangeToSameDir() {
    assertEquals(d3, cd.changeDir("d1/d2/d3", fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath()));
    assertEquals(d3, cd.changeDir(".", fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath()));
    
  }
  @Test
  public void testChangeToSameDirMultipleTimes() {
    assertEquals(d3, cd.changeDir("d1/d2/d3", fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath()));
    assertEquals(d3, cd.changeDir("././././././././.", fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath()));
    
  }
  @Test
  public void testChangeToRootMulitpleSlashes() {
    assertEquals(d3, cd.changeDir("d1/d2/d3", fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath()));
    assertEquals(fs.getRoot(), cd.changeDir("///////", fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath()));
    
  }
  @Test
  public void testChangeToFile() {
    assertEquals(fs.getRoot(), cd.changeDir("d1/d2/testfile1.txt", fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath()));
  }
  @Test
  public void testChangeToInvalidPath() {
    assertEquals(fs.getRoot(),cd.changeDir("d1/d2/txt", fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath()));
  }
  @Test
  public void testChangeUsingAbsPath() {
    cd.changeDir("/a/b/c", fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath());
    Directory a = (Directory) fs.getRoot().getFile(fs.getRoot().find("a"));
    Directory b = (Directory) a.getFile(a.find("b"));
    Directory d = (Directory) b.getFile(b.find("d"));
    assertEquals(d ,cd.changeDir("/a/b/d", fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath()));
  }
  @Test
  public void testChangeDirExec() {
    String[] cmds = {"cd", "d1/d2"};
    assertEquals(null, cd.execute(cmds));
    assertEquals(d2, fs.getWorkingDir());
  }
  

}
