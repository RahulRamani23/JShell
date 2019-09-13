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
import commands.ExecuteHistoryCommand;
import commands.History;

public class ExecuteHistoryCommandTest {
  private ExecuteHistoryCommand execHist;
  private History hs;

  @Before
  public void setUp() throws Exception {
    hs = new History();
    String[] cmds1 = {"mkdir", "a"};
    String[] cmds2 = {"ls", "a"};
    String[] cmds3 = {"cd", "a"};
    hs.pushCom(cmds1);
    hs.pushCom(cmds2);
    hs.pushCom(cmds3);
    execHist = new ExecuteHistoryCommand();

  }

  @Test
  public void testGetMostRecentCommand() {
    String[] params = {"!3"};
    hs.pushCom(params);
    assertEquals(" cd a", execHist.executeHistory(params, hs));
  }

  @Test
  public void testGetLeastRecentCommand() {
    String[] params = {"!1"};
    hs.pushCom(params);
    assertEquals(" mkdir a", execHist.executeHistory(params, hs));
  }

  @Test
  public void testGetCentralCommand() {
    String[] params = {"!2"};
    hs.pushCom(params);
    assertEquals(" ls a", execHist.executeHistory(params, hs));
  }

  @Test
  public void testOutOfBoundPosition() {
    String[] params = {"!1000"};
    hs.pushCom(params);
    assertEquals(null, execHist.executeHistory(params, hs));
  }

  @Test
  public void testOutOfNegPosition() {
    String[] params = {"!-1"};
    hs.pushCom(params);
    assertEquals(null, execHist.executeHistory(params, hs));
  }

  @Test
  public void testDooubleDigitPosition() {
    String[] cmds4 = {"mkdir", "b"};
    String[] cmds5 = {"ls", "b"};
    String[] cmds6 = {"cd", "b"};
    String[] cmds7 = {"mkdir", "c"};
    String[] cmds8 = {"ls", "c"};
    String[] cmds9 = {"cd", "c"};
    String[] cmds10 = {"ls"};
    hs.pushCom(cmds4);
    hs.pushCom(cmds5);
    hs.pushCom(cmds6);
    hs.pushCom(cmds7);
    hs.pushCom(cmds8);
    hs.pushCom(cmds9);
    hs.pushCom(cmds10);
    String[] params = {"!10"};
    hs.pushCom(params);
    assertEquals(" ls", execHist.executeHistory(params, hs));
  }


}
