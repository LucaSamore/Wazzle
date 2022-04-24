package wazzle.model.maingame;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import wazzle.model.maingame.alphabet.WeightedAlphabet;
import wazzle.model.maingame.alphabet.WeightedAlphabetImpl;
import wazzle.model.maingame.letter.score.ScoreConverter;
import wazzle.model.maingame.letter.score.ScoreConverterImpl;

public class TestScoreConverter {
	
	private static final double ERROR = 0.1;

	@Test
	public void testScoreConverter() {
		WeightedAlphabet weightedAlphabet = new WeightedAlphabetImpl(Map.of('A', 29.60,
				'F', 11.5, 
				'E', 19.40, 
				'L', 21.0, 
				'D', 18.5));
		ScoreConverter scoreMapper = new ScoreConverterImpl(weightedAlphabet);
		Map<Character, Double> scoredAlphabet = new HashMap<>(scoreMapper.convert().getWeightedAlphabet());
		assertEquals(weightedAlphabet.getWeightedAlphabet().size(), scoredAlphabet.size());
		assertTrue(scoredAlphabet.get('A') >= 1.0 - ERROR && scoredAlphabet.get('A') <= 1.0 + ERROR);
		assertTrue(scoredAlphabet.get('F') >= 7.0 - ERROR && scoredAlphabet.get('F') <= 7.0 + ERROR);
		assertTrue(scoredAlphabet.get('L') < scoredAlphabet.get('D'));
	}
}