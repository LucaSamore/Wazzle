package wazzle.controller.common;

public interface FileOperation {

	//TODO: Add javadoc
	
	enum Operation {
		READ,
		WRITE,
		APPEND
	}
	
	String getPath();
}
