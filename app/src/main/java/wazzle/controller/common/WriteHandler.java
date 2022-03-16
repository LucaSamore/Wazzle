package wazzle.controller.common;

public class WriteHandler extends FileHandler{

	//TODO: Add javadoc
	
	private FileOperation operation;
	
	public WriteHandler(FileOperation operation) {
		this.operation = operation;
	}
	
	@Override
	public boolean handle(FileOperation operation) {
		// TODO Implement this method
		return handleNext(operation);
	}

}
