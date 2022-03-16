package wazzle.controller.common;

import java.util.Objects;

public final class FileOperationImpl implements FileOperation {
	
	//TODO: Add javadoc

	private final Operation operation;
	private final String fileName;
	private final String separator = System.getProperty("file.separator");
	private final String directory = "src" + this.separator + 
			"main" + this.separator + 
			"res" + this.separator + 
			"files";
	
	public FileOperationImpl(final String fileName, final Operation operation) {
		this.fileName = fileName;
		this.operation = operation;
	}
	
	@Override
	public String getPath() {
		return this.directory + this.separator + this.fileName;
	}
	
	public Operation getOperation() {
		return this.operation;
	}
	
	public String getfileName() {
		return this.fileName;
	}

	public String getSeparator() {
		return separator;
	}

	public String getDirectory() {
		return directory;
	}

	@Override
	public int hashCode() {
		return Objects.hash(directory, fileName, operation, separator);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileOperationImpl other = (FileOperationImpl) obj;
		return Objects.equals(directory, other.directory) && Objects.equals(fileName, other.fileName)
				&& operation == other.operation && Objects.equals(separator, other.separator);
	}
	
	@Override
	public String toString() {
		return String.format("operation=%s fileName=%s separator=%s directory=%s", this.operation, this.fileName, this.separator, this.directory);
	}
}
