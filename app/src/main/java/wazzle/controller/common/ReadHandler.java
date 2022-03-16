package wazzle.controller.common;

public class ReadHandler extends FileHandler{

	//TODO: Add javadoc
	
	private FileOperation operation;
	
	public ReadHandler(FileOperation operation) {
		this.operation = operation;
	}
	
	@Override
	public boolean handle(FileOperation operation) {
		// TODO Implement this method
		return handleNext(operation);
	}

}
