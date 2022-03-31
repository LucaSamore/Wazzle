package wazzle.model.maingame;

import java.util.Objects;
import java.util.Set;

public class GridImpl implements Grid {
	
	private final Set<Letter> letters;
	private final Set<String> wordsCanBeFound;
	
	public GridImpl(final Set<Letter> letters, final Set<String> wordsCanBeFound) {
		this.letters = letters;
		this.wordsCanBeFound = wordsCanBeFound;
	}
	
	public Set<Letter> getLetters() {
		return this.letters;
	}

	public Set<String> getWordsCanBeFound() {
		return this.wordsCanBeFound;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(letters, wordsCanBeFound);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GridImpl other = (GridImpl) obj;
		return Objects.equals(letters, other.letters) && Objects.equals(wordsCanBeFound, other.wordsCanBeFound);
	}

	@Override
	public String toString() {
		return "Here's a lovely grid <3 " + 
				System.lineSeparator() + 
				"Letters inside the grid: " +
				System.lineSeparator() +
				this.letters + 
				System.lineSeparator() + System.lineSeparator() +
				String.format("Words you can find inside this grid [%d]: ", this.wordsCanBeFound.size()) +
				System.lineSeparator() +
				this.wordsCanBeFound + 
				System.lineSeparator();
	}
}