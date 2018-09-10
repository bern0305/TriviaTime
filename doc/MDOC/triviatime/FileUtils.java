package cst8284.triviatime;

import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Class handles everything to do with the trivia file chosen for the game, by
 * setting the array of QA objects, to returning the array, to reading the trivia file,
 * and getting the absolute path of the trivia file
 * @author Mary Anne Bernardino
 * @version 2
 * @see javafx.stage.Stage
 * @see javafx.stage.FileChooser
 * @see javafx.stage.FileChooser.ExtensionFilter
 * @see java.io.File
 * @see java.io.FileInputStream
 * @see java.io.IOException
 * @see java.io.ObjectInputStream
 * @see java.util.ArrayList;
 * @since Java 8
 */
public class FileUtils {
	private static ArrayList<QA> qaAL; //declaring arraylist reference variable
	/**
	 * retrieves chosen trivia file 
	 * @param primaryStage - where the QA objects will be displayed
	 * @return trivia file holding information about the QA objects
	 */
	public static File getFileHandle(Stage primaryStage) {
		FileChooser fc = new FileChooser();
		fc.setTitle("Open Trivia File");
		fc.getExtensionFilters().addAll(
				new ExtensionFilter("Trivia Files", "*.trivia"),
				new ExtensionFilter("All Files", "*.*")
		);
		File trivFile = fc.showOpenDialog(primaryStage);
		return (trivFile);
	}
	/**
	 * sets array of QA objects by reading the trivia file chosen by player
	 * @param absPath absolute path of trivia file being use to gather QA object information
	 * 
	 */
	public static void setQAArrayList(String absPath) {
		qaAL = new ArrayList<QA>(); //initialized a new arraylist
		if (fileExists(absPath)) {
			try {
				FileInputStream fis = new FileInputStream(absPath);
				ObjectInputStream ois = new ObjectInputStream(fis);
				while(true) {
					qaAL.add((QA) (ois.readObject())); //populates the arraylist QA objects according to the trivia file
					
				}
			} catch (IOException | ClassNotFoundException e) {} 
			
		}
		else 
			qaAL = null;
	}
	/**
	 * returns the Arraylist of QA objects
	 * @return array list of QA objects
	 */
	public static ArrayList<QA> getQAArrayList() {return qaAL;}
	/**
	 * ensures that the trivia file chosen is appropriate to use for the game
	 * @param f - trivia file that is chosen by player
	 * @return returns true if the file is compatible to use in game, false otherwise
	 */
	public static boolean fileExists(File f) {
		return (f != null && f.exists() && f.isFile() && f.canRead() && (f.length() > 2));
	}
	/**
	 * checking if the file chosen exist in user's system
	 * @param s - 
	 * @return
	 */
	public static boolean fileExists(String s) {
		return (fileExists(new File(s)));
	}
	/**
	 * gets the absolute path of the trivia file chosen
	 * @param f - chosen trivia file to test
	 * @return absolute path of the trivia file
	 */
	public static String getAbsPath(File f) {
		return f.getAbsolutePath();
	}

}
