package wazzle.controller.minigame;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import wazzle.controller.common.WazzleController;
import wazzle.model.minigame.MiniGame.State;
import wazzle.model.minigame.MiniGameWord;
import wazzle.model.minigame.Result;
import wazzle.model.minigame.WordChecker;

/**
 * The Interface MiniGameController.
 */
public interface MiniGameController {

    /**
     * Save the current state of minigame to serialize.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     * @see WazzleController
     */
    void saveMiniGame() throws IOException;
    
    /**
     * If it's present, this method loads a previous saved minigame,
     * if not it starts a new one.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     * @see WazzleController#deleteEndedMiniGame()
     */
    void startGame() throws IOException ;

    /**
     * Guess if the word is correct.
     * 
     * @see WordChecker
     * @param guessedWord - the guessed word
     * @return state - the state of the word after it's computed.
     */
    MiniGameWord guessWord(String guessedWord);

    /**
     *  
     * Check if game is won an return an Optional, if the optional is empty, it means that the game is lost
     * and therefore no bonus has been obtained.
     *
     * @return name -   the name of the obtained bonus if present, and empty optional if the game is lost.
     *                  if the optional is empty, it means that the game is lost and therefore
     *                  no bonus has been obtained.
     * @throws IOException Signals that an I/O exception has occurred.
     */
    Optional<String> obtainedBonus() throws IOException;
    
    /**
     * Gets the word that must be guessed of the current Minigame.
     *
     * @return the word that must be guessed.
     */
    String getTargetWord();
    
    /**
     * Gets the current attempts number.
     *
     * @return the current attempts number
     */
    int getCurrentAttemptsNumber();

    /**
     * Gets the word length.
     *
     * @return the word length
     */
    int getWordLength();

    /**
     * Gets the max attempts number.
     *
     * @return the max attempts number
     */
    int getMaxAttemptsNumber();

    /**
     * Gets the current state of the game.
     *
     * @see State
     * @return he current state of the game.
     */
    public int getStateOfCurrentMinigame();

    /**
     * Gets the list of guessed Minigame words so far.
     *
     * @see wazzle.model.minigame.MiniGameWord
     * @return the guessed Minigame words so far.
     */
    List<MiniGameWord> getGuessedMinigameWordsSoFar();
    
    /**
     * Gets the main controller.
     * @see WazzleController
     * @return controller - the main controller
     */
    WazzleController getMainController();

    /**
     * Given an index, this method returns the corresponding result code
     * of the letter contained in the last inserted word.
     * 
     * @see {@link Result}, {@link MiniGameWord}, {@link WordElement}
     * @param index - index of the letter you want to know the result of
     * @return code - the code corresponding to the result of the requested letter
     */
    int getLetterResultAtIndex(int index);
    
    /**
     * Given an index, this method returns the corresponding letter contained
     * in the last inserted word.
     * 
     * @see Result
     * @param index - index of the letter you want to know the result of
     * @return code - the code corresponding to the result of the requested letter
     */
    char getLetterCharAtIndex(int index);

    /**
     * This method returns all the wrong letters in the last inserted word.
     * 
     * @return A list containing all the letters that are not present 
     */
    List<Character> getAllWrongLetters();
    
    /**
     * Return true if the given word is correct but not in the correct place.
     * 
     * @return true - if the given word is correct but not in the correct place
     */
    boolean isTheLetterCorrectInAnotherPlace(char letter);
}
