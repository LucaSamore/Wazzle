package wazzle.model.minigame;

import java.util.List;
import java.util.Objects;

import javafx.util.Pair;

public class MiniGameWordImpl implements MiniGameWord {
	private List<Pair<Character, Result>> compositeWord;

	public MiniGameWordImpl() {
	}

	@Override
	public String toString() {
		return "MiniGameWordImpl [compositeWord=" + compositeWord + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(compositeWord);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MiniGameWordImpl other = (MiniGameWordImpl) obj;
		return Objects.equals(compositeWord, other.compositeWord);
	}

	/**
	 * @return the compositeWord
	 */
	@Override
	public List<Pair<Character, Result>> getCompositeWord() {
		return compositeWord;
	}

	/**
	 * @param compositeWord the compositeWord to set
	 */
	@Override
	public void setCompositeWord(List<Pair<Character, Result>> compositeWord) {
		this.compositeWord = compositeWord;
	}
}
