package wazzle.model.maingame.letter;

import java.util.Set;

import wazzle.model.maingame.Mediator;

/**
 * LetterAllocator allocate, randomly, an amount of Characters, with their score, 
 * in order to fill the game grid.
 *
 */

public interface LetterAllocator {
	
	/**
	 * Allocate the Character with their score in the grid, creating an object Letter.
	 * The result of the allocation is is passed to the notifyFromAllocator {@link Mediator} method.
	 */
	void allocate();
	
	/**
	 * Allocate the Character with their score in the grid, creating an object Letter, but it 
	 * doesn't use a {@link Mediator}. 
	 * It's made for tests only.
	 * 
	 * @return Set<Letter> which contains all the Letter allocated in the grid.
	 */
	Set<Letter> allocateForTests();
	
	
	/**
	 * Check the correctness of a grid.
	 * 
	 * @return boolean which indicate the result of the check.
	 */
	boolean checkGridForTests (Set<Letter> alreadyAllocated);
}
