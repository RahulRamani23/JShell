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
import commands.History;

public class HistoryTest {
  
  /**
   * Our History Instance
   */
  private History h;
  
  @Before
  public void setUp(){
    h = new History();
    String[] c = {"c"}, c2 = {"c2"};
    h.pushCom(c);
    h.pushCom(c2);
  }

  @Test
  public void testCorrectSizeOfHistory() {
    assertEquals(2, h.getSize());
  }
  
  @Test
  public void testCorrectOutputOfHistory() {
    String expected = "1.  c\n2.  c2\n";
    String ret = h.outputHist();
    assertEquals(expected, ret);
  }
  
  @Test
  public void testCorrectOutputOfSpecificNumOfHistory() {
    String expected = "2.  c2\n";
    String ret = h.outputNumHist(1);
    assertEquals(expected, ret);
  }
  
  @Test
  public void testExecuteCorrectOutputOfHistory() {
    String expected = "1.  c\n2.  c2\n";
    String[] params = {"history"};
    String ret = h.execute(params);
    assertEquals(expected, ret);
  }
  
  @Test
  public void testExecuteCorrectOutputOfNumHistory() {
    String expected = "2.  c2\n";
    String[] params = {"history", "1"};
    String ret = h.execute(params);
    assertEquals(expected, ret);
  }
  
  @Test
  public void testPushCommandPushesToEndOfHistory() {
    String expected = "3.  c3\n";
    String[] c = {"c3"};
    h.pushCom(c);
    String[] params = {"history", "1"};
    String ret = h.execute(params);
    assertEquals(expected, ret);
  }
  
  @Test
  public void testOutputOfBlankHistory() {
    History n = new History();
    String[] params = {"history"};
    String ret = n.execute(params);
    assertEquals("", ret);
  }
  
  @Test
  public void testPopRemovesLastItemFromTheHistory() {
    String expected = "1.  c\n";
    String[] params = {"history", "1"};
    h.popCom();
    String ret = h.execute(params);
    assertEquals(expected, ret);
  }

}
