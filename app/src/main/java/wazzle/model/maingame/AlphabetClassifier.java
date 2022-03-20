package wazzle.model.maingame;

import java.util.EnumMap;
import java.util.Set;

public interface AlphabetClassifier {
	
	public EnumMap<Range, WeightedAlphabet> classify();

}
