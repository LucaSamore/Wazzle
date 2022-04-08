package wazzle.controller.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.util.Pair;
import wazzle.model.common.AbstractBonusFactory;
import wazzle.model.common.AbstractGameFactory;
import wazzle.model.common.BonusFactoryImpl;
import wazzle.model.common.Dictionary;
import wazzle.model.common.GameFactoryImpl;
import wazzle.model.maingame.Difficulty;
import wazzle.model.maingame.MainGame;

public final class Facade {
	private final AbstractGameFactory gameFactory;
	private final AbstractBonusFactory bonusFactory;
	//private final Settings settings;
	private final List<MainGame> history;
	
	//TODO: Add file handling

	public Facade() {
		this.gameFactory = new GameFactoryImpl();
		this.bonusFactory = new BonusFactoryImpl();
		this.history = new ArrayList<>(); //TODO: deserialize from json file
		//this.settings = new SettingsImpl();
	}

	public MainGame startNewMainGame(final Dictionary dataset, final Pair<Integer,Integer> gridShape, final Difficulty difficulty) {
		return this.gameFactory.createMainGame(dataset, gridShape, difficulty);
	}
	
//	public MiniGame startNewMiniGame() {
//		
//	}
	
//	public Settings getSettings() {
//		return this.settings;
//	}
	
	public List<MainGame> getHistory() {
		return List.copyOf(this.history);
	}
	
	public void addMainGame(final MainGame game) {
		this.history.add(game);
	}
}
