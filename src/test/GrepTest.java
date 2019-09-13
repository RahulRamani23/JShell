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
import commands.Grep;
import file.Directory;
import file.File;
import file.FileSystem;

public class GrepTest {
  private FileSystem fs;
  private File testfile1;
  private Grep grep;
  private Directory d1;
  private Directory d2;
  private Directory d3;
  private Directory d4;

  @Before
  public void setUp() throws Exception {
    fs = new FileSystem();
    grep = new Grep(fs);
    d1 = new Directory("d1", fs.getRoot());
    fs.getRoot().append(d1);
    d2 = new Directory("d2", fs.getRoot());
    d1.append(d2);
    d3 = new Directory("d3", fs.getRoot());
    d2.append(d3);
    d4 = new Directory("d4", fs.getRoot());
    d1.append(d4);
    testfile1 = new File("testfile1.txt");
    testfile1.append("line 1\nline 2\nline 3");
    d2.append(testfile1);
  }

  @Test
  public void testAllLineConatinRegex() {
    assertEquals(
        "d1/d2/testfile1.txt:0:line 1\nd1/d2/testfile1.txt:1:line 2\nd1/d2/testfile1.txt:2:line 3\n",
        grep.grepFile("d1/d2/testfile1.txt", fs.getWorkingDir(), fs.getRoot(),
            "line"));
  }

  @Test
  public void testOneLineConatinRegex() {
    assertEquals("d1/d2/testfile1.txt:1:line 2\n", grep.grepFile(
        "d1/d2/testfile1.txt", fs.getWorkingDir(), fs.getRoot(), "line 2"));
  }

  @Test
  public void testNoLineConatinRegex() {
    assertEquals("", grep.grepFile("d1/d2/testfile1.txt", fs.getWorkingDir(),
        fs.getRoot(), "line 4"));
  }

  @Test
  public void testInvalidPath() {
    assertEquals(null, grep.grepFile("d1/d2/testf", fs.getWorkingDir(),
        fs.getRoot(), "line 2"));
  }

  @Test
  public void testPathToDirWithoutRecurOption() {
    assertEquals(null,
        grep.grepFile("d1/d2", fs.getWorkingDir(), fs.getRoot(), "line 2"));
  }

  @Test
  public void testRecursiveGrepFilePassedIn() {
    File testFile = new File("test");
    fs.getRoot().append(testFile);
    testFile.append("testString");
    String actual = grep.recursiveGrepFile("/test", "Str");
    assertEquals("/test:0:testString\n", actual);
  }

  @Test
  public void testRecursiveGrepFilePassedInNoMatch() {
    File testFile = new File("test");
    fs.getRoot().append(testFile);
    testFile.append("testString");
    String actual = grep.recursiveGrepFile("/test", "asdasd");
    assertEquals("", actual);
  }

  @Test
  public void testRecursiveGrepDirectroyPassedInOneFileMatch() {
    File testFile = new File("test");
    File testFile2 = new File("test2");
    fs.getRoot().append(testFile);
    fs.getRoot().append(testFile2);
    testFile.append("testString");
    String actual = grep.recursiveGrepFile("/", "Str");
    assertEquals("//test:0:testString\n", actual);
  }

  @Test
  public void testRecursiveGrepDirectroyPassedInMultipleMatches() {
    File testFile = new File("test");
    File testFile2 = new File("test2");
    fs.getRoot().append(testFile);
    fs.getRoot().append(testFile2);
    testFile2.append("Strestng");
    testFile.append("testString");
    String actual = grep.recursiveGrepFile("/", "Str");
    assertEquals("//test:0:testString\n//test2:0:Strestng\n", actual);
  }

  @Test
  public void testExecutReturnNullInvalidCommand() {
    File testFile = new File("test");
    fs.getRoot().append(testFile);
    testFile.append("testString");
    String[] params = {"grep", "-R"};
    assertEquals(null, grep.execute(params));
  }

  @Test
  public void testExecuteReturnStringValidRecursiveCommand() {
    File testFile = new File("test");
    fs.getRoot().append(testFile);
    testFile.append("testString");
    String[] params = {"grep", "-R", "\"Str\"", "/"};
    assertEquals("//test:0:testString\n", grep.execute(params));
  }

  @Test
  public void testExecuteReturnStringValidNonRecursiveCommand() {
    File testFile = new File("test");
    fs.getRoot().append(testFile);
    testFile.append("testString");
    String[] params = {"grep", "-R", "\"Str\"", "/test"};
    assertEquals("/test:0:testString\n", grep.execute(params));
  }

}
