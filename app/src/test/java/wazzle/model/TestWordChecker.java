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

public class TestWordChecker {

	@Test
	public void testAttempt() {
		WordChecker wordChecker = new WordCheckerImpl();
		MiniGameWord expectedWord;
		Attempt attempt = new AttemptImpl("gatto", "fatta");
		
		List<Pair<Character, Result>> result = new ArrayList<>(List.of(new Pair<>('f', Result.WRONG),
				new Pair<>('a', Result.CORRECT),
				new Pair<>('t', Result.CORRECT),
				new Pair<>('t', Result.CORRECT),
				new Pair<>('a', Result.CORRECT_WRONG_PLACE)));
		
		expectedWord = new MiniGameWordImpl();
		expectedWord.setCompositeWord(result);
		expectedWord.setWrong();
		assertEquals(expectedWord, wordChecker.computeAttemptResult(attempt));
		
		attempt = new AttemptImpl("crema", "grana");
		result = new ArrayList<>(List.of(new Pair<>('g', Result.WRONG),
				new Pair<>('r', Result.CORRECT),
				new Pair<>('a', Result.CORRECT_WRONG_PLACE),
				new Pair<>('n', Result.WRONG),
				new Pair<>('a', Result.CORRECT)));
		
		expectedWord = new MiniGameWordImpl();
		expectedWord.setCompositeWord(result);
		expectedWord.setWrong();
		assertEquals(expectedWord, wordChecker.computeAttemptResult(attempt));
		
		attempt = new AttemptImpl("cappa", "frodi");
		result = new ArrayList<>(List.of(new Pair<>('f', Result.WRONG),
				new Pair<>('r', Result.WRONG),
				new Pair<>('o', Result.WRONG),
				new Pair<>('d', Result.WRONG),
				new Pair<>('i', Result.WRONG)));
		
		expectedWord = new MiniGameWordImpl();
		expectedWord.setCompositeWord(result);
		expectedWord.setWrong();
		assertEquals(expectedWord, wordChecker.computeAttemptResult(attempt));
		
		attempt = new AttemptImpl("gatto", "gatto");
		result = new ArrayList<>(List.of(new Pair<>('g', Result.CORRECT),
				new Pair<>('a', Result.CORRECT),
				new Pair<>('t', Result.CORRECT),
				new Pair<>('t', Result.CORRECT),
				new Pair<>('o', Result.CORRECT)));
		expectedWord = new MiniGameWordImpl();
		expectedWord.setCompositeWord(result);
		expectedWord.setCorrect();
		assertEquals(expectedWord, wordChecker.computeAttemptResult(attempt));
	}
}