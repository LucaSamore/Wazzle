package wazzle.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import javafx.util.Pair;
import wazzle.model.minigame.Attempt;
import wazzle.model.minigame.AttemptImpl;
import wazzle.model.minigame.MiniGameWord;
import wazzle.model.minigame.MiniGameWordImpl;
import wazzle.model.minigame.Result;
import wazzle.model.minigame.WordChecker;
import wazzle.model.minigame.WordCheckerImpl;
import wazzle.model.minigame.WordElement;

public class TestWordChecker {
//
//	@Test
//	public void testAttempt() {
//		WordChecker wordChecker = new WordCheckerImpl();
//		MiniGameWord expectedWord;
//		Attempt attempt = new AttemptImpl("gatto", "fatta");
//		
//		List<WordElement> result = new ArrayList<>(List.of(new WordElement('f', Result.WRONG.getState()),
//				new  WordElement('a', Result.CORRECT.getState()),
//				new  WordElement('t', Result.CORRECT.getState()),
//				new  WordElement('t', Result.CORRECT.getState()),
//				new  WordElement('a', Result.CORRECT_WRONG_PLACE.getState())));
//		
//		expectedWord = new MiniGameWordImpl();
//		expectedWord.setCompositeWord(result);
//		assertFalse(wordChecker.isCorrectWord(attempt));
//		assertEquals(expectedWord, wordChecker.computeAttemptResult(attempt));
//		
//		attempt = new AttemptImpl("crema", "grana");
//		result = new ArrayList<>(List.of(new WordElement('g', Result.WRONG.getState()),
//				new	 WordElement('r', Result.CORRECT.getState()),
//				new	 WordElement('a', Result.CORRECT_WRONG_PLACE.getState()),
//				new	 WordElement('n', Result.WRONG.getState()),
//				new	 WordElement('a', Result.CORRECT.getState())));
//		
//		expectedWord = new MiniGameWordImpl();
//		expectedWord.setCompositeWord(result);
//		assertFalse(wordChecker.isCorrectWord(attempt));
//		assertEquals(expectedWord, wordChecker.computeAttemptResult(attempt));
//		
//		attempt = new AttemptImpl("cappa", "frodi");
//		result = new ArrayList<>(List.of(new WordElement('f', Result.WRONG.getState()),
//				new WordElement('r', Result.WRONG.getState()),
//				new WordElement('o', Result.WRONG.getState()),
//				new WordElement('d', Result.WRONG.getState()),
//				new WordElement('i', Result.WRONG.getState())));
//		
//		expectedWord = new MiniGameWordImpl();
//		expectedWord.setCompositeWord(result);
//		assertFalse(wordChecker.isCorrectWord(attempt));
//		assertEquals(expectedWord, wordChecker.computeAttemptResult(attempt));
//		
//		attempt = new AttemptImpl("gatto", "gatto");
//		result = new ArrayList<>(List.of(new WordElement('g', Result.CORRECT.getState()),
//				new WordElement('a', Result.CORRECT.getState()),
//				new WordElement('t', Result.CORRECT.getState()),
//				new WordElement('t', Result.CORRECT.getState()),
//				new WordElement('o', Result.CORRECT.getState())));
//		expectedWord = new MiniGameWordImpl();
//		expectedWord.setCompositeWord(result);
//		assertTrue(wordChecker.isCorrectWord(attempt));
//		assertEquals(expectedWord, wordChecker.computeAttemptResult(attempt));
//	}
}