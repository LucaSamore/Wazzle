package wazzle.controller.common.files;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class FileOperationImpl<T extends Serializable> implements FileOperation<T> {
	
	//TODO: Add javadoc

	private final Operation operation;
	private final String fileName;
	private final List<T> items;
	private final String separator = System.getProperty("file.separator");
	private final String directory = "src" + this.separator + 
			"main" + this.separator + 
			"res" + this.separator + 
			"files";
	
	public FileOperationImpl(final String fileName, final Operation operation, final List<T> items) {
		this.fileName = fileName;
		this.operation = operation;
		this.items = items;
	}
	
	@Override
	public List<T> getItems() {
		return Collections.unmodifiableList(this.items);
	}

	@Override
	public String getPath() {
		return this.directory + this.separator + this.fileName;
	}
	
	@Override
	public Operation getOperation() {
		return this.operation;
	}
	
	@Override
	public String getFileName() {
		return this.fileName;
	}

	public String getSeparator() {
		return this.separator;
	}

	public String getDirectory() {
		return this.directory;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.directory, this.fileName, this.items, this.operation, this.separator);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileOperationImpl<?> other = (FileOperationImpl<?>) obj;
		return Objects.equals(directory, other.directory) && Objects.equals(fileName, other.fileName)
				&& Objects.equals(items, other.items) && operation == other.operation
				&& Objects.equals(separator, other.separator);
	}
	
	@Override
	public String toString() {
		return String.format("operation=%s fileName=%s separator=%s directory=%s", this.operation, this.fileName, this.separator, this.directory);
	}
}
