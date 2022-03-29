package wazzle.model.maingame;

import java.util.Set;

@FunctionalInterface
public interface Filters {
	boolean applyAll(final String word, final Set<Letter> letters);
	
	//TODO: implement this method
	static boolean checkLetters(final String word, final Set<Letter> letters) {
		return true;
	}
	
	//TODO: implement this method
	static boolean checkPath(final String word, final Set<Letter> letters) {
		return true;
	}
}
