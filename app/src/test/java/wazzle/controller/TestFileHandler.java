package wazzle.controller;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import wazzle.controller.common.files.ConcreteFileHandler;
import wazzle.controller.common.files.FileOperation;
import wazzle.controller.common.files.FileOperation.Operation;
import wazzle.controller.common.files.FileOperationImpl;

public class TestFileHandler {
	
	private static final String TEST_FILE = "test.txt";
	private static final String EMPTY_FILE = "empty.txt";
	private static final String JSON_TEST_FILE = "jsonTest.json";
	
	//the following inner class is meant for serialization/deserialization test only!
	private final class Fruit implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private String name;
		private int quantity;
		//private LocalDateTime date;
		
		public Fruit(final String name, final int quantity) {
			this.name = name;
			this.quantity = quantity;
			//this.date = LocalDateTime.now();
		}
		
		public String getName() {
			return this.name;
		}
		
		@Override
		public String toString() {
			return this.name + " " + this.quantity + " ";
		}
	}
	
	private Fruit fruit1 = new Fruit("strawberry", 10);
	private Fruit fruit2 = new Fruit("banana", 5);
	private Fruit fruit3 = new Fruit("peach", 22);
	
	private FileOperation<String> readOperation1 = new FileOperationImpl<>(
			EMPTY_FILE, 
			Operation.READ, 
			Optional.empty()
	);
	
	private FileOperation<String> readOperation2 = new FileOperationImpl<>(
			TEST_FILE, 
			Operation.READ, 
			Optional.empty()
	);
	
	private FileOperation<String> readOperation3 = new FileOperationImpl<>(
			"I do not exist.txt", 
			Operation.READ, 
			Optional.empty()
	);
	
	private FileOperation<String> writeOperation = new FileOperationImpl<>(
			EMPTY_FILE, 
			Operation.WRITE, 
			Optional.of(List.of("This", "is", "a", "test"))
	);
	
	private FileOperation<String> appendOperation = new FileOperationImpl<>(
			TEST_FILE, 
			Operation.APPEND, 
			Optional.of(List.of("Added", "content"))
	);
	
	private FileOperation<String> clearOperation = new FileOperationImpl<>(
			EMPTY_FILE, 
			Operation.CLEAR,
			Optional.empty()
	);
	
	private FileOperation<TestFileHandler.Fruit> readJsonOperation1 = new FileOperationImpl<>(
			JSON_TEST_FILE, 
			Operation.READ,
			Optional.of(new ArrayList<>())
	);
	
	private FileOperation<TestFileHandler.Fruit> readJsonOperation2 = new FileOperationImpl<>(
			"I am a ghost.json", 
			Operation.READ,
			Optional.of(new ArrayList<>())
	);
	
	private FileOperation<TestFileHandler.Fruit> writeJsonOperation = new FileOperationImpl<>(
			JSON_TEST_FILE, 
			Operation.WRITE,
			Optional.of(List.of(fruit1, fruit2, fruit3))
	);
//	
//	private FileOperation<TestFileHandler.Fruit> appendJsonOperation = new FileOperationImpl<>(
//			JSON_TEST_FILE, 
//			Operation.APPEND,
//			Optional.of(List.of(fruit3))
//	);
//	
	private FileOperation<TestFileHandler.Fruit> clearJsonOperation = new FileOperationImpl<>(
			JSON_TEST_FILE, 
			Operation.CLEAR,
			Optional.empty()
	);
	
	private ConcreteFileHandler handler = new ConcreteFileHandler();

//	@Test
//	public void testTextFile() {
//		try {
//			this.handler.handle(readOperation1);
//			assertEquals(Collections.emptyList(), this.handler.getItemsFromFile()); // read an empty .txt file
//			
//			this.handler.handle(readOperation2);
//			assertTrue(this.handler.getItemsFromFile().size() > 0); // read a .txt file with some lines
//			
//			this.handler.handle(writeOperation);
//			assertTrue(Files.size(Path.of(writeOperation.getPath())) > 0); // write some lines to the empty .txt file
//			
//			this.handler.handle(clearOperation);
//			assertTrue(Files.size(Path.of(clearOperation.getPath())) == 0); // clear the empty.txt file previously written
//			
//			long oldSize = Files.size(Path.of(appendOperation.getPath()));
//			this.handler.handle(appendOperation);
//			assertTrue(Files.size(Path.of(appendOperation.getPath())) > oldSize); // add a few lines to a non-empty .txt file (size must change)
//			
//			this.handler.handle(readOperation3);
//			assertThrows(IOException.class, () -> this.handler.handle(readOperation3)); // try to read a file that does not exist
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//			//System.err.println("Exception thrown successfully");
//		}
//	}
	
	@Test
	public void testJsonFile() {
		try {
			
			//System.out.println(fruit1 + System.lineSeparator() + fruit2 + System.lineSeparator() + fruit3);
			
			this.handler.handle(writeJsonOperation);
			assertFalse(Files.size(Path.of(writeJsonOperation.getPath())) == 0); // serialize some dumb objects
			
			this.handler.handle(readJsonOperation1);
			assertTrue(this.handler.getItemsFromFile().size() > 0); // deserialize previous objects
			System.out.println(this.handler.getItemsFromFile().getClass());
			
//			long oldSize = Files.size(Path.of(JSON_TEST_FILE));
//			this.handler.handle(appendJsonOperation); // append a new dumb object
//			assertTrue(Files.size(Path.of(TEST_FILE)) > oldSize);
//			
//			this.handler.handle(clearJsonOperation); // clear json file (size must be zero)
//			assertTrue(Files.size(Path.of(JSON_TEST_FILE)) == 0);
			
//			this.handler.handle(readJsonOperation2);
//			assertThrows(IOException.class, () -> this.handler.handle(readJsonOperation2)); // try to deserialize a json file that does not exist
			
		} catch (IOException e) {
			e.printStackTrace();
			//System.err.println("Exception thrown successfully");
		}
	}

}
