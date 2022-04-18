package wazzle.model.minigame;

import java.util.List;
import java.util.Objects;

import javafx.util.Pair;

public class MiniGameWordImpl implements MiniGameWord {
	private List<Pair<Character, Result>> compositeWord;
	private boolean isTheCorrectWord;
	public MiniGameWordImpl() {
	}

	@Override
	public String toString() {
		return "MiniGameWordImpl [compositeWord=" + compositeWord + ", isTheCorrectWord=" + isTheCorrectWord + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(compositeWord, isTheCorrectWord);
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
		return Objects.equals(compositeWord, other.compositeWord) && isTheCorrectWord == other.isTheCorrectWord;
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
	
	@Override
	public void setCorrect() {
		this.isTheCorrectWord = true;
	}
	
	@Override
	public void setWrong() {
		this.isTheCorrectWord = false;
	}
	
	@Override
	public boolean isCorrect() {
		return this.isTheCorrectWord;
	} 
	

}
