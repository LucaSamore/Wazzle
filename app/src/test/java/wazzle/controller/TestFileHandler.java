package wazzle.controller;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

import wazzle.controller.common.files.ConcreteFileHandler;
import wazzle.controller.common.files.FileOperation;
import wazzle.controller.common.files.FileOperation.Operation;
import wazzle.controller.common.files.FileOperationImpl;

public class TestFileHandler {
	
	private FileOperation<String> op = new FileOperationImpl<>(
			"test.txt", 
			Operation.READ, 
			new ArrayList<String>()
	);
	
	private ConcreteFileHandler handler = new ConcreteFileHandler();

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
