package wazzle.controller.maingame;

import java.util.List;

import wazzle.controller.common.FileController;
import wazzle.model.maingame.MainGameImpl;

public interface WazzleController {

	Settings getSettings();
	
	FileController getFileController();
	
	List<MainGameImpl> getGameHistory();
	
	void gainBonus();
}
