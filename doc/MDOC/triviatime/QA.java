package cst8284.triviatime;
/**
 * FileName: QA.java
 * Author: Mary Anne Bernardino, 040888598
 * Course: CST8284
 * Assignment 2
 * Date: April 18 2018
 * Professor: Dave Houtman
 * Purpose: contains methods and variables needed to create a QA object
 */
/**
 * Class that creates QA objects from the information contained in the trivia file
 * @author Mary Anne Bernardino
 * @see cst8284.triviatime
 * @since Java 8
 *
 */

@SuppressWarnings("serial")
public class QA extends QARequirements {
	
	private String question, category, explanation;
	private int correctAnswer, points, difficulty;
	private String[] answers;
	private boolean result;
	
	public String getQuestion(){return question;}

	public void setQuestion(String question){this.question = question;}
	
	public String[] getAnswers(){return answers;}
	public void setAnswers(String[] answers){
		this.answers = answers;
//		this.answers = new String[answers.length];
//		for (int i=0; i < answers.length; i++) this.answers[i]=answers[i];
	}
	
	public String getExplanation() {return explanation;}
	public void setExplanation(String explanation) {this.explanation = explanation;}
	
	public String getCategory(){return category;}
	public void setCategory(String category){this.category = category;}
	
	public int getDifficulty(){return difficulty;}
	public void setDifficulty(int difficulty){this.difficulty = difficulty;}
	
	public int getPoints(){return points;}
	public void setPoints(int points){this.points = points;}
	
	public int getCorrectAnswerNumber(){return correctAnswer;}
	public void setCorrectAnswerNumber(int correctAnswer){this.correctAnswer = correctAnswer;}
	
	public void setResult(boolean b){result = b;}
	public boolean isCorrect(){return result;}
	
	 public QA(String q, String[] a, String category, String explanation, int diff, int points, int correctAnswer){
		setQuestion(q);
		setAnswers(a);
		setCategory(category);
		setExplanation(explanation);
		setDifficulty(diff);
		setPoints(points);
		setCorrectAnswerNumber(correctAnswer);
	}
	
	
}
