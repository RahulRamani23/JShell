package driver;
import commands.PathCheck;
import file.*;
public class Tester {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    PathCheck pc = new PathCheck();
    Directory dir = new Directory("/", null);
    dir.append(new Directory("a", dir));
    System.out.println(pc.parseDirectory("Directory", dir, "/a"));

  }

}
