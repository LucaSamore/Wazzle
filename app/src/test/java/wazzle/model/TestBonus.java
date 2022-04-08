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
		
		//ScoreBonus
		bonusManager.updateScoreBonusQuantity(b -> b+1);
		assertEquals(bonusManager.getScoreBonusQuantity(), 1);
		var currentScore = 1000;
		var gridTotalScore = 100;
		assertTrue(bonusManager.applyScoreBonus(currentScore, gridTotalScore) == currentScore + gridTotalScore);
		assertEquals(bonusManager.getScoreBonusQuantity(), 0);
		
		//WordBonus
		bonusManager.updateWordBonusQuantity(b -> b+3);
		assertEquals(bonusManager.getWordBonusQuantity(), 3);
		bonusManager.updateWordBonusQuantity(b -> b-1);
		assertEquals(bonusManager.getWordBonusQuantity(), 2);
		Set<String> toFoundWords = Set.of("cane", "gatto", "topo", "ape", "libellula", "rana");
		assertTrue(toFoundWords.containsAll(bonusManager.applyWordBonus(toFoundWords)));
		assertEquals(bonusManager.getWordBonusQuantity(), 1);
		
		//TimeBonus
		bonusManager.updateTimeBonusQuantity(b -> b+2);
		assertEquals(bonusManager.getTimeBonusQuantity(), 2);	
		long halfMinute = 30000;
		long increment = 120000;
		assertTrue(bonusManager.applyTimeBonus(increment) == halfMinute + increment);
		assertEquals(bonusManager.getTimeBonusQuantity(), 1);
		
		//Extract
		int startQuantity = bonusManager.getScoreBonusQuantity() + bonusManager.getWordBonusQuantity() 
						  + bonusManager.getTimeBonusQuantity();
		bonusManager.extractBonus();
		int finalQuantity = bonusManager.getScoreBonusQuantity() + bonusManager.getWordBonusQuantity() 
		  + bonusManager.getTimeBonusQuantity();
		assertEquals(1, finalQuantity - startQuantity);
		
	}
}