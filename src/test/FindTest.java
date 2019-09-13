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
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import commands.Find;
import file.*;

public class FindTest {
  private Find find;
  private FileSystem fs;

  @Before
  public void setUp() throws Exception {
    fs = new FileSystem();
    find = new Find(fs);
    Directory a = new Directory("a", fs.getRoot());
    Directory a2 = new Directory("a", fs.getRoot());
    Directory b = new Directory("b", fs.getRoot());
    File b2 = new File("b");
    File b3 = new File("b");
    fs.getRoot().append(a);
    fs.getRoot().append(b);
    b.append(b3);
    a.append(b2);
    a.append(a2);
  }

  @Test
  public void testExecuteReturnsNullNoTypeParamInputted() {
    String[] params = {"find", "/", "-name", "\"xyz\""};
    assertEquals(null, find.execute(params));
  }

  @Test
  public void testExecuteReturnsNullNoNameInputted() {
    String[] params = {"find", "/", "-type", "f", "me", "\"xyz\""};
    assertEquals(null, find.execute(params));
  }

  @Test
  public void testExecuteReturnsNullNoTpyInputtedAfterType() {
    String[] params = {"find", "/", "-type", "f", "me", "\"xyz\""};
    assertEquals(null, find.execute(params));
  }

  @Test
  public void testExecuteReturnsNullNoNameAfterDashName() {
    String[] params = {"find", "/", "-type", "f", "name"};
    assertEquals(null, find.execute(params));
  }

  @Test
  public void testExecuteReturnsStringValidCommandInputted() {
    String[] params = {"find", "/", "-type", "f", "-name", "\"xyz\""};
    assertTrue(find.execute(params).getClass().equals(String.class));
  }

  @Test
  public void testExecuteRetsCorrectStringForNonExistentDirSingleSearchDir() {
    ArrayList<String> dirs = new ArrayList<String>();
    dirs.add("/");
    String actual = find.findFile(dirs, "c", "d");
    String expected = "The file " + "c" + " of type " + "d"
        + " does not exist at " + "/" + "\n";
    assertEquals(expected, actual);
  }

  @Test
  public void testExecuteRetsCorrectStringForNonExistentDirMultSearchDir() {
    ArrayList<String> dirs = new ArrayList<String>();
    dirs.add("/");
    dirs.add("/a");
    String actual = find.findFile(dirs, "c", "d");
    String expected = "The file " + "c" + " of type " + "d"
        + " does not exist at " + "/" + "\n" + "The file " + "c" + " of type "
        + "d" + " does not exist at " + "/a" + "\n";
    assertEquals(expected, actual);
  }

  @Test
  public void testExecuteRetsCorrectStringForNonExistentFileSingleSearchDir() {
    ArrayList<String> dirs = new ArrayList<String>();
    dirs.add("/");
    String actual = find.findFile(dirs, "c", "f");
    String expected = "The file " + "c" + " of type " + "f"
        + " does not exist at " + "/" + "\n";
    assertEquals(expected, actual);
  }

  @Test
  public void testExecuteRetsCorrectStringForNonExistentFileMultSearchDir() {
    ArrayList<String> dirs = new ArrayList<String>();
    dirs.add("/");
    dirs.add("/a");
    String actual = find.findFile(dirs, "c", "f");
    String expected = "The file " + "c" + " of type " + "f"
        + " does not exist at " + "/" + "\n" + "The file " + "c" + " of type "
        + "f" + " does not exist at " + "/a" + "\n";
    assertEquals(expected, actual);
  }

  @Test
  public void testExecuteRetsCorrectStringForExistentDirOneSearchDir() {
    ArrayList<String> dirs = new ArrayList<String>();
    dirs.add("/");
    String actual = find.findFile(dirs, "a", "d");
    String expected =
        "The file " + "a" + " of type " + "d" + " exists at " + "/" + "\n";
    assertEquals(expected, actual);
  }

  @Test
  public void testExecuteRetsCorrectStringMultSearchDirDirExistsInAll() {
    ArrayList<String> dirs = new ArrayList<String>();
    dirs.add("/");
    dirs.add("/a");
    String actual = find.findFile(dirs, "a", "d");
    String expected = "The file " + "a" + " of type " + "d" + " exists at "
        + "/" + "\n" + "The file " + "a" + " of type " + "d" + " exists at "
        + "/a" + "\n";
    assertEquals(expected, actual);
  }

  @Test
  public void testExecuteRetsCorrectStringMultSearchDirDirExistsInOne() {
    ArrayList<String> dirs = new ArrayList<String>();
    dirs.add("/");
    dirs.add("/a");
    String actual = find.findFile(dirs, "b", "d");
    String expected = "The file " + "b" + " of type " + "d" + " exists at "
        + "/" + "\n" + "The file " + "b" + " of type " + "d"
        + " does not exist at " + "/a" + "\n";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testExecuteRetsCorrectStringForExistentFileOneSearchDir() {
    ArrayList<String> dirs = new ArrayList<String>();
    dirs.add("/a");
    String actual = find.findFile(dirs, "b", "f");
    String expected =
        "The file " + "b" + " of type " + "f" + " exists at " + "/a" + "\n";
    assertEquals(expected, actual);
  }

  @Test
  public void testExecuteRetsCorrectStringMultSearchDirFileExistsInAll() {
    ArrayList<String> dirs = new ArrayList<String>();
    dirs.add("/b");
    dirs.add("/a");
    String actual = find.findFile(dirs, "b", "f");
    String expected = "The file " + "b" + " of type " + "f" + " exists at "
        + "/b" + "\n" + "The file " + "b" + " of type " + "f" + " exists at "
        + "/a" + "\n";
    assertEquals(expected, actual);
  }

  @Test
  public void testExecuteRetsCorrectStringMultSearchDirDirFileInOne() {
    ArrayList<String> dirs = new ArrayList<String>();
    dirs.add("/a");
    dirs.add("/");
    String actual = find.findFile(dirs, "b", "f");
    String expected = "The file " + "b" + " of type " + "f" + " exists at "
        + "/a" + "\n" + "The file " + "b" + " of type " + "f"
        + " does not exist at " + "/" + "\n";
    assertEquals(expected, actual);
  }

}
