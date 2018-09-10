package cst8284.triviatime;
/**
 * FileName: Controls.java
 * Author: Mary Anne Bernardino, 040888598
 * Course: CST8284
 * Assignment 2
 * Date: April 18 2018
 * Professor: Dave Houtman
 * Purpose: Holds objects that affect the running of the triviatime game
 * Class list: Controls, ByDiff, ByTop
 */
import java.io.File;
import java.util.Collections;
import java.util.Comparator;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

	/**This class controls what is displayed when the game starts. It displays the menu,
	 * menu items, and questions involved in the triviatime game.
	 *  
	 * @author Mary Anne Bernardino
	 * @version 2
	 * @see java.io.File
	 * @see javafx.application.Platform
	 * @see javafx.event.ActionEvent
	 * @see javafx.scene.control.Alert
	 * @see javafx.scene.control.Alert.AlertType
	 * @see javafx.scene.control.Menu
	 * @see javafx.scene.control.MenuBar
	 * @see javafx.scene.control.MenuItem
	 * @see javafx.scene.input.KeyCode
	 * @see javafx.scene.input.KeyCodeCombination
	 * @see javafx.scene.input.KeyCombination
	 * @see javafx.scene.layout.BorderPane
	 * @see javafx.scene.layout.HBox
	 * @see javafx.scene.layout.VBox
	 * @see javafx.stage.FileChooser
	 * @see javafx.stage.Stage;
	 * @since Java
	 */
public class Controls {
	/**
	 * @field mnuItm - holds a reference to each menu item placed inside a menu
	 * @field mnu - holds a reference to each menu on the menu bar
	 * @field stage - where all the items are placed
	 * @field currentQuestion - counter to keep track of what question number the game is on
	 */
	/**** Generic Menu/Menu Item Properties ****/
	private static MenuItem mnuItm; 
	private static Menu mnu;
	private static Stage stage;
	private static int currentQuestion = 0;
	

	/**
	 * Sets the content of the menu bar at the top of the triviatime stage
	 * 
	 * @param primaryStage the stage that holds the the entire contents of the game
	 * @return MenuBar object at the top of the stage
	 * @see getMenuBar
	 */
	/***************** MenuBar *****************/
	public static MenuBar getMenuBar(Stage primaryStage) {
		setStage(primaryStage);
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(getMnuFile(), getMnuSettings(), getMnuHelp());
		return menuBar;
	}
	
	/**
	 * adds the file menu and the new game and exit menu items to the stage
	 * @return Menu object that holds the menu items new game and exit
	 */
	/******************* Menu ******************/
	private static Menu getMnuFile() {
		mnu = new Menu("_File");
		mnu.getItems().addAll(
			getMnuItmNewGame(), 
			getMnuItmExit()
		);
	
		mnu.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN)); //setting accelerator key for the file menu option
		return mnu;
	}

	/**
	 * returns Menu object that allows for certain settings to be chosen in the game
	 * @return menu object that holds random question, increasing difficult and by topic
	 * menu items
	 */
	private static Menu getMnuSettings() {
		mnu = new Menu("_Settings");
		mnu.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN)); //setting accelerator key
		
		mnu.getItems().addAll(getRandomQuest(), increaseDiff(), byTopic()); //adding menu items to the setting menu option
		
		return mnu;   
	} 

	/**
	 * returns Menu object that allows the user to find the about page of the game
	 * @return help menu item that allows for the author of the game to be shown
	 */
	private static Menu getMnuHelp() {
		mnu = new Menu("_Help");
		mnu.getItems().addAll(getMnuItmAbout());
		mnu.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN)); //setting accelerator key
		return mnu;
	}

	/**
	 * return Menu item object that allows the player to choose a trivia file, and load the
	 * questions onto the stage via a borderpane and QAPane that gets the next question
	 *
	 *@return the menu item object with a borderpane that has the next question
	 */
	/***************** MenuItems *****************/
	private static MenuItem getMnuItmNewGame() {
		
		mnuItm = new MenuItem("_New Game");
		mnuItm.setOnAction((ActionEvent e) -> {
			currentQuestion=0;
			FileChooser fileCh=new FileChooser(); //creating file chooser object
			fileCh.setInitialDirectory(new File("C:/TriviaTime")); //setting default directory to find trivia files
			File file1=fileCh.showOpenDialog(getStage()); //allowing player to choose a trivia file
			
			BorderPane bp = (BorderPane)getStage().getScene().getRoot();
			bp.setStyle("-fx-font: 16px Tahoma; -fx-stroke: black; -fx-stroke-width: 1;");
			
			FileUtils.setQAArrayList(file1.getAbsolutePath());
			
			QAPane qa = new QAPane(Controls.getNextQA());
			
			VBox rBuf = new VBox(); rBuf.setPrefWidth(100); bp.setRight(rBuf);
		 	VBox lBuf = new VBox(); lBuf.setPrefWidth(100);	bp.setLeft(lBuf);
		    HBox tBuf = new HBox(); tBuf.setPrefHeight(100); bp.setTop(tBuf);

		    bp.setCenter(qa.getQAPane());
		    bp.setTop(getMenuBar(getStage()));
		});
		mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN)); //setting accelerator key
		return mnuItm;
	}

	/**
	 * returns the menu item object that is responsible for exiting the game
	 * @return exits the game when the exit button is selected
	 */
	private static MenuItem getMnuItmExit() {
		mnuItm = new MenuItem("_Exit");
		mnuItm.setOnAction((ActionEvent e) -> Platform.exit());
		mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN)); //setting accelerator key
		return mnuItm;
	}
	/**
	 * returns the menu item object that holds the information about the author of the game
	 * @return information about the author
	 */
	private static MenuItem getMnuItmAbout() {
		/* From Marco Jakob, code.makery, */
		/* http://code.makery.ch/blog/javafx-dialogs-official/ */
		mnuItm = new MenuItem("_About");
		mnuItm.setOnAction((ActionEvent e) -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About");
			alert.setHeaderText("About Trivia Time");
			alert.setContentText(
					"Mary Anne Bernardino from sample code provided by Prof. Dave Houtman, ©2018");
			alert.showAndWait();
		});
		mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN)); //setting accelerator key
		return mnuItm;
	}
	/**
	 * returns the array of question from the trivia file in a random order
	 * @return menu item object containing a randomly sorted array of questions
	 */
	private static MenuItem getRandomQuest() {
		mnuItm=new MenuItem("_Randomize Questions");
		mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN)); //setting accelerator key
		
		mnuItm.setOnAction((ActionEvent e)->{
			Collections.shuffle(FileUtils.getQAArrayList()); //shuffling QA arraylist randomly
		});
		return mnuItm;
	}
	/**
	 * Inner class responsible for sorting QA objects by difficulty 
	 * @author Mary Anne Bernardino
	 * @see java.util.Comparator;
	 *
	 */
	public static class ByDiff implements Comparator<QA>{
		@Override
		/**
		 * Overriding the Comparator's compare method to compare difficulty of QA
		 * object's questions
		 * @param q1 - QA object 
		 * @param q2 - QA object
		 * @return integer value, if q1 is less than q2 (returns negative integer),
		 * if q1 greater than q2 (return positive integer), and equal (returns zero)
		 */
		public int compare(QA q1, QA q2) {
			return (q1.getDifficulty()-q2.getDifficulty());
		}
	}
	/**
	 * returns sorted array by difficulty using inner class ByDiff
	 * @return menu item object that sorts array by difficulty
	 */
	private static MenuItem increaseDiff() {
		mnuItm=new MenuItem("_Increasing Difficulty");
		mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN));
		
		mnuItm.setOnAction((ActionEvent e)->{
			Collections.sort(FileUtils.getQAArrayList(), new ByDiff()); //sorting arraylist by difficulty
			
		});
		return mnuItm;
	}
	/**
	 * Inner class that helps sort array by topic
	 * @author Mary Anne Bernardino
	 * @see import java.util.Comparator
	 */
	public static class ByTop implements Comparator<QA>{
		@Override
		/**
		 * returns if q1 is less than, equal or greater than q2 by topic
		 * @param QA object q1
		 * @param QA object q2
		 * @return integer that represents of q1 is greater, less than or equals to q2
		 */
		public int compare(QA q1, QA q2) {
			return (q1.getCategory().compareTo(q2.getCategory())); 
		}
	}
	/**
	 * return menu item object that holds the sorted array by topic
	 * @return by topic sorted array
	 */
	private static MenuItem byTopic() {
		mnuItm=new MenuItem("_By Topic");
		mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN)); //setting accelerator key
		mnuItm.setOnAction((ActionEvent e)->{
			Collections.sort(FileUtils.getQAArrayList(), new ByTop()); //sorting arraylist by category
			
		});
		return mnuItm;
	}
	
	/**
	 * sets stage for questions to be displayed on
	 * @param s - stage object variable
	 */
	private static void setStage(Stage s) {stage= s;}
	/**
	 * returns the stage
	 * @return stage object
	 */
	public static Stage getStage() {return stage;}

	/**
	 * returns QA object of the next question in the array
	 * @return QA object 
	 */
	public static QA getNextQA() {
		if (currentQuestion < FileUtils.getQAArrayList().size())
		   return FileUtils.getQAArrayList().get(currentQuestion++); //getting new question from arraylist
		else
		   return null;
	}

}
