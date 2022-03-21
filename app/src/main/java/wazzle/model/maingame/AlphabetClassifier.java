package wazzle.model.maingame;

import java.util.EnumMap;

public interface AlphabetClassifier {
	
	public EnumMap<Range, WeightedAlphabet> classify();

}
