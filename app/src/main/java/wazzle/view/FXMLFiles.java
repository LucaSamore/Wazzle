package wazzle.view;

/**
 * This enum groups all the FXML files used in Wazzle
 * and provides a method for obtaining them.
 */
public enum FXMLFiles {
	MAIN_MENU("layouts/mainMenu.fxml"),
	
	SETTINGS("layouts/SettingPage.fxml"),
	
	MAIN_GAME("layouts/MainGame.fxml"),
	
	MINI_GAME("layouts/MiniGame.fxml"),
	
	LOADING_SCREEN("layouts/LoadingScreen.fxml"),
	
	MAIN_GAME_STATS("layouts/statisticsMainGame.fxml"),
	
	MINI_GAME_STATS("layouts/statisticsMiniGame.fxml"),
	
	TUTORIAL_MAIN_GAME("layouts/TutorialMainGame.fxml"),
	
	TUTORIAL_MINI_GAME("layouts/TutorialMiniGame.fxml"),
	
	HISTORY("layouts/history.fxml");
	
	private final String path;
	
	/**
	 * Construct a new FXMLFiles object.
	 * @param path a {@code String} representing the path of the FXML file.
	 */
	FXMLFiles(final String path) {
		this.path = path;
	}
	
	/**
	 * Returns the path of the file.
	 * @return a {@code String} representing the path of the FXML file.
	 */
	public String getPath() {
		return this.path;
	}
}
