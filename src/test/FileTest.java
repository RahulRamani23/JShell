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

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import file.File;

public class FileTest {

    private File test;
    
    @Before
    public void setUp() {
      test = new File("test");
      test.append("First line\n");
    }
    
    @Test
    public void testGettingFileContents() {
      assertEquals("First line\n", test.contents());
    }
    
    @Test
    public void testAppendToFile() {
      test.append("Second line");
      assertEquals("First line\nSecond line", test.contents());
    }
    
    @Test
    public void testReWriteFile() {
      test.rewrite("This is the new first line");
      assertEquals("This is the new first line", test.contents());
    }
    
    @Test
    public void testMakingACopyOfFile() {
      File cp = test.copy();
      assertNotSame(cp, test);
    }
    
    @Test
    public void testContentsOfCopiedFile() {
      File cp = test.copy();
      assertEquals(test.contents(), cp.contents());
    }
    
    @Test
    public void testEqualsTypeFile() {
      assertEquals(true, test.equalsType("file.File"));
    }
    
    @Test
    public void testChangeFileName() {
      test.setFileName("newTestName");
      assertEquals("newTestName", test.getFileName());
    }
    
}
