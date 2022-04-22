package wazzle.controller.common;

import org.checkerframework.checker.nullness.qual.NonNull;

public enum WazzleFiles {
	HISTORY("history.json"),
	
	BONUSES("bonuses.json"),
	
	SETTINGS("settings.json"),
	
	MINI_GAME("mini-game.json"),
	
	SHORT_DATASET("files/short-dataset.txt"),
	
	DATASET("files/medium-dataset.txt"),
	
	ALL_SETTINGS("files/all-settings.json");
	
	private static final String SEPARATOR = System.getProperty("file.separator");
	private static final String DIRECTORY = 
			System.getProperty("user.home") + SEPARATOR + 
			"wazzle" + SEPARATOR + 
			"files" + SEPARATOR;
	
	private final String fileName;

	WazzleFiles(final String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public static String getFoldersStructure() {
		return DIRECTORY;
	}
	
	public static String getFullPathByName(@NonNull final String fileName) {
		return DIRECTORY + fileName;
	}
}
