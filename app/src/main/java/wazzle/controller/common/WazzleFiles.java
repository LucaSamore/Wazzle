package wazzle.controller.common;

public enum WazzleFiles {
	HISTORY("history.json"),
	BONUSES("bonuses.json"),
	SETTINGS("settings.json");
	
	private final String fileName;

	WazzleFiles(final String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return this.fileName;
	}
}
