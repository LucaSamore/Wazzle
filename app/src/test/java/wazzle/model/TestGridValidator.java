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
	
	//The purpose of these tests is to see the set of words (and how many of them) I can build with a certain number (gridShape) of generated letters
	
	private static final int ITERATIONS = 1;
	
	private static final Pair<Integer,Integer> SHAPE_4x4 = new Pair<>(4,4);
	private static final Pair<Integer,Integer> SHAPE_5x5 = new Pair<>(5,5);
	private static final Pair<Integer,Integer> SHAPE_6x6 = new Pair<>(6,6);
	
	private final Difficulty easy4x4 = new Difficulty(126, 250, 240000L);
	private final Difficulty hard4x4 = new Difficulty(50, 125, 180000L);
	
	private final Difficulty easy5x5 = new Difficulty(201, 325, 240000L);
	private final Difficulty hard5x5 = new Difficulty(125, 200, 180000L);
	
	private final Difficulty easy6x6 = new Difficulty(351, 500, 240000L);
	private final Difficulty hard6x6 = new Difficulty(250, 350, 180000L);
	
	@Test
	public void testValidationOutputEasy4x4() {
		try {
			final Dictionary dataset = new DictionaryImpl(TestReader.readDataset("datasetNuovo.txt"));
			final Mediator mediator = new Mediator(dataset, SHAPE_4x4);
			final GridValidator validator = new GridValidatorImpl(dataset, this.easy4x4);
			
			int average = 0;
			
			for(int i = 0; i < ITERATIONS; i++) {
				final var letters = mediator.computeLetters();
				System.out.println("Generated Letters: ");
				letters.get().forEach(System.out::println);
				
				final var words = validator.validateForTest(letters.get());
				System.out.println();
				average += words.size();
				System.out.println(String.format("Words can be found [%d]: ", words.size()));
				words.forEach(System.out::println);
				System.out.println();
			}
			
			System.out.println("Average: " + average/ITERATIONS + System.lineSeparator());
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidationOutputHard4x4() {
		try {
			final Dictionary dataset = new DictionaryImpl(TestReader.readDataset("datasetNuovo.txt"));
			final Mediator mediator = new Mediator(dataset, SHAPE_4x4);
			final GridValidator validator = new GridValidatorImpl(dataset, this.hard4x4);
			
			int average = 0;
			
			for(int i = 0; i < ITERATIONS; i++) {
				final var letters = mediator.computeLetters();
				System.out.println("Generated Letters: ");
				letters.get().forEach(System.out::println);
				
				final var words = validator.validateForTest(letters.get());
				System.out.println();
				average += words.size();
				System.out.println(String.format("Words can be found [%d]: ", words.size()));
				words.forEach(System.out::println);
				System.out.println();
			}
			
			System.out.println("Average: " + average/ITERATIONS + System.lineSeparator());
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidationOutputEasy5x5() {
		try {
			final Dictionary dataset = new DictionaryImpl(TestReader.readDataset("datasetNuovo.txt"));
			final Mediator mediator = new Mediator(dataset, SHAPE_5x5);
			final GridValidator validator = new GridValidatorImpl(dataset, this.easy5x5);
			
			int average = 0;
			
			for(int i = 0; i < ITERATIONS; i++) {
				final var letters = mediator.computeLetters();
				System.out.println("Generated Letters: ");
				letters.get().forEach(System.out::println);
				
				final var words = validator.validateForTest(letters.get());
				System.out.println();
				average += words.size();
				System.out.println(String.format("Words can be found [%d]: ", words.size()));
				words.forEach(System.out::println);
				System.out.println();
			}
			
			System.out.println("Average: " + average/ITERATIONS + System.lineSeparator());
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidationOutputHard5x5() {
		try {
			final Dictionary dataset = new DictionaryImpl(TestReader.readDataset("datasetNuovo.txt"));
			final Mediator mediator = new Mediator(dataset, SHAPE_5x5);
			final GridValidator validator = new GridValidatorImpl(dataset, this.hard5x5);
			
			int average = 0;
			
			for(int i = 0; i < ITERATIONS; i++) {
				final var letters = mediator.computeLetters();
				System.out.println("Generated Letters: ");
				letters.get().forEach(System.out::println);
				
				final var words = validator.validateForTest(letters.get());
				System.out.println();
				average += words.size();
				System.out.println(String.format("Words can be found [%d]: ", words.size()));
				words.forEach(System.out::println);
				System.out.println();
			}
			
			System.out.println("Average: " + average/ITERATIONS + System.lineSeparator());
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidationOutputEasy6x6() {
		try {
			final Dictionary dataset = new DictionaryImpl(TestReader.readDataset("datasetNuovo.txt"));
			final Mediator mediator = new Mediator(dataset, SHAPE_6x6);
			final GridValidator validator = new GridValidatorImpl(dataset, this.easy6x6);
			
			int average = 0;
			
			for(int i = 0; i < ITERATIONS; i++) {
				final var letters = mediator.computeLetters();
				System.out.println("Generated Letters: ");
				letters.get().forEach(System.out::println);
				
				final var words = validator.validateForTest(letters.get());
				System.out.println();
				average += words.size();
				System.out.println(String.format("Words can be found [%d]: ", words.size()));
				words.forEach(System.out::println);
				System.out.println();
			}
			
			System.out.println("Average: " + average/ITERATIONS + System.lineSeparator());
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidationOutputHard6x6() {
		try {
			final Dictionary dataset = new DictionaryImpl(TestReader.readDataset("datasetNuovo.txt"));
			final Mediator mediator = new Mediator(dataset, SHAPE_6x6);
			final GridValidator validator = new GridValidatorImpl(dataset, this.hard6x6);
			
			int average = 0;
			
			for(int i = 0; i < ITERATIONS; i++) {
				final var letters = mediator.computeLetters();
				System.out.println("Generated Letters: ");
				letters.get().forEach(System.out::println);
				
				final var words = validator.validateForTest(letters.get());
				System.out.println();
				average += words.size();
				System.out.println(String.format("Words can be found [%d]: ", words.size()));
				words.forEach(System.out::println);
				System.out.println();
			}
			
			System.out.println("Average: " + average/ITERATIONS + System.lineSeparator());
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
