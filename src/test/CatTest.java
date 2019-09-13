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
import commands.Cat;
import file.Directory;
import file.File;
import file.FileSystem;

public class CatTest {
  private Cat cat;
  private FileSystem fs;
  private File testfile1;
  private File testfile2;

  @Before
  public void setUp() throws Exception {
    fs = new FileSystem();
    cat = new Cat(fs);
    testfile1 = new File("testfile1.txt");
    testfile1.append("line 1 line 2 line 3");
    testfile2 = new File("testfile2.txt");
    testfile2.append("line 1 \nline 2 \nline 3 \n");
    fs.getRoot().append(testfile1);
    fs.getRoot().append(testfile2);
    Directory dir = new Directory("testdir", fs.getRoot());
    fs.getRoot().append(dir);
  }

  @Test
  public void testPrintContentsofGivenFile() {
    String cmds[] = {"cat", "testfile1.txt"};
    String cmds2[] = {"cat", "testfile2.txt"};
    assertEquals("line 1 line 2 line 3\n \n \n",
        cat.cat(cmds, fs.getWorkingDir(), fs.getWorkingPath()));
    assertEquals("line 1 \nline 2 \nline 3 \n\n \n \n",
        cat.cat(cmds2, fs.getWorkingDir(), fs.getWorkingPath()));
  }

  @Test
  public void testPrintContentsofMultipleFiles() {
    String cmds[] = {"cat", "testfile1.txt", "testfile2.txt"};
    assertEquals(
        "line 1 line 2 line 3\n \n \nline 1 \nline 2 \nline 3 \n\n \n \n",
        cat.cat(cmds, fs.getWorkingDir(), fs.getWorkingPath()));
  }

  @Test
  public void testNonExistingFiles() {
    String cmds[] = {"cat", "testfake1.txt", "testfake2.txt"};
    assertEquals("", cat.cat(cmds, fs.getWorkingDir(), fs.getWorkingPath()));
  }

  @Test
  public void testNonExistingandExistingFiles() {
    String cmds[] = {"cat", "testfile1.txt", "testfake2.txt"};
    assertEquals("line 1 line 2 line 3\n \n \n",
        cat.cat(cmds, fs.getWorkingDir(), fs.getWorkingPath()));
  }

  @Test
  public void testCatOnDir() {
    String cmds[] = {"cat", "testdir"};
    assertEquals("", cat.cat(cmds, fs.getWorkingDir(), fs.getWorkingPath()));
  }

  @Test
  public void testCatUsingAbsPath() {
    File testfile3 = new File("testfile3.txt");
    testfile3.append("Hello World");
    ((Directory) fs.getRoot().getFile(fs.getRoot().find("testdir")))
        .append(testfile3);
    String cmds[] = {"cat", "testdir/testfile3.txt"};
    assertEquals("Hello World\n \n \n",
        cat.cat(cmds, fs.getWorkingDir(), fs.getWorkingPath()));
  }

  @Test
  public void testCatExecSingleFile() {
    String cmds[] = {"cat", "testfile1.txt"};
    assertEquals("line 1 line 2 line 3", cat.execute(cmds));
  }

  @Test
  public void testCatExecMultipleFile() {
    String cmds[] = {"cat", "testfile1.txt", "testfile2.txt"};
    assertEquals(
        "line 1 line 2 line 3\n \n \nline 1 \nline 2 \nline 3 \n\n \n \n",
        cat.execute(cmds));
  }
}
