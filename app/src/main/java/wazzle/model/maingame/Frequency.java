package wazzle.model.maingame;

import java.util.Map;

public interface Frequency {
	Map<Character, Long> getFrequency();
	Long getTotalLetters();
}
