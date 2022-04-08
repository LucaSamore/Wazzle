package wazzle.controller.maingame;

//import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import wazzle.model.maingame.MainGame;

public class GameHistoryControllerImpl implements GameHistoryController {
	
	List<MainGame> gameHistory;
	
//	public ControllerGameHistory(Facade mainController) {
//		this.gameHistory = mainController.getGameHistory();
//	}

	@Override
	public List<MainGame> getGameHistory() {
		return this.gameHistory;
	}

	@Override
	public MainGame getBestGame() {
		return this.gameHistory.stream().filter(mg -> mg.getCurrentScore() == this.gameHistory
																				  .stream()
																				  .map(g -> g.getCurrentScore())
																				  .max(Comparator.comparing(Double::valueOf))
																				  .orElse(Double.NaN))
								 		.findFirst()
								 		.get();
	}

	@Override
	public List<MainGame> getSortedByDataGameHistory() {
		List<MainGame> sortedGameHistory = this.gameHistory;
		//Collections.sort(sortedGameHistory, (v1, v2) -> v1.getDateTime().compareTo(v2.getDateTime()));
		return sortedGameHistory;
	}

	@Override
	public List<MainGame> getSortedByScoreGameHistory() {
		return this.gameHistory.stream().sorted(Comparator.comparingDouble(MainGame::getCurrentScore)).collect(Collectors.toList());
	}

}
