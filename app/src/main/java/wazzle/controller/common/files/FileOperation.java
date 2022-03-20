package wazzle.controller.common.files;

import java.io.Serializable;
import java.util.List;

public interface FileOperation<T extends Serializable> {

	//TODO: Add javadoc
	
	enum Operation {
		READ,
		WRITE,
		APPEND
	}

	List<T> getItems();
	Operation getOperation();
	String getFileName();
	String getPath();
}
