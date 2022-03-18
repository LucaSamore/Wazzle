package wazzle.model.maingame;

import java.util.EnumMap;
import java.util.List;

import javafx.util.Pair;

public interface LetterChooser {
	
	public EnumMap<Range, List<Pair<Character, Double>>> choose();

}
