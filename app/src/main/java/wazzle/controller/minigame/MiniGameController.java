package wazzle.controller.minigame;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import wazzle.controller.common.WazzleController;
import wazzle.model.common.Dictionary;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.MiniGame.State;
import wazzle.model.minigame.MiniGameWord;
import wazzle.model.minigame.WordChecker;

// TODO: Auto-generated Javadoc
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
     * @see WordChecker
     * 
     *
     * @param guessedWord the guessed word
     * 
     * @return the state of the word after it's computed.
     */
    MiniGameWord guessWord(String guessedWord);

    /**
     * Obtain bonus if the game is won and returns its identifier.
     *
     * @return the name of the obtained bonus or and empty optional if the game is lost.
     * 
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
     * @see wazzle.model.minigame.MiniGame.State
     *
     * @return he current state of the game.
     */
    public int getStateOfCurrentMinigame();

    /**
     * Gets the list of guessed Minigame words so far.
     *
     * @see wazzle.model.minigame.MiniGameWord
     * 
     * @return the guessed Minigame words so far.
     */
    List<MiniGameWord> getGuessedMinigameWordsSoFar();
    
    /**
     * Gets the main controller.
     *
     * @return the main controller
     */
    WazzleController getMainController();

    int getLetterResultAtIndex(int index);
    
    char getLetterCharAtIndex(int index);

    List<Character> getAllWrongLetters();
    
    boolean isTheLetterCorrectInAnotherPlace(char letter);
}
