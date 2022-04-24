package wazzle.controller.common;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * This enum groups all files used in Wazzle
 * and provides a method for obtaining them.
 */
public enum WazzleFiles {
	HISTORY("history.json"),
	
	BONUSES("bonuses.json"),
	
	SETTINGS("settings.json"),
	
	MINI_GAME("mini-game.json"),
	
	SHORT_DATASET("files/short-dataset.txt"),
	
	DATASET("files/dataset.txt"),
	
	ALL_SETTINGS("files/all-settings.json");
	
	private static final String SEPARATOR = System.getProperty("file.separator");
	private static final String DIRECTORY = 
			System.getProperty("user.home") + SEPARATOR + 
			"wazzle" + SEPARATOR + 
			"files" + SEPARATOR;
	
	private final String fileName;

	/**
	 * Construct a new WazzleFiles object.
	 * @param fileName a {@code String} representing the name of the file.
	 */
	WazzleFiles(final String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * Returns the name of the file.
	 * @return a {@code String} representing the name of the file.
	 */
	public String getFileName() {
		return this.fileName;
	}
	
	/**
	 * Returns the file structure
	 * @return a {@code String} representing the folder structure.
	 */
	public static String getFoldersStructure() {
		return DIRECTORY;
	}
	
	/**
	 * Given the file name, returns the full path
	 * @param fileName a {@code String} representing the name of the file
	 * @return a {@code String} representing the full path.
	 */
	public static String getFullPathByName(@NonNull final String fileName) {
		return DIRECTORY + fileName;
	}
}
