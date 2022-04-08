package wazzle.model.maingame;

import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;

public class WordPathChecker {
	
	static Map<Pair<Integer, Integer>, Boolean> visited = new HashMap<>();
	
	public boolean exist(final Map<Pair<Integer, Integer>, Character> board, final String word) {
		visited.clear();
        for (int i = 0; i < Math.sqrt(board.size()); i++) {
            for (int j = 0; j < Math.sqrt(board.size()); j++) {
                if ((word.charAt(0) == board.get(new Pair<>(i, j))) && this.search(board, word, i, j, 0)) {
                	return true;
                }
            }
        }
        
        return false;
    }
	
    private boolean search(final Map<Pair<Integer, Integer>, Character> board, final String word, 
    		final int i, final int j, final int index) {
    	if (index == word.length()) {
            return true;
        }
        
        if (i >= Math.sqrt(board.size()) || 
        		i < 0 ||
        		j >= Math.sqrt(board.size()) ||
        		j < 0 ||
        		board.get(new Pair<>(i,j)) != word.charAt(index) ||
        		visited.containsKey(new Pair<>(i,j))) {
            return false;
        }
        
        visited.put(new Pair<>(i,j), true);
        
        if (search(board, word, i-1, j, index+1) ||
           search(board, word, i+1, j, index+1) ||
           search(board, word, i, j-1, index+1) ||
           search(board, word, i, j+1, index+1) ||
           search(board, word, i-1, j+1, index+1) ||
           search(board, word, i+1, j+1, index+1) ||
           search(board, word, i-1, j-1, index+1) ||
           search(board, word, i+1, j-1, index+1)) {
            return true;
        }
        
        visited.put(new Pair<>(i,j), false);
        return false;
    }
}
