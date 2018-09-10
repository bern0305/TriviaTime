package cst8284.triviatime;
/**
 * FileName: QARequirements.java
 * Author: Mary Anne Bernardino, 040888598
 * Course: CST8284
 * Assignment 2
 * Date: April 18 2018
 * Professor: Dave Houtman
 * Purpose: abstract methods implemented in the QA class
 */
import java.io.Serializable;
/**
 * Abstract class that holds methods needed to be implemented for creating QA objects
 * @author Mary Anne Bernardino
 * @see java.io.Serializable
 * @since Java 8
 *
 */
public abstract class QARequirements implements Serializable {
	
	public static final long serialVersionUID = 1L;
	public abstract String getQuestion();
	public abstract void setQuestion(String question);
	
	public abstract String[] getAnswers();
	public abstract void setAnswers(String[] answers);
	
	public abstract String getExplanation();
	public abstract void setExplanation(String question);
	
	public abstract String getCategory();
	public abstract void setCategory(String category);
	
	public abstract int getDifficulty();
	public abstract void setDifficulty(int difficulty);
	
	public abstract int getPoints();
	public abstract void setPoints(int points);
	
	public abstract int getCorrectAnswerNumber();
	public abstract void setCorrectAnswerNumber(int correctAnswer);
	
	public abstract boolean isCorrect();
	public abstract void setResult(boolean b);

}
