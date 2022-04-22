package wazzle.model.minigame;

import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.Expose;

public class MiniGameWordImpl implements MiniGameWord {

	@Expose
	private List<WordElement> compositeWord;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<WordElement> getCompositeWord() {
		return List.copyOf(this.compositeWord);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCompositeWord(List<WordElement> compositeWord) {
		this.compositeWord = compositeWord;
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
}
