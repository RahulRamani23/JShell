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
package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import file.*;

public class FileSystemTest {
  private FileSystem fs;
  @Before
  public void setUp() throws Exception {
    fs = new FileSystem();
  }

  @Test
  public void testFsReturnsCorrectRootNoDirectoryChange() {
    Directory test = new Directory("/", null);
    assertTrue(fs.getRoot().equals(test));
  }
  
  @Test
  public void testFsReturnsCorrectWorkingDirectoryNoDirectoryChange() {
    Directory test = new Directory("/", null);
    assertTrue(fs.getWorkingDir().equals(test));
  }
  
  @Test
  public void testFsReturnsCorrectPWDNoDirectoryChange() {
    Directory test = new Directory("/", null);
    assertEquals("/", fs.getWorkingPath().pwd());
  }
  
  @Test
  public void testFsReturnsCorrectWorkingDirAfterDirChange() {
    Directory test = new Directory("/", null);
    Directory newDir = new Directory("test", null);
    test.append(newDir);
    fs.getWorkingDir().append(newDir);
    fs.changeWorkingDir(newDir);
    fs.getWorkingPath().dirChange(newDir);
    assertEquals(newDir, fs.getWorkingDir());
  }
  
  @Test
  public void testFsReturnsCorrectRootDirAfterDirChange() {
    Directory test = new Directory("/", null);
    Directory newDir = new Directory("test", null);
    test.append(newDir);
    fs.getWorkingDir().append(newDir);
    fs.changeWorkingDir(newDir);
    fs.getWorkingPath().dirChange(newDir);
    assertTrue(test.equals(fs.getRoot()));
  }
  
  @Test
  public void testFsReturnsCorrectPWDAfterDirChange() {
    Directory test = new Directory("/", null);
    Directory newDir = new Directory("test", null);
    test.append(newDir);
    fs.getWorkingDir().append(newDir);
    fs.changeWorkingDir(newDir);
    fs.getWorkingPath().dirChange(newDir);
    assertEquals("/test", fs.getWorkingPath().pwd());
  }

}
