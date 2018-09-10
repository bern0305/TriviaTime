package cst8284.triviatime;
/**
 * FileName: TriviaTimeLaunch.java
 * Author: Mary Anne Bernardino, 040888598
 * Course: CST8284
 * Assignment 2
 * Date: April 18 2018
 * Professor: Dave Houtman
 * Purpose: launches the triviatime game
 */
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * Class that launches the game by creating the primary stage and borderpane to display
 * the 
 * @author Mary Anne Bernardino
 * @version 2
 * @see javafx.animation.FadeTransition
 * @see javafx.animation.ParallelTransition
 * @see javafx.animation.RotateTransition
 * @see javafx.animation.Timeline
 * @see javafx.application.Application
 * @see javafx.scene.Scene
 * @see javafx.scene.effect.Glow
 * @see javafx.scene.text.Text
 * @see javafx.scene.layout.BorderPane
 * @see javafx.stage.Stage
 * @see javafx.util.Duration
 * @since Java 8
 */
public class TriviaTimeLaunch extends Application {
	/**  @Copyright Dave Houtman 2018.  For use in Winter 2018 - CST8284 classes only */
	

	private static BorderPane rootPane;
	
	/**
	 * sets the root pane on the stage to display the title screen
	 * @param primaryStage - where all the game elements are displayed on
	 */
	@Override
	public void start(Stage primaryStage){	
	   // Display Splash Screen
	   setRootPane("Welcome to Trivial Time");
	   getRootPane().setTop(Controls.getMenuBar(primaryStage));
	   Scene scene =  new Scene(getRootPane(), 1024, 512);
	   primaryStage.setTitle("Trivia Time");
	   primaryStage.setScene(scene);
	   primaryStage.show();	
	}
	
	/**
	 * sets effects to the title screen, like fading, rotation and transitions
	 * @param splashString - string to be displayed as the title of the game
	 */
	public static void setRootPane(String splashString) {
		
		rootPane= new BorderPane();
		Text text = new Text(splashString);
		text.setStyle("-fx-font: 40px Tahoma; -fx-stroke: blue; -fx-stroke-width: 1;");
		text.setEffect(new Glow(1.0));
		RotateTransition rot=new RotateTransition (Duration.millis(1000), text); //new rotate transition object
		rot.setAutoReverse(true);
		rot.setByAngle(360f);
		rot.setCycleCount(1);
		FadeTransition fad= new FadeTransition(Duration.millis(1000), text); //new fade transition object
		fad.setFromValue(1.0f);
		fad.setToValue(0.3f);
		fad.setCycleCount(2);
		fad.setAutoReverse(true);
		
		ParallelTransition para=new ParallelTransition(); //new parallel transition object
		para.getChildren().addAll(fad, rot);
		para.setCycleCount(Timeline.INDEFINITE);
		para.play();
		rootPane.setCenter(text);
	}
	
	/**
	 * returns the border pane object
	 * @return returns pane that holds elements of the game
	 */
	public static BorderPane getRootPane() {return rootPane;}
	
	/**
	 * launches game 
	 * @param args 
	 */
	public static void main(String[] args){
		Application.launch(args);
	}
	
}