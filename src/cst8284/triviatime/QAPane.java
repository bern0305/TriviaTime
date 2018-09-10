package cst8284.triviatime;
/**
 * FileName: QAPane.java
 * Author: Mary Anne Bernardino, 040888598
 * Course: CST8284
 * Assignment 2
 * Date: April 18 2018
 * Professor: Dave Houtman
 * Purpose: displays QA object
 */
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Class that displays the QA object and helps navigate through the questions
 * @author Mary Anne Bernardino
 * @version 2
 * @see javafx.geometry.Pos
 * @see javafx.scene.control.Alert
 * @see javafx.scene.control.Button
 * @see javafx.scene.control.ButtonType
 * @see javafx.scene.control.Label
 * @see javafx.scene.control.RadioButton
 * @see javafx.scene.control.ToggleGroup
 * @see javafx.scene.layout.HBox
 * @see javafx.scene.layout.VBox;
 * @since Java 8
 *
 */
public class QAPane {
   private RadioButton[] rbAr;
   private static VBox qaPane;
   private static Button thatsMyAnsBtn;
   private static Button btnNext;
   
   /**
    * constructor that initializes the QA object components to be displayed on the pane
    * @param qa - QA object being displayed on the pane
    */
   public QAPane(QA qa) {
	   
	   HBox questionBox = new HBox(); 
	   questionBox.getChildren().add(new Label(qa.getQuestion()));
	   
	   VBox explanationBox = new VBox(); 
	   explanationBox.setPrefHeight(200);
	   explanationBox.setAlignment(Pos.CENTER_LEFT);
	   
	   thatsMyAnsBtn= new Button("That's my answer");
	   btnNext = new Button("  Next Question   ");
		
	   qaPane = new VBox();
	   qaPane.getChildren().addAll(
			questionBox, 
			getAnswerPane(qa.getAnswers()), 
			explanationBox, 
			getThatsMyAnswerBox(qa, explanationBox),
			getNextQuestionBox());
	   setQAPane(qaPane);
   }
   
   /**
    * pane where the explanation of the question is displayed, sets action of
    * the that's my answer button
    * @param qa - QA object where the explanation is extracted from
    * @param explanationBox - where the explanation will be displayed
    * @return HBox that contains both the thatsMyAnsBtn and the explanation of the current question
    */
   private HBox getThatsMyAnswerBox (QA qa, VBox explanationBox) {
	   
	   HBox thatsMyAnsBox = new HBox();
	   thatsMyAnsBtn.setOnAction((e)->{
		   if (getRadioButtonSelected() < 0) {
			  Alert alert = new Alert(AlertType.WARNING, "Must choose an answer first", ButtonType.OK);
		      alert.showAndWait();
		   }
		   else {
			  qa.setResult(isAnswerCorrect(qa));
		      explanationBox.getChildren().add(new Label(qa.getExplanation()));
		      thatsMyAnsBtn.setDisable(true);
		      btnNext.setDisable(false);
		   }
	   });
	   thatsMyAnsBox.getChildren().add(thatsMyAnsBtn);
	   thatsMyAnsBox.setAlignment(Pos.CENTER);
	   return thatsMyAnsBox;
    }
   /**
    * returns an HBox with the next question/results 
    * @return displays the next question/results
    */
	private HBox getNextQuestionBox() {
		
	   HBox hbxNextQuestion = new HBox(); hbxNextQuestion.setPrefHeight(200);
	   hbxNextQuestion.setAlignment(Pos.CENTER_RIGHT);
	   hbxNextQuestion.getChildren().add(btnNext); 
	   
	   QA qa = Controls.getNextQA();
	   if (qa != null){ // Still questions to load?
	      btnNext.setDisable(true);
	      btnNext.setOnAction((e)->{    
	         QAPane qaPane = new QAPane(qa);
		     TriviaTimeLaunch.getRootPane().setCenter(qaPane.getQAPane());
		     thatsMyAnsBtn.setDisable(false);	  
	      });   
	   }
	   else { //final question
		  btnNext.setText("Check Results");
		  btnNext.setOnAction((e) -> 
			  TriviaTimeLaunch.getRootPane().setCenter(ResultsPane.getResults())
		  ); 
	   } 
	   return hbxNextQuestion;
	}
   private VBox getAnswerPane(String[] answerStrAr){	   
	  VBox answerBox = new VBox();
	  rbAr = new RadioButton[answerStrAr.length];
      int rbCtr = 0; final ToggleGroup tg = new ToggleGroup();
      for (String ans: answerStrAr){
         rbAr[rbCtr] = new RadioButton(ans);
         rbAr[rbCtr].setToggleGroup(tg);
         answerBox.getChildren().add(rbAr[rbCtr++]);
      } 
      return answerBox;
   }
	
   private int getRadioButtonSelected(){
      int btnCtr = 1;  
      if (rbAr != null)
         for (RadioButton rb: rbAr){
            if (rb.isSelected()) break;
            btnCtr++;
         }
      return (btnCtr > rbAr.length)? -1: btnCtr;
   }
   
   private boolean isAnswerCorrect(QA qa) {
	   return (qa.getCorrectAnswerNumber() == getRadioButtonSelected());
   }
   
   private void setQAPane(VBox vb) {QAPane.qaPane = vb;}
   public VBox getQAPane() {return qaPane;}
   
}
