package cst8284.triviatime;
/**
 * FileName: ResultsPane.java
 * Author: Mary Anne Bernardino, 040888598
 * Course: CST8284
 * Assignment 2
 * Date: April 18 2018
 * Professor: Dave Houtman
 * Purpose: formats and displays results of the game
 */
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Class that displays the results of the game
 * @author Mary Anne Bernardino
 * @see javafx.geometry.Pos
 * @see javafx.scene.control.ScrollPane
 * @see javafx.scene.control.ScrollPane.ScrollBarPolicy
 * @see javafx.scene.layout.HBox
 * @see javafx.scene.layout.VBox
 * @see javafx.scene.text.Text
 * @since Java 8
 *
 */
public class ResultsPane {
	public static ScrollPane getResults() {
	    VBox results = new VBox();
	    results.setMinWidth(300);
	    results.setStyle("-fx-font: 20px Tahoma; -fx-stroke: black; -fx-stroke-width: 1;");
	    
	    int questionNum = 1, correctAns = 0;
		for (QA qa: FileUtils.getQAArrayList()) { 
		   results.getChildren().add(new Text("  " + questionNum++ + 
			  ".  " + (qa.isCorrect()?"Right":"Wrong")));		
		   correctAns += qa.isCorrect()?1:0;
		}
		
		HBox marks = new HBox();
		marks.setAlignment(Pos.CENTER_RIGHT);	
		marks.getChildren().add(new Text("Results:" + 
				correctAns + "/" + FileUtils.getQAArrayList().size())); //getting ratio of total number of correct answers from total number of questions
		results.getChildren().add(marks);	
		
		ScrollPane spResults = new ScrollPane();
		spResults.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		spResults.setHbarPolicy(ScrollBarPolicy.AS_NEEDED); 
		spResults.setContent(results);
		return spResults;
	}
}
