package wazzle.view;

/**
 * This enum groups all the images and gifs used in Wazzle
 * and provides a method for obtaining them.
 */
public enum Images {
	WAZZLE_ICON("img/wally.png"),
	
	WALLY("img/wally.gif"),
	
	SETTINGS_ICON("img/settingsIcon.png"),
	
	TUTORIAL_MAIN_GAME("img/gifTutorial.gif"),
	
	TUTORIAL_MINI_GAME("img/gifTutorialMiniGame.gif");
	
	private final String path;
	
	/**
	 * Construct a new Images object.
	 * @param fileName a {@code String} representing the name of the file.
	 */
	Images(final String path) {
		this.path = path;
	}
	
	/**
	 * Returns the path of the file.
	 * @return a {@code String} representing the path of the file.
	 */
	public String getPath() {
		return this.path;
	}
}
