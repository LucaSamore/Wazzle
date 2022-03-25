package wazzle.model.maingame;

import java.util.Set;

public interface LetterAllocator {

	Set<Letter> alloc();
	
	boolean checkGridForTests (Set<Letter> alreadyAllocated);
}
