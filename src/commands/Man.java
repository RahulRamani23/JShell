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
package commands;

import java.util.*;
import verify.command.ValidityCheckMan;

/**
 * The Man class implements the ability to display the man pages for any
 * available command
 *
 */
public class Man implements Command {

  // Create hash table for the man command
  private static Hashtable<String, String> manPages =
      new Hashtable<String, String>();
  // object to validate the arguments
  private ValidityCheckMan valid;

  /**
   * This is the man constructor which will instantiate the validity object
   * and create the hashtable which will store the man pages
   */
  public Man() {
    // Instantiate valid
    valid = new ValidityCheckMan();
    // Populate the man pages
    manPages.put("ls",
        "\n\t\t -- CSCB07 JShell Man Pages -- \t\t\n\n"
            + "NAME\n\tls -- list the contents of the current directory\n\n"
            + "SYNOPSIS\n\tls [-R] [PATH ...]\n\nDESCRIPTION\n\tThe ls"
            + " utility is designed to list out all the files and "
            + "directories which are in the directory.\n\tIf no "
            + "specified path is given then ls will list the contents "
            + "of the current working directory.\n\n"
            + "\tOptions:\n\t[PATH ...]  -  Optional paramter to list the "
            + "contents of a specified directory given a pathway.\n\t[-R]"
            + "  -  Optional parameter which will recursively list all "
            + "subdirectories as a tree structure.\n\n"
            + "EXAMPLES\n\tThe following will list the contents of the "
            + "current working directory.\n\n\t\tls\n\n\tThe following will"
            + " list all the contents of the specified directory.\n\n\t\tls"
            + " /Desktop/Pics\n\n\tThe following will recursively printout"
            + " the content inside the given directory.\n\n\t\tls -R "
            + "/Desktop/Documents/Projects\n\n\n\t\t-- Created on June "
            + "29, 2018 --\t\t\n");
    manPages.put("cat",
        "\n\t\t -- CSCB07 JShell Man Pages -- \t\t\n\n"
            + "NAME\n\tcat -- display contents of file\n\nSYNOPSIS\n\tcat "
            + "file_name\n\nDESCRIPTION\n\tThe cat utility is designed to"
            + " print out to the shell the contents of the given files. \n"
            + "\tGiven multiple paths, the output of file contents is "
            + "separated using three newline characters. \n\nEXAMPLES\n\t"
            + "The following will display the contents of RiceBoy.txt on "
            + "the shell.\n\n\t\tcat Riceboy.txt\n\n\n\t\t-- Created on "
            + "June 29, 2018 --\t\t\n");
    manPages.put("cd",
        "\n\t\t -- CSCB07 JShell Man Pages -- \t\t\n\n"
            + "NAME\n\tcd -- change the working directory\n\nSYNOPSIS\n\tcd"
            + " DIR\n\nDESCRIPTION\n\tThe cd utility is designed to change"
            + " the current working directory of the shell to the desired "
            + "directory.\n\tThe given directory may be given as a relative"
            + " path to the current working directory or an absolute path."
            + "\n\n\tOptions:\n\t[ .. ]  -  May be used to go to the current"
            + " directories parent.\n\nEXAMPLES\n\tThe following will change"
            + " the current working directory to the parent of the current"
            + " working directory.\n\n\t\tcd ..\n\n\tThe following will "
            + "change the current working directory to the directory "
            + "specified by the given pathway.\n\n\t\tcd /Desktop/Wowza/k"
            + "\n\n\n\t\t-- Created on June 29, 2018 --\t\t\n");
    manPages.put("echo",
        "\n\t\t -- CSCB07 JShell Man Pages -- \t\t\n\n"
            + "NAME\n\techo -- write arguments to standard output\n\n"
            + "SYNOPSIS\n\techo STRING [>|>>] [file]\n\nDESCRIPTION\n\tThe"
            + " echo utility is designed to write out any specified operands"
            + " that are inputted to standard output.\n\n\tOptions:\n\t> "
            + " -  Writes the given operands to the given file.\n\n\t>>  - "
            + " Appends the given operands to the given file.\n\n\t[file] "
            + " -  Operations paramater which must be passed in when using"
            + " '>' or '>>'.\n\nEXAMPLES\n\tThe following will print out to"
            + " the shell the given string.\n\n\t\techo \"some_random_"
            + "string\"\n\n\tThe following will take the given string and"
            + " put it into the given file.\n\tIf the file already exists "
            + "then whatever is in the file will be removed and the string"
            + " will be added.\n\tIf the file doesn't exist it will be "
            + "created.\n\n\t\techo \"some_random_string\" > somefile.txt"
            + "\n\n\tThe following will take the given string and append it"
            + " to the desired file.\n\tIf the file doesn't already exist "
            + "it will be created.\n\n\t\techo \"some_random_string\" >> "
            + "somefile.txt\n\n\n\t\t-- Created on June 29, 2018 --\t\t\n");
    manPages.put("find",
        "\n\t\t -- CSCB07 JShell Man Pages -- \t\t\n\n"
            + "NAME\n\tfind -- find the given file in the given directorie"
            + "(s)\n\nSYNOPSIS\n\tfind path ... -type [d|f] -name file_name"
            + "\n\nDESCRIPTION\n\tThe find utility is designed to find the"
            + " given file or directory in the given directorie(s).\n\n\t"
            + "Options:\n\tf  -  Specifies that the utility will search for"
            + " a file.\n\td  -  Specifies that the utillity will search for"
            + " a directory.\n\nEXAMPLES\n\tThe following will search the"
            + " given directory for a file with the name of file_name.xtn."
            + "\n\n\t\tfind /Desktop/RandomFolder -type f -name \"file_name"
            + ".xtn\"\n\n\tThe following will search the two given "
            + "directories for a directory named directory_name.\n\n\t\t"
            + "find /Desktop /Desktop/Music/GNR -type d -name \"directory_"
            + "name\"\n\n\n\t\t-- Created on June 29, 2018 --\t\t\n");
    manPages.put("history",
        "\n\t\t -- CSCB07 JShell Man Pages -- \t\t"
            + "\n\nNAME\n\thistory -- display command history\n\nSYNOPSIS\n"
            + "\thistory [n]\n\nDESCRIPTION\n\tThe history utility is "
            + "designed to output to the shell all previous commands which"
            + " have been used by the user.\n\n\tOptions:\n\tn  -  Display"
            + " the last 'n' number of commands. This is inclusive, as such"
            + " this command will also be outputted and counted to 'n'.\n"
            + "\nEXAMPLES\n\tThe following will list the entire command "
            + "history.\n\n\t\thistory\n\n\tThe following will list out "
            + "the last 7 user commands including this one.\n\n\t\thistory"
            + " 7\n\n\n\t\t-- Created on June 29, 2018 --\t\t\n");
    manPages.put("mkdir",
        "\n\t\t -- CSCB07 JShell Man Pages -- \t\t\n\n"
            + "NAME\n\tmkdir -- make directories\n\nSYNOPSIS\n\tmkdir"
            + " directory_name ... \n\nDESCRIPTION\n\tThe mkdir utility "
            + "is designed to create directorie(s) with the given directory"
            + " name(s).\n\nEXAMPLES\n\tThe following will create a "
            + "directory with the name 'newDirectory' at the desired "
            + "pathway.\n\n\t\t mkdir /Users/newDirectory\n\n\tThe "
            + "following will create the directory 'A' and the directory "
            + "'B' at their desired pathways.\n\n\t\t mkdir /Desktop/A "
            + "/Users/B\n\n\tThe following will create the directory 'C' "
            + "in the current working directory.\n\n\t\t mkdir C\n\n\n\t\t"
            + "-- Created on June 29, 2018 --\t\t\n");
    manPages.put("pushd",
        "\n\t\t -- CSCB07 JShell Man Pages -- \t\t\n\n"
            + "NAMEntpushd -- push the current directory to a stack and "
            + "change current directory to the given directory\n\nSYNOPSIS"
            + "\n\tpushd DIR\n\nDESCRIPTION\n\tThe pushd utility is designed"
            + " to push the current working directory onto a stack and then"
            + " switch\n\tthe current working directory to the given "
            + "directory.\n\nEXAMPLES\n\tThe following will push the "
            + "current working directory onto a stack and change the "
            + "current working directory to the given one.\n\n\t\tpushd "
            + "/Desktop/newDirectory\n\n\n\t\t-- Created on June 29, 2018 "
            + "--\t\t\n");
    manPages.put("popd",
        "\n\t\t -- CSCB07 JShell Man Pages -- \t\t\n\n"
            + "NAME\n\tpopd -- change current directory to the top directory"
            + " on stack\n\nSYNOPSIS\n\tpopd\n\nDESCRIPTION\n\tThe popd "
            + "utility is designed to change the current working directory "
            + "with the last directory\n\twhich was pushed onto the "
            + "directory stack.\n\nEXAMPLES\n\tThe follwing will change the"
            + " current working directory to the directory which is on top"
            + "\n\tof the stack (The one that was pushed onto it).\n\n\t\t"
            + "popd\n\n\n\t\t-- Created on June 29, 2018 --\t\t\n");
    manPages.put("pwd",
        "\n\t\t -- CSCB07 JShell Man Pages -- \t\t\n\n"
            + "NAME\n\tpwd -- return the working directory\n\nSYNOPSIS\n\t"
            + "pwd\n\nDESCRIPTION\n\tThe pwd utility is designed to print "
            + "out to the shell the absolute pathway of the current\n\t"
            + "working directory that the shell is in.\n\nEXAMPLES\n\tThe "
            + "following will display the absolute pathway of the current "
            + "working directory.\n\n\t\tpwd\n\n\n\t\t-- Created on June "
            + "29, 2018 --\t\t\n");
    manPages.put("tree",
        "\n\t\t -- CSCB07 JShell Man Pages -- \t\t\n\n"
            + "NAME\n\ttree -- display entire file system\n\nSYNOPSIS\n\t"
            + "tree\n\nDESCRIPTION\n\tThe tree utility is designed to output"
            + " to the shell the entire file system. Will display all "
            + "directories and files\n\tstarting at the root directory.\n\n"
            + "EXAMPLES\n\tThe following will print out the entire file "
            + "system starting from the root directory.\n\n\t\ttree\n\n\n\t"
            + "\t-- Created on June 29, 2018 --\t\t\n");
    manPages.put("curl",
        "\n\t\t -- CSCB07 JShell Man Pages -- \t\t\n\n"
            + "NAME\n\tcurl -- save file at the given URL to the working "
            + "directory\n\nSYNOPSIS\n\t"
            + "curl\n\nDESCRIPTION\n\tThe curl utility is designed to save"
            + " to the current working directory the file that was found at"
            + " the given URL.\n\nEXAMPLES\n\tThe following will save the file "
            + "'file03879.txt' to the current working directory.\n\n\t\tcurl"
            + " http://www.riceboy.iloverice.ca/file03879.txt\n\n\n\t"
            + "\t-- Created on June 29, 2018 --\t\t\n");
    manPages.put("mv",
        "\n\t\t -- CSCB07 JShell Man Pages -- \t\t\n\n"
            + "NAME\n\tmv -- move the item from the old path to the new"
            + " path directory\n\nSYNOPSIS\n\tmv OLDPATH NEWPATH"
            + " \n\nDESCRIPTION\n\tThe mv utility is designed to move"
            + " the file or directory from the oldpath to the newpath. "
            + "\n\tThe given file or directory may be given as a relative"
            + " path or an absolute path.\n\nEXAMPLES\n\tThe following "
            + "will move 'lol' into the the 'k' directory."
            + "\n\n\t\tmv /Pics/lol /Docs/k"
            + "\n\n\n\t\t-- Created on June 29, 2018 --\t\t\n");
    manPages.put("cp",
        "\n\t\t -- CSCB07 JShell Man Pages -- \t\t\n\n"
            + "NAME\n\tcp -- copy the item from the old path to the new"
            + " path directory\n\nSYNOPSIS\n\tcp OLDPATH NEWPATH"
            + " \n\nDESCRIPTION\n\tThe cp utility is designed to copy"
            + " the file or directory from the oldpath to the newpath. "
            + "\n\tThe given file or directory may be given as a relative"
            + " path or an absolute path.\n\nEXAMPLES\n\tThe following "
            + "will copy 'lol' into the the 'k' directory."
            + "\n\n\t\tcp /Pics/lol /Docs/k"
            + "\n\n\n\t\t-- Created on June 29, 2018 --\t\t\n");
    manPages.put("!number",
        "\n\t\t -- CSCB07 JShell Man Pages -- \t\t\n\n"
            + "NAME\n\t!number -- recall and execute the command found "
            + "at the history location.\n\nSYNOPSIS\n\t!number"
            + " \n\nDESCRIPTION\n\tThe !number utility is designed to "
            + "recall the command that was called prior\n\tgiven its "
            + "number in history and then execute it again."
            + "\n\nEXAMPLES\n\tThe following will print out \"I like "
            + "rice\" if 'echo \"I like rice\"' was the 3rd\n\tcommand "
            + "inputted into the shell.\n\n\t\t!3"
            + "\n\n\n\t\t-- Created on June 29, 2018 --\t\t\n");
    manPages.put("grep", "\n\t\t -- CSCB07 JShell Man Pages -- \t\t\n\n"
        + "NAME\n\tgrep -- print to the shell the lines where the"
        + " regex was found.\n\nSYNOPSIS\n\tgrep [-R] REGEX PATH ..."
        + " \n\nDESCRIPTION\n\tThe grep utility is designed to "
        + "find any lines in a file or multiple files which contains"
        + " lines matching the regex.\n\n\tOptions:\n\t[-R]  -  Will "
        + "recursively look in a given directory for the files "
        + "that match.\n\nEXAMPLES\n\tThe following will print out "
        + "the lines in the file 'k.txt' specified by the path which "
        + "match the given regex.\n\n\t\tgrep REGEX /Users/files/k.txt"
        + "\n\n\tThe following will print to the shell the files "
        + "along with their lines that match the given regex which"
        + "\n\twere found in the given directory and/or sub "
        + "directories.\n\n\t\tgrep [-R] REGEX /Users/Directory"
        + "\n\tgrep will print in the form path:line number:line "
        + "for each line in " + "the file that matches the REGEX"
        + "\n\n\tgrep will print nothing if no lines matching lines are found"
        + "\n\n\n\t\t-- Created on June 29, 2018 --\t\t\n");

  }


  /**
   * This method will check if what is being passed in is valid and if it is it
   * will call printMan to print out the pages
   * 
   * @param params Which is an array of the arguments the user passed in
   * @return ret which is a string with the man page or empty if invalid
   */
  public String execute(String[] params) {
    // String var to hold the return
    String ret = "";
    // Error check the arguments
    if (valid.validate(params)) {
      ret = manPages.get(params[1]);
    } else {
      System.out.println("Invalid command, please try again.");
      return null;
    }
    return ret;
  }

}
