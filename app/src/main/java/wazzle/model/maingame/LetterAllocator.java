package wazzle.model.maingame;

import java.util.Set;

/**
 * LetterAllocator allocate, randomly, an amount of Characters, with their score, 
 * in order to fill the game grid.
 *
 */

public interface LetterAllocator {
	
	/**
	 * Allocate the Character with their score in the grid, creating an object Letter.
	 * 
	 * @return Set<Letter> which contains all the Letter allocated in the grid.
	 */
	Set<Letter> allocate();
	
	/**
	 * Check the correctness of a grid.
	 * 
	 * @return boolean which indicate the result of the check.
	 */
	boolean checkGridForTests (Set<Letter> alreadyAllocated);
}
