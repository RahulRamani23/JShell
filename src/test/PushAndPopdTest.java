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
import file.*;
import commands.PushAndPopd;
import commands.ChangeDirectory;

public class PushAndPopdTest {
  private FileSystem fs;
  private PushAndPopd PPD;
  private ChangeDirectory cd;

  @Before
  public void setUp() throws Exception {
    fs = new FileSystem();
    PPD = new PushAndPopd(fs);
    cd = new ChangeDirectory(fs);
  }

  @Test
  public void testExecutePushdRetNullValidCommand() {
    String[] params = {"pushd", "/"};
    assertEquals(null, PPD.executePushd(params));
  }

  @Test
  public void testExecutePushdRetNullInvalidCommand() {
    String[] params = {"pushd"};
    assertEquals(null, PPD.executePushd(params));
  }

  @Test
  public void testCorrectDirectoryAfterPushdWithNoDirChange() {
    PPD.Pushd("/", ".", fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath());
    assertEquals("/", fs.getWorkingPath().pwd());
  }

  @Test
  public void testCorrectDirectoryReturnedPushdWithRelativeDirChange() {
    Directory root = fs.getRoot();
    Directory test = new Directory("test", root);
    root.append(test);
    Directory newDir = PPD.Pushd(fs.getWorkingPath().pwd(), "test",
        fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath());
    assertEquals(test, newDir);
  }

  @Test
  public void testCorrectDirectoryReturnedPushdWithAbsoluteDirChange() {
    Directory root = fs.getRoot();
    Directory test = new Directory("test", root);
    root.append(test);
    Directory newDir = PPD.Pushd(fs.getWorkingPath().pwd(), "/test",
        fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath());
    assertEquals(test, newDir);
  }

  @Test
  public void testCorrectDirectoryReturnedPushdWithDirChangeToRootFromSubDir() {
    Directory root = fs.getRoot();
    Directory test = new Directory("test", root);
    root.append(test);
    fs.changeWorkingDir(test);
    Directory newDir = PPD.Pushd(fs.getWorkingPath().pwd(), "/",
        fs.getWorkingDir(), fs.getRoot(), fs.getWorkingPath());
    assertEquals(root, newDir);
  }

   @Test
   public void testPopdChangesToRootFromSubDir() {
     Directory root = fs.getRoot();
     Directory test = new Directory("test", root);
     root.append(test);
     PPD.Pushd(fs.getWorkingPath().pwd(), "/test",
         root, fs.getRoot(), fs.getWorkingPath());
     String[] params = {"popd"};
     PPD.executePopd(params);
     assertEquals(root, fs.getWorkingDir());
   }
   
   @Test
   public void testPopdChangesToSubDirFromRoot() {
     Directory root = fs.getRoot();
     Directory test = new Directory("test", root);
     root.append(test);
     String[] cdParams = {"cd", "/test"};
     cd.execute(cdParams);
     PPD.Pushd(fs.getWorkingPath().pwd(), "/",
         test, fs.getRoot(), fs.getWorkingPath());
     String[] params = {"popd"};
     PPD.executePopd(params);
     assertEquals(test, fs.getWorkingDir());
   }
   
   @Test
   public void testPopdChangesToDifferentSubDirOfRoot() {
     Directory root = fs.getRoot();
     Directory test = new Directory("test", root);
     Directory test2 = new Directory("test2", root);
     root.append(test);
     root.append(test2);
     String[] cdParams = {"cd", "/test"};
     cd.execute(cdParams);
     PPD.Pushd(fs.getWorkingPath().pwd(), "/test2",
         test, fs.getRoot(), fs.getWorkingPath());
     String[] params = {"popd"};
     PPD.executePopd(params);
     assertEquals(test, fs.getWorkingDir());
   }

}
