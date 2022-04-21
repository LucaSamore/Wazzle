package wazzle.model.maingame;

import javafx.util.Pair;

/**
 *  Letter is an object which have its content, its position, and its score.
 *
 */

public interface Letter {
	
	/**
	 * Gives the content of the Letter. 
	 * 
	 * @return char which represent the content of the letter.
	 */
	char getContent();
	
	/**
	 * Gives the position of the Letter. 
	 * 
	 * @return Character which represent the position of the letter.
	 */
	Pair<Integer, Integer> getPosition();
	
	/**
	 * Gives the score of the Letter. 
	 * 
	 * @return double which represent the score of the letter.
	 */
	int getScore();
	
}
