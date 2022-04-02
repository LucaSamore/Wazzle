package wazzle.model.maingame;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class GridImpl implements Grid {
	
	private final Set<Letter> letters;
	private final Set<String> wordsCanBeFound;
	
	public GridImpl(final Set<Letter> letters, final Set<String> wordsCanBeFound) {
		Objects.requireNonNull(letters);
		Objects.requireNonNull(wordsCanBeFound);
		this.letters = new HashSet<>(letters);
		this.wordsCanBeFound = new HashSet<>(wordsCanBeFound);
	}
	
	@Override
	public Set<Letter> getLetters() {
		return Set.copyOf(this.letters);
	}

	@Override
	public Set<String> getWordsCanBeFound() {
		return Set.copyOf(this.wordsCanBeFound);
	}
	
	@Override
	public Double getTotalScore() {
		return this.letters.stream()
				.map(Letter::getScore)
				.reduce(0.0, (x,y) -> x + y);
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
		return "Here's a lo<3ly grid " + System.lineSeparator() + 
				"Letters inside the grid: " + System.lineSeparator() +
				this.letters + System.lineSeparator() + System.lineSeparator() +
				String.format("Words you can find inside this grid [%d]: ", this.wordsCanBeFound.size()) +
				System.lineSeparator() +
				this.wordsCanBeFound + System.lineSeparator();
	}
}