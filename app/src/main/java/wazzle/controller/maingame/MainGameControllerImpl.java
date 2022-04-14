package wazzle.controller.maingame;

import java.util.Optional;
import java.util.Set;

import javafx.util.Pair;
import wazzle.controller.common.WazzleController;
import wazzle.model.common.Dictionary;
import wazzle.model.maingame.Difficulty;
import wazzle.model.maingame.MainGame;

public final class MainGameControllerImpl implements MainGameController {
	private static final int ATTEMPTS_BEFORE_HELP = 5;
	private final WazzleController mainController;
	private Optional<MainGame> game;
	
	public MainGameControllerImpl(final WazzleController mainController) {
		this.mainController = mainController.getThis();
		this.game = Optional.empty();
	}
	
	@Override
	public Optional<MainGame> getGame() {
		return this.game;
	}

	@Override
	public void startNewGame(final Dictionary dataset, final Pair<Integer,Integer> gridShape, final Difficulty difficulty) {
		if(this.game.isEmpty()) {
			this.game = Optional.of(this.mainController.getFacade().startNewMainGame(dataset, gridShape, difficulty));
		}
	}

	@Override
	public void endGame() {
		this.game.ifPresent(g -> {
			this.saveGame();
			this.game = Optional.empty();
		});
	}

	@Override
	public boolean attempt(final String word) {
		if(this.game.isPresent()) {
			return this.game.get().tryWord(word);
		}
		return false;
	}
	
	@Override
	public boolean needHelp() {
		return this.game.get().getFailedAttempts() == ATTEMPTS_BEFORE_HELP;
	}
	
	@Override
	public boolean areWeDone() {
		return this.game.get().wordsFound().size() == this.game.get().wordsCanBeFound().size();
	}

	@Override
	public void useScoreBonus() {
		this.game.get().setCurrentScore(this.mainController
				.getBonusManager()
				.applyScoreBonus(this.game.get().getCurrentScore(), this.game.get().getGrid().getTotalScore()));
	}

	@Override
	public void useTimeBonus() {
		// TODO Auto-generated method stub
	}

	@Override
	public Set<String> useWordBonus() {
		return this.mainController
				.getBonusManager()
				.applyWordBonus(this.game.get().wordsToBeFound());
	}
	
	private void saveGame() {
		this.mainController.addMainGametoHistory(this.game.get());
	}

	@Override
	public WazzleController getMainController() {
		return this.mainController;
	}
}
