package wazzle.model.maingame;

import java.util.EnumMap;
import java.util.List;
import java.util.Set;

import javafx.util.Pair;

public final class Mediator {
	
	/*
	 * TODO: AlphabetClassifier, LetterChooser and LetterAllocator shall have a Mediator instance variable,
	 * passed via constructor.
	 * Public methods of these classes shall return void and the result will be passed to the Mediator through
	 * the notifyFromXYZ() methods. All partial results will be handled in this class.
	 * This class is meant to be an interface (sort of a Facade) for GridGenerator, because I want to separate 
	 * the process of getting N Letters from evaluating the grid quality (via the GridValidator object in GridGenerator)  
	*/
	
	public void notifyFromClassifier(final EnumMap<Range,WeightedAlphabet> classifiedLetters) {
		
	}
	
	public void notifyFromChooser(final EnumMap<Range,List<Pair<Character,Double>>> chosenLetters) {
		
	}
	
	public void notifyFromAllocator(final Set<Letter> letters) {
		
	}
}
