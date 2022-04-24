package wazzle.model.minigame;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import wazzle.model.minigame.attempt.MiniGameWord;
import wazzle.model.minigame.attempt.MiniGameWordImpl;
import wazzle.model.minigame.attempt.Result;
import wazzle.model.minigame.attempt.WordChecker;
import wazzle.model.minigame.attempt.WordCheckerImpl;
import wazzle.model.minigame.attempt.WordElement;

public class TestWordChecker {

	@Test
	public void testAttempt() {
		WordChecker wordChecker = new WordCheckerImpl("breve");
		MiniGameWord expectedWord;
		String guessedWord;
		
		
		guessedWord = "pasta";
		List<WordElement> result = new ArrayList<>(List.of(
		        new WordElement('p', Result.WRONG),
				new WordElement('a', Result.WRONG),
				new WordElement('s', Result.WRONG),
				new WordElement('t', Result.WRONG),
				new WordElement('a', Result.WRONG)));
		
		expectedWord = new MiniGameWordImpl(result);
		assertFalse(wordChecker.isCorrectWord(guessedWord));
		assertEquals(expectedWord, wordChecker.computeAttemptResult(guessedWord));
		
	    guessedWord = "creme";
		result = new ArrayList<>(List.of(
		        new WordElement('c', Result.WRONG),
                new WordElement('r', Result.CORRECT),
                new WordElement('e', Result.CORRECT),
                new WordElement('m', Result.WRONG),
                new WordElement('e', Result.CORRECT)));
		
		expectedWord = new MiniGameWordImpl(result);
        assertFalse(wordChecker.isCorrectWord(guessedWord));
        assertEquals(expectedWord, wordChecker.computeAttemptResult(guessedWord));
        
        
        guessedWord = "breee";
        result = new ArrayList<>(List.of(
                new WordElement('b', Result.CORRECT),
                new WordElement('r', Result.CORRECT),
                new WordElement('e', Result.CORRECT),
                new WordElement('e', Result.WRONG),
                new WordElement('e', Result.CORRECT)));
        
        expectedWord = new MiniGameWordImpl(result);
        assertFalse(wordChecker.isCorrectWord(guessedWord));
        assertEquals(expectedWord, wordChecker.computeAttemptResult(guessedWord));
        		
        guessedWord = "everb";
        result = new ArrayList<>(List.of(
                new WordElement('e', Result.CORRECT_WRONG_PLACE),
                new WordElement('v', Result.CORRECT_WRONG_PLACE),
                new WordElement('e', Result.CORRECT),
                new WordElement('r', Result.CORRECT_WRONG_PLACE),
                new WordElement('b', Result.CORRECT_WRONG_PLACE)));
        
        expectedWord = new MiniGameWordImpl(result);
        assertFalse(wordChecker.isCorrectWord(guessedWord));
        assertEquals(expectedWord, wordChecker.computeAttemptResult(guessedWord));
        
        guessedWord = "qwryu";
        result = new ArrayList<>(List.of(
                new WordElement('q', Result.WRONG),
                new WordElement('w', Result.WRONG),
                new WordElement('r', Result.CORRECT_WRONG_PLACE),
                new WordElement('y', Result.WRONG),
                new WordElement('u', Result.WRONG)));
        
        expectedWord = new MiniGameWordImpl(result);
        assertFalse(wordChecker.isCorrectWord(guessedWord));
        assertEquals(expectedWord, wordChecker.computeAttemptResult(guessedWord));
        
        guessedWord = "breve";
        result = new ArrayList<>(List.of(
                new WordElement('b', Result.CORRECT),
                new WordElement('r', Result.CORRECT),
                new WordElement('e', Result.CORRECT),
                new WordElement('v', Result.CORRECT),
                new WordElement('e', Result.CORRECT)));
        
        expectedWord = new MiniGameWordImpl(result);
        assertTrue(wordChecker.isCorrectWord(guessedWord));
        assertEquals(expectedWord, wordChecker.computeAttemptResult(guessedWord));
	}
}