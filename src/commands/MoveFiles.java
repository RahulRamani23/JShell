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
package commands;

import verify.command.ValidityCheckMv;
import file.*;

/**
 * This class represents the mv Command
 *
 */
public class MoveFiles extends PathCheck implements Command {
  /**
   * The file System that MoveFiles works on
   */
  private FileSystem fs;

  /**
   * Constructs the MoveFiles command
   * 
   * @param workingFs The working FileSystem of the given Shell
   */
  public MoveFiles(FileSystem workingFs) {
    this.fs = workingFs;
  }

  /**
   * Given a list of params representing the mv command i.e mv source
   * destination will execute the mv command and move the source to the
   * destination
   * 
   * @param params a list of the parameters required for the mv command starting
   *        with mv
   */
  public String execute(String[] params) {
    // check validity
    ValidityCheckMv test = new ValidityCheckMv();
    if (test.validate(params)) {
      this.moveFile(params[1], params[2]);
    } else {
      System.out.println("Invalid Command");
    }
    return null;
  }

  /**
   * given a path to the source and path to the dest this will move the source
   * into the destination. If conflicting names exist this will eit
   * 
   * @param source path to source
   * @param dest path to destination
   */
  public void moveFile(String source, String dest) {
    // obtain the file or directory specified at source
    // first check if the given path is relative
    FileType sourceFile, destination;
    Directory srcRoot = this.getRootOfDir(source);
    sourceFile = this.obtainFile(srcRoot, source);
    // check if the sourceFile is null
    if (sourceFile == null) {
      System.out.println("invalid Source");
    } else {
      // other wise obtain the destination
      // first check whether the given path is relative or not
      Directory destRoot = this.getRootOfDir(dest);
      // check if the given destination is an existing directory
      destination = this.parseDirectory("Directory", destRoot, dest);
      // check if there already exists a file with the same name
      if (destination != null
          && this.fileExists(sourceFile, (Directory) destination)) {
        // if the file does exist use the dest is directory conflict exists
        // helper
        this.moveDestIsDirectoryConflictExists(sourceFile,
            (Directory) destination, srcRoot, source);

      } else if (destination != null) {
        // otherwise if the destination exists and there is not conflict in the
        // directory use move dest directory no conflict method
        this.moveDestIsDirectoryNoConflict(sourceFile, (Directory) destination,
            srcRoot, source);

      } else {
        // if the destination is null then try obtaining a File with the same
        // path
        destination = this.parseDirectory("File", destRoot, dest);
        // if the destination is a file then check whether the source is a File
        // or Directory
        if (destination != null && sourceFile.equalsType("file.File")) {
          // if its a File then overWrite the file and delete the source
          this.moveDestIsFileSourceIsFile((File) destination, sourceFile,
              srcRoot, source);
        } else if (destination != null) {
          // if the destination is a directory then output an error
          this.moveDestIsFileSourceIsDir(sourceFile, destination);
        } else {
          // if the file doesn't exist at all then check if the subdirectory
          // exists
          // first check how many /'s there are to see if the subdir is the
          // working dir after removing trailing slashes
          this.moveDestDoesNotExist(dest, sourceFile, destRoot, srcRoot,
              source);
        }
      }
    }
  }

  /**
   * This gets the root of a given path either working directory or the root
   * 
   * @param path the path to obtain the root for
   * @return the root of the path
   */

  private Directory getRootOfDir(String path) {
    Directory root;
    if (this.isPathRelative(path)) {
      // obtain the source using obtainFile
      root = fs.getWorkingDir();
    } else {
      root = fs.getRoot();
    }
    return root;
  }

  /**
   * obtains a File or Directory at a given path returns null if the file doesnt
   * exist
   * 
   * @param dir the root directory of the path
   * @param path the path to watned file
   * @return the file or directory at the specified or null if neither exist
   */
  private FileType obtainFile(Directory dir, String path) {
    // try to obtain both a directory and a file
    // first check if the path is / indicating its an invalid dir
    if (path.matches("/+")) {
      return null;
    }
    FileType file = this.parseDirectory("Directory", dir, path);
    if (file != null) {
      return file;
    } else {
      return this.parseDirectory("File", dir, path);
    }
  }

  /**
   * Checks if a file with the same name as the original file exists another
   * directory
   * 
   * @param original the original file
   * @param destination the directory to search in
   * @return True if a file with the same name exists False if not
   */
  private boolean fileExists(FileType original, Directory destination) {
    return destination.find(original.getFileName()) != -1;
  }

  /**
   * Deletes a given file from its source directory
   * 
   * @param srcRoot the root of the source
   * @param sourceFile the File to be deleted
   * @param sourceName the path to original source
   */
  private void deleteSource(Directory srcRoot, FileType sourceFile,
      String sourceName) {
    // first check if the last index of / is -1 indicating that the sourceDir is
    // the src root
    Directory sourceDir;
    if (sourceName.replaceAll("/+$", "").replaceAll("^/+", "")
        .lastIndexOf("/") == -1) {
      sourceDir = srcRoot;
    } else {
      // otherwise cut of the name of source and obtain the directory source is
      // in
      sourceDir =
          (Directory) this.parseDirectory("Directory", srcRoot, sourceName
              .substring(0, sourceName.replaceAll("/+$", "").lastIndexOf("/")));
    }
    // delete the sourceFile from its original directory
    sourceDir.delete(sourceDir.find(sourceFile.getFileName()));
  }

  /**
   * Makes a copy of a given file add it to its new directory and change its
   * name
   * 
   * @param sourceFile the original file to be copied
   * @param newDir the new directoy to place the file in
   * @param newName the new name for the file
   */
  private void copyAndReplace(FileType sourceFile, Directory newDir,
      String newName) {
    FileType srcCopy;
    if (sourceFile.equalsType("file.File")) {
      srcCopy = ((File) sourceFile).copy();
    } else {
      srcCopy = ((Directory) sourceFile).copy(newDir);
    }
    // rename the copy and append the copy to the subDirectory
    srcCopy.setFileName(newName);
    newDir.append(srcCopy);
  }

  /**
   * This file will move the source to the destination if the destination given
   * points to a directory and there exists a file in the directory with the
   * same name as the source
   * 
   * @param sourceFile the original sourceFile
   * @param destination the destination directory
   * @param srcRoot the root of the source
   * @param source the path pointing the source
   */
  private void moveDestIsDirectoryConflictExists(FileType sourceFile,
      Directory destination, Directory srcRoot, String source) {
    FileType conflictFile =
        destination.getFile((destination).find(sourceFile.getFileName()));
    if (sourceFile.equalsType("file.Directory")) {
      // check if the conflict file is also a directory
      if (conflictFile.equalsType("file.Directory")) {
        if (conflictFile != sourceFile) {
          // if it is a directory then overwrite the directory
          // first copy the source directory
          Directory newDir =
              ((Directory) sourceFile).copy((Directory) sourceFile);
          // delete the original conflict in dest
          destination.delete(destination.find(sourceFile.getFileName()));
          // append the copy
          destination.append(newDir);
          // remove the original source
          this.deleteSource(srcRoot, sourceFile, source);
        }
      } else {
        System.out.println("Can't overwrite File as a Directory");
      }
    } else {
      // if the source File is a file then check if the found File is also a
      // file
      if (conflictFile.equalsType("file.File")) {
        // only do this if conflict and the original file arent the same
        if (conflictFile != sourceFile) {
          // if it is a file then overwrite it
          ((File) conflictFile).rewrite(((File) sourceFile).contents());
          // then delete the source
          this.deleteSource(srcRoot, sourceFile, source);
        }
      } else {
        // otherwise output an error
        System.out.println(
            "Invalid destination file cannot overwrite Directory as a File");
      }
    }
  }

  /**
   * This will move the sourceFile to the destination if the destination is a
   * directory and there is not file with the same name in the directory
   * 
   * @param sourceFile the original sourceFile
   * @param destination the destination directory
   * @param srcRoot the root of the source
   * @param source the path pointing the source
   */
  private void moveDestIsDirectoryNoConflict(FileType sourceFile,
      Directory destination, Directory srcRoot, String source) {
    // otherwise if the destination exists and there is not conflict in the
    // directory simply copy the file over then
    if (sourceFile.equalsType("file.Directory")) {
      // check if the source directory is the same as the destination directory
      if (sourceFile == destination) {
        System.out.println("Cannot move a directory into itself");
      } else {
        Directory newDir =
            ((Directory) sourceFile).copy((Directory) sourceFile);
        destination.append(newDir);
        // delete source File
        this.deleteSource(srcRoot, sourceFile, source);

      }

    } else {
      ((Directory) destination).append(sourceFile);
      // delete source File
      this.deleteSource(srcRoot, sourceFile, source);
    }

  }

  /**
   * moves the source to the destination if the destination is a File and the
   * source is also a File
   * 
   * @param destination the destination File
   * @param sourceFile the original sourceFile
   * @param srcRoot the root of the source File
   * @param source the path to the source File
   */
  private void moveDestIsFileSourceIsFile(File destination, FileType sourceFile,
      Directory srcRoot, String source) {
    // first check if the files are the exact same
    if (sourceFile == destination) {
      System.out.println(sourceFile.getFileName() + " and "
          + destination.getFileName() + " are the same file");
    } else {
      ((File) destination).rewrite(((File) sourceFile).contents());
      this.deleteSource(srcRoot, sourceFile, source);
    }

  }

  private void moveDestIsFileSourceIsDir(FileType sourceFile,
      FileType destination) {
    // if the destination is a directory then output an error
    System.out.println("Invalid destination file " + sourceFile.getFileName()
        + " is a file and " + destination.getFileName() + " is a direcory");
  }

  private void moveDestDoesNotExist(String dest, FileType sourceFile,
      Directory destRoot, Directory srcRoot, String source) {
    // if the file doesn't exist at all then check if the subdirectory
    // exists
    // first check how many /'s there are to see if the subdir is the
    // working dir after removing trailing slashes
    dest = dest.replaceAll("/+$", "").replaceAll("^/+", "");
    int numSlashes = dest.length() - dest.replaceAll("/+", "").length();
    // if the number of slashes is 0 then all we have is a name therefore
    // the subdirectory must exist
    if (numSlashes == 0) {
      // first check if the file name is a valid name
      this.moveSubDirectoryOfDestIsRoot(dest, sourceFile, destRoot, srcRoot,
          source);
    } else {
      // otherwise parse through the directory to find the subdirectory
      this.moveSubDirOfDestNotRoot(destRoot, dest, sourceFile, srcRoot, source);
    }
  }

  private void moveSubDirOfDestNotRoot(Directory destRoot, String dest,
      FileType sourceFile, Directory srcRoot, String source) {
    // otherwise parse through the directory to find the subdirectory
    Directory subDir = (Directory) this.parseDirectory("Directory", destRoot,
        dest.substring(0, dest.lastIndexOf('/')));
    // check if the subDir exsits
    if (subDir != null) {
      // check if the new name is valid
      if (sourceFile.equalsType("file.Directory") && dest
          .substring(dest.lastIndexOf('/') + 1).matches("[a-zA-Z0-9]*")) {
        // if the name is valid then move the directory to the new
        // directory and rename
        this.copyAndReplace(sourceFile, subDir,
            dest.substring(dest.lastIndexOf('/') + 1));
        // remove the original
        this.deleteSource(srcRoot, sourceFile, source);
      } else if (sourceFile.equalsType("file.File") && dest
          .substring(dest.lastIndexOf('/') + 1).matches("[a-zA-Z0-9.]*")) {
        // if the name is valid then move the directory to the new
        // directory and rename
        this.copyAndReplace(sourceFile, subDir,
            dest.substring(dest.lastIndexOf('/') + 1));
        // remove the original
        this.deleteSource(srcRoot, sourceFile, source);
      } else {
        System.out.println("Invalid Destination name");
      }
    } else {
      System.out.println("Invalid path for Destination");
    }

  }

  private void moveSubDirectoryOfDestIsRoot(String dest, FileType sourceFile,
      Directory destRoot, Directory srcRoot, String source) {
    if (dest.matches("[a-zA-Z0-9]*")) {
      // if the name is valid then simply move a copy into he new
      // location and rename the original to the new name
      this.copyAndReplace(sourceFile, destRoot, dest);
      // remove the original
      this.deleteSource(srcRoot, sourceFile, source);

    } else {
      System.out.println("Invalid File Name entered For Destination");
    }
  }
}
