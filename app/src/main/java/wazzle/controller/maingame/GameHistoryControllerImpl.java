package wazzle.controller.maingame;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.MainGameImpl;

/**
 * This class is a concrete implementation for {@link GameHistoryController}
 * It provides methods for manage the {@link MainGame} history.
 */
public final class GameHistoryControllerImpl implements GameHistoryController {
	
	private final List<MainGameImpl> gameHistory;
	
	/**
	 * Construct a new {@link GameHistoryController}.
	 * @param gameHistory 	The starting game history.
	 */
	public GameHistoryControllerImpl(List<MainGameImpl> gameHistory) {
		this.gameHistory = gameHistory;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MainGameImpl> getGameHistory() {
		return List.copyOf(this.gameHistory);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MainGame getBestGame() {
		return this.gameHistory.stream().filter(mg -> mg.getCurrentScore() == this.gameHistory
																				  .stream()
																				  .map(g -> g.getCurrentScore())
																				  .max(Comparator.comparing(Integer::valueOf))
																				  .orElse(0))
								 		.findFirst()
								 		.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sortGameHistoryByData() {
		Collections.sort(this.gameHistory, (v1, v2) -> v2.getDateTime().compareTo(v1.getDateTime()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MainGameImpl> getSortedByScoreGameHistory() {
		return this.gameHistory.stream().sorted(Comparator.comparingDouble(MainGame::getCurrentScore)).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addNewGame(MainGameImpl maingame) {
		this.gameHistory.add(maingame);
	}

}
