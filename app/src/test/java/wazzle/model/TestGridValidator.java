package wazzle.model;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.util.Set;

import org.junit.Test;

import javafx.util.Pair;
import wazzle.model.common.Dictionary;
import wazzle.model.common.DictionaryImpl;
import wazzle.model.maingame.Difficulty;
import wazzle.model.maingame.GridValidator;
import wazzle.model.maingame.GridValidatorImpl;
import wazzle.model.maingame.Mediator;

public class TestGridValidator {
	
	
	//The purpose of this test is to see the set of words (and how many of them) I can build with a certain number (gridShape) of generated letters
	
//	private static final int ITERATIONS = 50;
//	
//	private final Difficulty easy = new Difficulty(126, 250, 240000L);
//	
//	@Test
//	public void testValidationOutput() {
//		try {
//			final Dictionary dataset = new DictionaryImpl(".\\src\\test\\res\\datasetNuovo.txt");
//			final Mediator mediator = new Mediator(dataset, new Pair<>(6,6));
//			final GridValidator validator = new GridValidatorImpl(dataset, this.easy);
//			
//			int average = 0;
//			
//			for(int i = 0; i < ITERATIONS; i++) {
//				final var letters = mediator.computeLetters();
//				System.out.println("Generated Letters: ");
//				letters.get().forEach(System.out::println);
//				
//				final var words = validator.validateForTest(letters.get());
//				System.out.println();
//				average += words.size();
//				System.out.println(String.format("Words can be found [%d]: ", words.size()));
//				words.forEach(System.out::println);
//				System.out.println();
//			}
//			
//			System.out.println("Average: " + average/ITERATIONS);
//		
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
}
