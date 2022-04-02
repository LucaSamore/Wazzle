package wazzle.model;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import wazzle.model.common.BonusManager;
import wazzle.model.common.BonusManagerImpl;

public class TestBonus {

	@Test
	public void testBonus() {
		BonusManager bonusManager = new BonusManagerImpl();
		
		bonusManager.updateScoreBonusQuantity(s -> s+1);
		assertEquals(bonusManager.getScoreBonusQuantity(), 1);
		bonusManager.updateWordBonusQuantity(s -> s+3);
		
		assertEquals(bonusManager.getWordBonusQuantity(), 3);
		bonusManager.updateWordBonusQuantity(s -> s-1);
		assertEquals(bonusManager.getWordBonusQuantity(), 2);
		
		var currentScore = 1000;
		var gridTotalScore = 100;
		assertTrue(bonusManager.applyScoreBonus(currentScore, gridTotalScore) == currentScore + gridTotalScore);
		
		long halfMinute = 30000;
		long increment = 120000;
		assertTrue(bonusManager.applyTimeBonus(increment) == halfMinute + increment);
		
		Set<String> toFoundWords = Set.of("cane", "gatto", "topo", "ape", "libellula", "rana");
		assertTrue(toFoundWords.containsAll(bonusManager.applyWordBonus(toFoundWords)));
	}
}