package wazzle.model.minigame;

import java.util.List;

import javafx.util.Pair;

public interface MiniGameWord {

	public List<Pair<Character, Result>> getCompositeWord();

	public void setCompositeWord(List<Pair<Character, Result>> compositeWord);

	public boolean isCorrect();

	public void setCorrect();
	
	public void setWrong();

}
