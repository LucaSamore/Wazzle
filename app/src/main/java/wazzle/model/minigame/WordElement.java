package wazzle.model.minigame;

import java.util.Objects;

import com.google.gson.annotations.Expose;


public final class WordElement {
	@Expose
	private char character;
	
	@Expose
	private int result;

	public WordElement(final char character, final int result) {
		this.character = character;
		this.result = result;
	}

	public char getCharacter() {
		return this.character;
	}

	@Override
	public String toString() {
		return "WordElement [character=" + character + ", result=" + result + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(character, result);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordElement other = (WordElement) obj;
		return character == other.character && result == other.result;
	}

	public int getResult() {
		return this.result;
	}

}
