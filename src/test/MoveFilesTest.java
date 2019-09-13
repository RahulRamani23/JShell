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
import file.Directory;
import file.File;
import file.FileSystem;
import org.junit.Test;
import commands.Ls;
import commands.MoveFiles;

public class MoveFilesTest {
  private FileSystem fs;
  private MoveFiles mv;
  private Ls ls;
  private Directory dirA;
  private Directory dirAB;
  private Directory dirABC;
  private File testfile1;
  private File testfile2;

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
    mv = new MoveFiles(fs);
    ls = new Ls(fs);

  }

  @Test
  public void moveDirToRoot() {

    // Array to hold the command and argument
    String[] commands = {"mv", "a/b/c", "/"};
    String[] commandsLS = {"ls"};
    String[] commandsLSB = {"ls", "a/b"};

    assertEquals(null, mv.execute(commands));
    assertEquals("a\nc\n", ls.execute(commandsLS));
    assertEquals("a/b: testfile2.txt\n", ls.execute(commandsLSB));
  }

  @Test
  public void moveSameDirDiffName() {

    // Array to hold the command and argument
    String[] commands = {"mv", "a", "DiffName"};
    String[] commandsLS = {"ls"};
    String[] commandsLSDiffName = {"ls", "DiffName"};

    assertEquals(null, mv.execute(commands));
    assertEquals("DiffName\n", ls.execute(commandsLS));
    assertEquals("DiffName: b testfile1.txt\n", ls.execute(commandsLSDiffName));
  }

  @Test
  public void moveSameDirSameName() {

    // Array to hold the command and argument
    String[] commands = {"mv", "a", "a"};
    String[] commandsLS = {"ls"};
    String[] commandsLSA = {"ls", "a"};

    assertEquals(null, mv.execute(commands));
    assertEquals("a\n", ls.execute(commandsLS));
    assertEquals("a: b testfile1.txt\n", ls.execute(commandsLSA));
  }

  @Test
  public void moveFileToRoot() {

    // Array to hold the command and argument
    String[] commands = {"mv", "a/b/testfile2.txt", "/"};
    String[] commandsLS = {"ls"};

    assertEquals(null, mv.execute(commands));
    assertEquals("a\ntestfile2.txt\n", ls.execute(commandsLS));
  }

  @Test
  public void moveSameFileDiffName() {

    // Array to hold the command and argument
    String[] commands = {"mv", "a/testfile1.txt", "a/DiffName.txt"};
    String[] commandsLS = {"ls", "a"};

    assertEquals(null, mv.execute(commands));
    assertEquals("a: b DiffName.txt\n", ls.execute(commandsLS));
  }

  @Test
  public void moveSameFileSameName() {

    // Array to hold the command and argument
    String[] commands = {"mv", "a/testfile.txt", "a/testfile1.txt"};
    String[] commandsLS = {"ls"};

    assertEquals(null, mv.execute(commands));
    assertEquals("a\n", ls.execute(commandsLS));
  }

  @Test
  public void InvalidSourcePath() {

    // Array to hold the command and argument
    String[] commands = {"mv", "invalid__", "a/testfile1.txt"};
    String[] commandsLS = {"ls", "a"};

    assertEquals(null, mv.execute(commands));
    assertEquals("a: b testfile1.txt\n", ls.execute(commandsLS));
  }

  @Test
  public void moveUsingAbsPath() {

    // Array to hold the command and argument
    String[] commands = {"mv", "/a/b/c", "/a"};
    String[] commandsLS = {"ls"};

    assertEquals(null, mv.execute(commands));
    assertEquals("a\n", ls.execute(commandsLS));
  }



}
