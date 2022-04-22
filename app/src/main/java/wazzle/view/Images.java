package wazzle.view;

public enum Images {
	WAZZLE_ICON("img/wazzle-icon.jpeg"),
	
	WALLY("img/wally.gif"),
	
	SETTINGS_ICON("img/settingsIcon.png"),
	
	TUTORIAL_MAIN_GAME("img/gifTutorial.gif");
	
	private final String path;
	
	Images(final String path) {
		this.path = path;
	}
	
	public String getPath() {
		return this.path;
	}
}
