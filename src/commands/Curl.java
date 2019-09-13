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

import java.io.*;
import java.net.*;
import file.File;
import file.FileSystem;
import verify.command.ValidityCheckCurl;


/**
 * The Curl class implements the ability to save the contents of a file from a
 * given URL and save the file in the current working directory
 *
 */
public class Curl implements Command {

  private FileSystem fsystem;
  private ValidityCheckCurl valid;

  /**
   * This is the Curl constructor which will instantiate the validity object and
   * set the file system to the one being passed in
   * 
   * @param fs Which is the file system
   */
  public Curl(FileSystem fs) {
    valid = new ValidityCheckCurl();
    this.fsystem = fs;
  }


  /**
   * This method is designed to get the contents of the desired file at the
   * given URL and create the file in the current working directory
   * 
   * @param arguments An array which contains the users input
   */
  public void getURLContent(String[] arguments) {
    // Variables to hold the content
    String contents = "", theURL = "", name = "";
    // Get the URL for the file
    theURL += arguments[1];
    // Try to get the file at the URL
    try {
      // Create the URL
      URL website = new URL(theURL);
      // Declare buffered reader and use openStream so we can read the contents
      BufferedReader theFile =
          new BufferedReader(new InputStreamReader(website.openStream()));
      // String variable for each line we read
      String inputLine;
      // Continue to loop and read until there is nothing left
      while ((inputLine = theFile.readLine()) != null)
        contents += inputLine + "\n";
      // Make sure to close stream
      theFile.close();

      // If the content was found get the name of the file
      int startName = theURL.lastIndexOf("/");
      // Store the file name
      name = theURL.substring(startName + 1, theURL.length());

      // Check if the file already exists
      int existence = fsystem.getWorkingDir().find(name);

      // Create the file if it doesn't exist
      if (existence == -1) {
        File newF = new File(name);
        newF.append(contents + "\n");
        // Add the file to the current directory
        fsystem.getWorkingDir().append(newF);
      } else {
        System.out.println("File already exists in the current directory");
      }

      // Catch the exception if the URL is invalid
    } catch (IOException e) {
      System.out.println(theURL + " : Is an invalid URL!");
    }
  }


  /**
   * This method will error check the given arguments for the curl command and
   * will get the contents and store it in a file in the current directory if
   * valid arguments are passed in
   * 
   * @param params Which is an array of the arguments the user passed in
   * @return null
   */
  public String execute(String[] params) {
    // Error check to see if valid and execute if valid
    if (valid.validate(params)) {
      getURLContent(params);
    } else {
      System.out.println("Invalid command, please try again");
    }
    return null;
  }

}
