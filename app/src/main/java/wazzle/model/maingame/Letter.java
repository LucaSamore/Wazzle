package wazzle.model.maingame;

import java.util.List;

import javafx.util.Pair;

public interface Letter {
	
	Character getContent();
	
	Pair<Integer, Integer> getPosition();
	
}
