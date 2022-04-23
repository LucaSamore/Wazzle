package wazzle.model.maingame;

import javafx.util.Pair;

/**
 *  This interface encapsulates the concept of letter.
 *
 */
public interface Letter {
	
	/**
	 * Gives the content of the {@link Letter}. 
	 * 
	 * @return char which represent the content of the {@link Letter}.
	 */
	char getContent();
	
	/**
	 * Gives the position of the {@link Letter}. 
	 * 
	 * @return Character which represent the position of the {@link Letter}.
	 */
	Pair<Integer, Integer> getPosition();
	
	/**
	 * Gives the score of the {@link Letter}. 
	 * 
	 * @return double which represent the score of the {@link Letter}.
	 */
	int getScore();
	
}
