package wazzle.model.minigame.attempt;

import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.Expose;

public class MiniGameWordImpl implements MiniGameWord {

	@Expose
	private List<WordElement> compositeWord;

	
	public MiniGameWordImpl(final List<WordElement> compositeWord){
	    this.compositeWord = compositeWord;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<WordElement> getCompositeWord() {
		return List.copyOf(this.compositeWord);
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
