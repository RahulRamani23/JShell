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
import org.junit.Test;
import org.junit.Before;
import file.*;

public class DirectoryTest {

  private Directory test;
  private Directory root;
  private File fileTest;
  
  @Before
  public void setUp() throws Exception {
    root = new Directory("root", null);
    test = new Directory("test", root);
    fileTest = new File("fileTest");
  }

  @Test
  public void testAddFileToDirectory() {
    test.append(fileTest);
    assertEquals(0, test.find("fileTest"));
  }

  @Test
  public void testAddDirectoryToDirectory() {
    Directory d = new Directory("testDirectory", test);
    test.append(d);
    assertEquals(0, test.find("testDirectory"));
  }
  
  @Test
  public void testGetParent() {
    Directory ret = test.getParent();
    assertEquals("root", ret.getFileName());
  }
  
  @Test
  public void testCopyDirectoryRetainsName() {
    Directory copiedDir = test.copy(null);
    assertEquals("test", copiedDir.getFileName());
  }
  
  @Test
  public void testCopyDirectoryChangesParent() {
    Directory newParent = new Directory("newParent", root);
    Directory copiedDir = test.copy(newParent);
    assertEquals("newParent", copiedDir.getParent().getFileName());
  }
  
  @Test
  public void testCopyDirectoryRetainsContents() {
    Directory contentTest = new Directory("contentTest", test);
    test.append(contentTest);
    Directory copiedDir = test.copy(null);
    assertEquals(0, copiedDir.find("contentTest"));
  }
  
  @Test
  public void testFindFileTypeInDirectory() {
    Directory contentTest = new Directory("contentTest", test);
    test.append(contentTest);
    test.append(fileTest);
    assertEquals(1, test.find("fileTest"));
  }
  
  @Test
  public void testGetFileTypeFromTheDirectory() {
    test.append(fileTest);
    assertEquals(fileTest, test.getFile(test.find("fileTest")));
  }
  
  
  @Test
  public void testDeleteFromDirectory() {
    test.append(fileTest);
    test.delete(test.find("fileTest"));
    assertEquals(-1, test.find("fileTest"));
  }
  
  @Test
  public void testEqualsTypeDirectory() {
    assertEquals(true, test.equalsType("file.Directory"));
  }
  
  @Test
  public void testChangeFileName() {
    test.setFileName("newTestName");
    assertEquals("newTestName", test.getFileName());
  }
}
