package wazzle.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import wazzle.model.maingame.ScoreMapper;
import wazzle.model.maingame.ScoreMapperImpl;
import wazzle.model.maingame.WeightedAlphabet;
import wazzle.model.maingame.WeightedAlphabetImpl;

public class TestScoreAdapter {

	@Test
	public void testScoreAdapter() {
		WeightedAlphabet weightedAlphabet = new WeightedAlphabetImpl(Map.of('A', 29.60,
				'F', 11.5, 
				'E', 19.40, 
				'L', 21.0, 
				'D', 18.5));
		ScoreMapper scoreMapper = new ScoreMapperImpl(weightedAlphabet);
		Map<Character, Double> scoredAlphabet = new HashMap<>(scoreMapper.convert().getWeightedAlphabet());
		assertTrue(scoredAlphabet.get('A').equals((double) 5.0));
		assertTrue(scoredAlphabet.get('F').equals((double) 0.0));
		assertTrue(scoredAlphabet.get('L') > scoredAlphabet.get('D'));
	}
}
