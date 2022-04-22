package wazzle.model.common;

import javafx.util.Pair;
import wazzle.model.maingame.Difficulty;
import wazzle.model.maingame.Grid;
import wazzle.model.maingame.MainGame;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.WordsDispenser;

public interface AbstractGameFactory {
	MainGame createMainGame(final Dictionary dataset, final Pair<Integer,Integer> gridShape, final Difficulty difficulty);
	MiniGame createMiniGame(final WordsDispenser wordsDispenser);
}
