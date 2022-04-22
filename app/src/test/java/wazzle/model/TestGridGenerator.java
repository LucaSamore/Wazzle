//package wazzle.model;
//
//import static org.junit.Assert.*;
//
//import org.junit.Test;
//
//import javafx.util.Pair;
//import wazzle.model.common.Dictionary;
//import wazzle.model.common.DictionaryImpl;
//import wazzle.model.maingame.Difficulty;
//import wazzle.model.maingame.GridGenerator;
//import wazzle.model.maingame.GridGeneratorImpl;
//
//public class TestGridGenerator {
//
//	// These tests are used to see how much time it takes to generate a grid of a certain difficulty
//	
//	private static final Pair<Integer,Integer> SHAPE_4x4 = new Pair<>(4,4);
//	private static final Pair<Integer,Integer> SHAPE_5x5 = new Pair<>(5,5);
//	private static final Pair<Integer,Integer> SHAPE_6x6 = new Pair<>(6,6);
//	
//	private final Difficulty easy4x4 = new Difficulty(76, 200, 240000L);
//	private final Difficulty hard4x4 = new Difficulty(25, 75, 180000L);
//	
//	private final Difficulty easy5x5 = new Difficulty(151, 250, 240000L);
//	private final Difficulty hard5x5 = new Difficulty(100, 150, 180000L);
//	
//	private final Difficulty easy6x6 = new Difficulty(226, 350, 240000L);
//	private final Difficulty hard6x6 = new Difficulty(150, 225, 180000L);
//	
//	@Test
//	public void testGeneratorEasy4x4() {
//		try {
//			final Dictionary dataset = new DictionaryImpl(TestReader.readDataset("medium-dataset.txt"));
//			final GridGenerator generatorEasy = new GridGeneratorImpl(dataset, SHAPE_4x4, this.easy4x4);
//			
//			var grid = generatorEasy.generate();
//			System.out.println(grid);
//		
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testGeneratorHard4x4() {		
//		try {
//			final Dictionary dataset = new DictionaryImpl(TestReader.readDataset("medium-dataset.txt"));
//			final GridGenerator generatorHard = new GridGeneratorImpl(dataset, SHAPE_4x4, this.hard4x4);
//			
//			var grid = generatorHard.generate();
//			
//			System.out.println(grid);
//		
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testGeneratorEasy5x5() {
//		try {
//			final Dictionary dataset = new DictionaryImpl(TestReader.readDataset("medium-dataset.txt"));
//			final GridGenerator generatorEasy = new GridGeneratorImpl(dataset, SHAPE_5x5, this.easy5x5);
//			
//			var grid = generatorEasy.generate();
//			System.out.println(grid);
//		
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testGeneratorHard5x5() {		
//		try {
//			final Dictionary dataset = new DictionaryImpl(TestReader.readDataset("medium-dataset.txt"));
//			final GridGenerator generatorHard = new GridGeneratorImpl(dataset, SHAPE_5x5, this.hard5x5);
//			
//			var grid = generatorHard.generate();
//
//			System.out.println(grid);
//		
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testGeneratorEasy6x6() {
//		try {
//			final Dictionary dataset = new DictionaryImpl(TestReader.readDataset("medium-dataset.txt"));
//			final GridGenerator generatorEasy = new GridGeneratorImpl(dataset, SHAPE_6x6, this.easy6x6);
//			
//			var grid = generatorEasy.generate();
//			
//			System.out.println(grid);
//		
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testGeneratorHard6x6() {
//		try {
//			final Dictionary dataset = new DictionaryImpl(TestReader.readDataset("medium-dataset.txt"));
//			final GridGenerator generatorHard = new GridGeneratorImpl(dataset, SHAPE_6x6, this.hard6x6);
//			
//			var grid = generatorHard.generate();
//			
//			System.out.println(grid);
//		
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
