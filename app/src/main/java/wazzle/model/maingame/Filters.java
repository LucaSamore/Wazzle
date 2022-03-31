package wazzle.model.maingame;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.util.Pair;

@FunctionalInterface
public interface Filters {
	
	boolean applyAll(final String word, final Set<Letter> letters);
	
	static boolean checkLetters(final String word, final Set<Letter> letters) {
		return word.chars()
				.mapToObj(c -> (char) c)
				.filter(c -> letters.stream()
						.map(Letter::getContent)
						.collect(Collectors.toList()).contains(c))
				.collect(Collectors.toList())
				.size() == word.length();
	}
	
	//TODO: implement this method
	static boolean checkPath(final String word, final Set<Letter> letters) {
		return exist(letters.stream()
                .collect(Collectors.toMap(l -> l.getPosition(), l -> l.getContent())), word);
	}
	
	private static boolean exist(Map<Pair<Integer, Integer>, Character> board, String word) {
		
		//System.out.println("Board: " + board + System.lineSeparator());
		//visited = new HashMap<>();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
            	//System.out.println("Board get: " + board.get(new Pair<>(i, j)));
                if((word.charAt(0) == board.get(new Pair<>(i, j))) && search(board, word, i, j, 0)){
                	return true;
                    
                }
            }
        }
        return false;
    }
	
    private static boolean search(Map<Pair<Integer, Integer>, Character> board, String word, int i, int j, int index){
        
    	Map<Pair<Integer, Integer>, Boolean> visited = new HashMap<>();
    	
    	if(index == word.length()){
            return true;
        }
        
        if(i >= 4 || i < 0 || j >= 4 || j < 0 || board.get(new Pair<>(i,j)) != word.charAt(index) || visited.containsKey(new Pair<>(i,j))) {
            return false;
        }
        
        visited.put(new Pair<>(i,j), true);
        
        if(search(board, word, i-1, j, index+1) || 
           search(board, word, i+1, j, index+1) ||
           search(board, word, i, j-1, index+1) || 
           search(board, word, i, j+1, index+1) ||
           search(board, word, i-1, j+1, index+1) ||
           search(board, word, i+1, j+1, index+1) ||
           search(board, word, i-1, j-1, index+1) ||
           search(board, word, i+1, j-1, index+1)){
            return true;
        }
        
        visited.put(new Pair<>(i,j), false);
        return false;
    }
}
