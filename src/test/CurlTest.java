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
import commands.Curl;
import file.FileSystem;

public class CurlTest {

  private FileSystem fs;
  private Curl website;


  @Before
  public void Setup() {
    fs = new FileSystem();
    website = new Curl(fs);
  }

  @Test
  public void testValidURLNewFile() {
    String[] commands =
        {"curl", "http://www.cs.cmu.edu/~spok/grimmtmp/073.txt"};

    website.execute(commands);
    assertEquals(0, fs.getWorkingDir().find("073.txt"));
  }

  @Test
  public void testValidURLAlreadyExists() {
    String[] commands =
        {"curl", "http://www.cs.cmu.edu/~spok/grimmtmp/073.txt"};

    website.execute(commands);
    website.execute(commands);

    assertEquals(1, fs.getWorkingDir().numFile());
  }

  @Test
  public void testInvalidURL() {
    String[] commands = {"curl", "YOLO"};

    website.execute(commands);
    assertEquals(0, fs.getWorkingDir().numFile());
  }

  @Test
  public void testInvalidInput() {
    String[] commands = {"curl", "https://www.google.ca", "HAHAHA"};

    website.execute(commands);
    assertEquals(0, fs.getWorkingDir().numFile());
  }

}
