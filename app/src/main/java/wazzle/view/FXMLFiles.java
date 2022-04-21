package wazzle.view;

public enum FXMLFiles {
	MAIN_MENU("layouts/mainMenu.fxml"),
	
	SETTINGS("layouts/SettingPage.fxml"),
	
	MAIN_GAME("layouts/MainGame.fxml"),
	
	MINI_GAME("layouts/MiniGame.fxml"),
	
	LOADING_SCREEN("layouts/LoadingScreen.fxml"),
	
	MAIN_GAME_STATS("layouts/statisticsMainGame.fxml"),
	
	MINI_GAME_STATS("layouts/statisticsMiniGame.fxml"),
	
	TUTORIAL("layouts/tutorial.fxml"),
	
	HISTORY("layouts/history.fxml");
	
	private final String path;
	
	FXMLFiles(final String path) {
		this.path = path;
	}
	
	public String getPath() {
		return this.path;
	}
	
}
