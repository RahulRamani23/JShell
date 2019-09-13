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
import java.util.ArrayList;
import Stack.Stack;

public class StackTest {
  private Stack testStack;

  @Before
  public void setUp() throws Exception {
    testStack = new Stack();
  }

  @Test
  public void testSizeReturnsCorrectSize() {
    // add 6 elements to the stack
    for (int i = 0; i < 6; i++) {
      testStack.push("");
    }
    // check if size is equal to 6
    assertEquals(6, testStack.size());
  }

  @Test
  public void testPushAddsCorrectItem() {
    // add 1 item to the stack
    testStack.push("TEST ITEM");
    // see if the item pushed is returned
    assertEquals("TEST ITEM", testStack.pop());
  }

  @Test
  public void testPopObeysLIFO() {
    // create an expected and actual arrayList
    ArrayList<Integer> expected = new ArrayList<Integer>();
    ArrayList<Integer> actual = new ArrayList<Integer>();
    expected.add(4);
    expected.add(3);
    expected.add(2);
    expected.add(1);
    for (int i = 1; i < 5; i++) {
      testStack.push(i);
    }
    for (int i = 0; i < 4; i++) {
      actual.add((Integer)testStack.pop());
    }
    assertEquals(expected, actual);
  }

}
