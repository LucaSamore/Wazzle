package wazzle.controller.maingame;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javafx.util.Pair;
import wazzle.controller.common.WazzleController;
import wazzle.model.common.Dictionary;
import wazzle.model.maingame.Difficulty;
import wazzle.model.maingame.MainGame;

public final class MainGameControllerImpl implements MainGameController {
	private static final int ATTEMPTS_BEFORE_HELP = 5;
	private final WazzleController mainController;
	private Optional<MainGame> game;
	private long timeRemaining;
	private TimerTask timer;
	
	public MainGameControllerImpl(final WazzleController mainController) {
		this.mainController = mainController.getThis();
		this.game = Optional.empty();
		this.timeRemaining = (int) mainController.getSettings().getCurrentDifficulty().getTimeInMilliseconds() / 1000;
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
		this.timeRemaining = this.mainController.getBonusManager().applyTimeBonus(this.timeRemaining);
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

	@Override
	public String longestWord() {
		return this.game.get()
				.wordsFound()
				.stream()
				.max(Comparator.comparingInt(String::length))
				.orElse("");
	}

	@Override
	public String highestScoreWord() {		
        double max = this.game.get()
                .wordsFound()
                .stream()
                .map(w -> this.game.get().getScoreFromWord(w))
                .max(Comparator.comparingDouble(Double::doubleValue))
                .orElse(Double.NaN);

        return this.game.get()
                .wordsFound()
                .stream()
                .filter(w -> max == this.game.get().getScoreFromWord(w))
                .findFirst()
                .get();
	}
	
	@Override
	public void startTimer() {
		this.timer = new TimerTask() {
			@Override
			public void run() {
				timeRemaining--;
				
				if(timeRemaining <= 0) {
					this.cancel();
					return;
				}
			}
		};
		new Timer().scheduleAtFixedRate(this.timer, 1000L, 1000L);
	}
	
	@Override
	public void stopTimer() {
		this.timer.cancel();
	}

	@Override
	public long getRemainingTime() {
		return this.timeRemaining;
	}
}
